grammar FOOL;

@header{
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import ast.*;
import lib.FOOLlib;
}

@parser::members{
/*
 * Variabile globale per la gestione del nesting level.
 */
private int nestingLevel = 0;
/*
 * Dichiarazione della symbol table.
 * - il livello ambiente con dichiarazioni piu' esterno è 0 (prima posizione ArrayList) invece che 1 (slides);
 * - il "fronte" della lista di tabelle è symTable.get(nestingLevel).
 */
private ArrayList<HashMap<String,STentry>> symTable = new ArrayList<HashMap<String,STentry>>();
/*
 * Dichiarazione della class table.
 * È una struttura dati memorizzante, per ogni classe dichiarata, il riferimento alla relativa Virtual Table.
 * - evita che la Virtual Table di una classe venga persa (consentendo la gestione dell'ereditarietà e
 *   dell'invocazione di metodi con la notazione punto);
 * - consente di recuperarla.
 */
private HashMap<String,HashMap<String,STentry>> classTable = new HashMap<String,HashMap<String,STentry>>();
/*
 * Offset attuale a nesting level 0.
 * Viene usato per decrementare l'offset di funzioni/variabili a nesting level 0, partendo dall'offset
 * raggiunto con le dichiarazioni di classi.
 */
private int offsetNlZero = -2;
}

@lexer::members {
int lexicalErrors=0;
}

/*------------------------------------------------------------------
 * PARSER RULES
 *------------------------------------------------------------------*/

prog returns [Node ast]
	: {HashMap<String,STentry> hm = new HashMap<String,STentry>();
       symTable.add(hm);}
	  (	e=exp
        {$ast = new ProgNode($e.ast);}
      | LET
      	{
      		ArrayList<Node> cAndDecList = new ArrayList<Node>();
      		boolean declistYN = false;
      	}
      	( c=clist (d=declist {declistYN = true;})?
      	{
      		cAndDecList.addAll($c.astlist);
      		if (declistYN)
        		cAndDecList.addAll($d.astlist);
      	}
      	| d=declist
      	{ cAndDecList.addAll($d.astlist); }
      	) IN e=exp
        {
        	$ast = new ProgLetInNode(cAndDecList,$e.ast);
        }
	  )
	  {symTable.remove(nestingLevel);}
      SEMIC
      ;

clist returns [ArrayList<Node> astlist]
	: {$astlist = new ArrayList<Node>();}
	(
		{boolean inheritance = false;}
		CLASS i=ID (EXTENDS e=ID {inheritance = true;})?
		{
			ClassNode c = new ClassNode($i.text);
			$astlist.add(c);
			/*
			 * Struttura dati usata per la rilevazione di ridefinzioni (erronee) di campi e metodi
			 * con stesso nome all'interno della stessa classe.
			 */
			HashSet<String> localDec = new HashSet<String>();
			// dichiaro il tipo della classe e la relativa Virtual Table
			ClassTypeNode ct = null;
			HashMap<String,STentry> vt = null;
			if (inheritance) {	// se eredito
				if (classTable.containsKey($e.text)) {
					/*
					 * Creo il tipo copiando quello della classe da cui si eredita:
					 * recupero la STentry della classe da cui eredito (considerando nestingLevel=0,
					 * dal momento che tutte le classi sono definite nell'ambiente globale).
					 */
					STentry superEntry = symTable.get(nestingLevel).get($e.text);
					ct = (ClassTypeNode)((ClassTypeNode)superEntry.getType()).clone();
					/*
					 * Copio la Virtual Table della classe da cui si eredita:
					 * recupero la vt attraverso la ClassTable e la clono.
					 */
					vt = (HashMap<String,STentry>)classTable.get($e.text).clone();
					/*
					 * Memorizzo la relazione di sottotipo attraverso la struttura dati supertype.
					 */
					FOOLlib.addSuperTypeRelation($i.text,$e.text);
					/*
					 * Memorizzo l'entry della classe super nella classe corrente.
					 */
					c.setSuperEntry(superEntry);
				} else {
					System.out.println("Id "+$e.text+" at line "+$e.line+" not valid");
					System.exit(0);
				}
			} else {			// se non eredito
				ct = new ClassTypeNode();										// creo un ClassTypeNode inizialmente vuoto
				vt = new HashMap<String,STentry>();								// creo una Virtual Table vuota
			}
			// mappo il nome della classe con la VT creata o copiata
			classTable.put($i.text,vt);
			// inserisco la entry della classe in symbol table
			HashMap<String,STentry> hm = symTable.get(nestingLevel);
			if (hm.put($i.text,new STentry(nestingLevel,ct,offsetNlZero--,false)) != null)
        	{
        		System.out.println("Class id "+$i.text+" at line "+$i.line+" already declared");
        		System.exit(0);
        	}
        	// creo un nuovo livello per la symbol table
            nestingLevel++;
            // setto il livello con la nuova virtual table creata
            symTable.add(vt);
		}
		LPAR {
			/*
			 * Se non si eredita, il contatore di offset per i campi è settato inizialmente in base
			 * al layout di oggetti: -1.
			 * Altrimenti, è impostato al primo offset libero in base alla lunghezza di allFields
			 * nel ClassTypeNode legato alla classe da cui si eredita.
			 */
			int fieldOffset = (inheritance) ?
				-((ClassTypeNode)(symTable.get(0).get($e.text).getType())).getAllFields().size()-1 : -1;
		}
		(fid=ID COLON fty=type
		{
			if (!localDec.add($fid.text)) {
				System.out.println("Erroneous redefinition of the field "+$fid.text+" at line "+$fid.line);
				System.exit(0);
			}
			FieldNode f = new FieldNode($fid.text,$fty.ast);
			if (!vt.containsKey($fid.text)) {													// nuovo campo
				// setto l'offset nel nodo
				f.addOffset(fieldOffset);
				// aggiorno la Virtual Table
				vt.put($fid.text,new STentry(nestingLevel,$fty.ast,fieldOffset--,false));
				// aggiorno l'oggetto ClassTypeNode
				ct.addFieldType($fty.ast);
			} else {																			// overriding
				STentry tmpEntry = vt.get($fid.text);
				if (!tmpEntry.isMethod()) {
					// setto l'offset nel nodo
					f.addOffset(fieldOffset);
					// aggiorno la Virtual Table (preservando l'offset)
					vt.put($fid.text,new STentry(nestingLevel,$fty.ast,tmpEntry.getOffset(),false));
					// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
					ct.setFieldType(-tmpEntry.getOffset()-1, $fty.ast);
				} else {
					System.out.println("Field id "+$fid.text+" at line "+$fid.line+" cannot be overridden by a method");
					System.exit(0);
				}
			}
			// memorizzo il campo in ClassNode
			c.addField(f);
		}
		(COMMA id=ID COLON ty=type
		{
			if (!localDec.add($id.text)) {
				System.out.println("Erroneous redefinition of the field "+$id.text+" at line "+$id.line);
				System.exit(0);
			}
			f = new FieldNode($id.text,$ty.ast);
			if (!vt.containsKey($id.text)) {													// nuovo campo
				// setto l'offset nel nodo
				f.addOffset(fieldOffset);
				// aggiorno la Virtual Table
				vt.put($id.text,new STentry(nestingLevel,$ty.ast,fieldOffset--,false));
				// aggiorno l'oggetto ClassTypeNode
				ct.addFieldType($ty.ast);
			} else {																			// overriding
				STentry tmpEntry = vt.get($id.text);
				if (!tmpEntry.isMethod())
				{
					// setto l'offset nel nodo
					f.addOffset(fieldOffset);
					// aggiorno la Virtual Table (preservando l'offset)
					vt.put($id.text,new STentry(nestingLevel,$ty.ast,tmpEntry.getOffset(),false));
					// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
					ct.setFieldType(-tmpEntry.getOffset()-1,$ty.ast);
				} else {
					System.out.println("Field id "+$id.text+" at line "+$id.line+" cannot be overridden by a method");
					System.exit(0);
				}				
			}
			// memorizzo il campo in ClassNode
			c.addField(f);
		}
		)*
		)?
		RPAR CLPAR {
			/*
			 * Se non si eredita, il contatore di offset per i metodi è settato inizialmente in base
			 * al layout delle dispatch tables: 0.
			 * Altrimenti, è impostato al primo offset libero in base alla lunghezza di allMethods
			 * nel ClassTypeNode legato alla classe da cui si eredita.
			 */
			int methodOffset = (inheritance) ?
				((ClassTypeNode)(symTable.get(0).get($e.text).getType())).getAllMethods().size() : 0;
		}
			(FUN mi=ID COLON mty=type
			{
				if (!localDec.add($mi.text)) {
					System.out.println("Erroneous redefinition of the method "+$mi.text+" at line "+$mi.line);
					System.exit(0);
				}
				MethodNode m = new MethodNode($mi.text,$mty.ast);
				STentry entry = null;
				if (!vt.containsKey($mi.text)) {													// nuovo metodo
					// memorizzo in MethodNode l'offset messo in symbol table
					m.addOffset(methodOffset);
					// aggiorno la Virtual Table
					entry = new STentry(nestingLevel,methodOffset++,true);
					vt.put($mi.text,entry);
					// aggiorno l'oggetto ClassTypeNode
					ct.addMethodType($mty.ast);
				} else {																			// overriding				
					STentry tmpEntry = vt.get($mi.text);
					if (tmpEntry.isMethod()) {
						// memorizzo in MethodNode l'offset messo in symbol table
						m.addOffset(tmpEntry.getOffset());
						// aggiorno la Virtual Table (preservando l'offset)
						entry = new STentry(nestingLevel,tmpEntry.getOffset(),true);
						vt.put($mi.text,entry);
						// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
						ct.setMethodType(tmpEntry.getOffset(),$mty.ast);
					} else {
						System.out.println("Method id "+$mi.text+" at line "+$mi.line+" cannot be overridden by a field");
						System.exit(0);
					}
				}
				// memorizzo il metodo in ClassNode
				c.addMethod(m);
				// creo una nuova hashmap per la symTable
                nestingLevel++;
                HashMap<String,STentry> hmm = new HashMap<String,STentry>();
                symTable.add(hmm);
			}
			LPAR {ArrayList<Node> parTypes = new ArrayList<Node>();
				int parOffset=0;}
			(pid=ID COLON pt=hotype
			{
				parTypes.add($pt.ast);
				ParNode mpar = new ParNode($pid.text,$pt.ast);											// creo un nodo ParNode
				m.addPar(mpar);																			// lo collego al MethodNode con addPar
                parOffset = ($pt.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
                // aggiungo la dichiarazione nell'hashmap del livello corrente
				if (hmm.put($pid.text,new STentry(nestingLevel,$pt.ast,parOffset,false)) != null)
				{
					System.out.println("Parameter id "+$pid.text+" at line "+$pid.line+" already declared");
					System.exit(0);
				}
			}
			(COMMA pi=ID COLON pty=hotype
			{
				parTypes.add($pty.ast);
				ParNode par = new ParNode($pi.text,$pty.ast);
				m.addPar(par);
				parOffset = ($pty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
				// aggiungo la dichiarazione nell'hashmap del livello corrente
				if (hmm.put($pi.text,new STentry(nestingLevel,$pty.ast,parOffset,false)) != null)
				{
					System.out.println("Parameter id "+$pi.text+" at line "+$pi.line+" already declared");
					System.exit(0);
				}
			}
			)*
			)?
			RPAR
			{
				ArrowTypeNode symType = new ArrowTypeNode(parTypes,$mty.ast);
				entry.addType(symType);
				// memorizzo in MethodNode il tipo messo in symbol table
				m.addSymType(symType);
			}
			(LET {int varOffset=-2;}
			(VAR vi=ID COLON vty=type ASS ve=exp
			{
				VarNode v = new VarNode($vi.text,$vty.ast,$ve.ast);
				// memorizzo la dichiarazione interna al metodo
				m.addDec(v);
				// aggiungo la dichiarazione nell'hashmap del livello corrente
				if (hmm.put($vi.text,new STentry(nestingLevel,$vty.ast,varOffset--,false)) != null)
				{
					System.out.println("Var id "+$vi.text+" at line "+$vi.line+" already declared");
					System.exit(0);
				}
			}
			SEMIC)* IN)? me=exp
			{
				m.addBody($me.ast);
				// rimuovo la hashmap corrente poiché esco dallo scope del metodo
				symTable.remove(nestingLevel--);
			}
			 SEMIC
			)*
		CRPAR
		{
			// memorizzo in ClassNode il tipo messo in symbol table
			c.addSymType(ct);
			// rimuovo la hashmap corrente poiché esco dallo scope della classe
			symTable.remove(nestingLevel--);
		}
	)+
	;

declist	returns [ArrayList<Node> astlist]
	: {$astlist = new ArrayList<Node>();
	   int offset = (nestingLevel==0)?offsetNlZero:-2;}
	  ( (
	  		VAR i=ID COLON ht=hotype ASS e=exp
            {
            	VarNode v = new VarNode($i.text,$ht.ast,$e.ast);
            	$astlist.add(v);
            	HashMap<String,STentry> hm = symTable.get(nestingLevel);
            	if (hm.put($i.text,new STentry(nestingLevel,$ht.ast,offset,false)) != null)
            	{
            		System.out.println("Var id "+$i.text+" at line "+$i.line+" already declared");
            		System.exit(0);
            	}
            	offset = ($ht.ast instanceof ArrowTypeNode) ? (offset - 2) : (offset - 1);
            }
      |
            FUN i=ID COLON t=type
            {
            	// inserisco ID nella symbol table
            	FunNode f = new FunNode($i.text,$t.ast);
            	$astlist.add(f);
            	HashMap<String,STentry> hm = symTable.get(nestingLevel);
            	STentry entry = new STentry(nestingLevel,offset,false);
            	offset-=2;
            	if (hm.put($i.text,entry) != null)
            	{
            		System.out.println("Fun id "+$i.text+" at line "+$i.line+" already declared");
            		System.exit(0);
            	}
                // creo una nuova hashmap per la symTable
                nestingLevel++;
                HashMap<String,STentry> hmn = new HashMap<String,STentry>();
                symTable.add(hmn);
			}
			LPAR {ArrayList<Node> parTypes = new ArrayList<Node>();
				int parOffset=0;}
			(fid=ID COLON fty=hotype
			{
				parTypes.add($fty.ast);
				ParNode fpar = new ParNode($fid.text,$fty.ast);											// creo nodo ParNode
				f.addPar(fpar);																			// lo collego al FunNode con addPar
                parOffset = ($fty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
				if (hmn.put($fid.text,new STentry(nestingLevel,$fty.ast,parOffset,false)) != null)		// aggiungo dich a hmn
				{
					System.out.println("Parameter id "+$fid.text+" at line "+$fid.line+" already declared");
					System.exit(0);
				}
			}
			(COMMA id=ID COLON ty=hotype
			{
				parTypes.add($ty.ast);
				ParNode par = new ParNode($id.text,$ty.ast);
				f.addPar(par);
				parOffset = ($ty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
				if (hmn.put($id.text,new STentry(nestingLevel,$ty.ast,parOffset,false)) != null)
				{
					System.out.println("Parameter id "+$id.text+" at line "+$id.line+" already declared");
					System.exit(0);
				}
			}
			)*
			)?
			RPAR
			{
				ArrowTypeNode symType = new ArrowTypeNode(parTypes,$t.ast);
				entry.addType(symType);
				f.addSymType(symType);
			}
			(LET d=declist IN {f.addDec($d.astlist);})? e=exp
			{
				f.addBody($e.ast);
				// rimuovo la hashmap corrente poiché esco dallo scope
				symTable.remove(nestingLevel--);
			}
      ) SEMIC
    )+
	;

exp	returns [Node ast]
 	: f=term {$ast = $f.ast;}
 	    (PLUS l=term
 	    	{$ast = new PlusNode($ast,$l.ast);}
 	    | MINUS l=term
 	    	{$ast = new MinusNode($ast,$l.ast);}
 	    | OR l=term
 	    	{$ast = new OrNode($ast,$l.ast);}
 	    )*
 	;

term returns [Node ast]
	: f=factor {$ast = $f.ast;}
	    (TIMES l=factor
	    	{$ast = new MultNode($ast,$l.ast);}
	    | DIV l=factor
	    	{$ast = new DivNode($ast,$l.ast);}
	    | AND l=factor
	    	{$ast = new AndNode($ast,$l.ast);}
	    )*
	;

factor returns [Node ast]
	: f=value {$ast = $f.ast;}
	    (EQ l=value
	    	{$ast = new EqualNode($ast,$l.ast);}
	    | GE l=value
	    	{$ast = new GreaterOrEqualNode($ast,$l.ast);}
	    | LE l=value
	    	{$ast = new LessOrEqualNode($ast,$l.ast);}
	    )*
 	;

value returns [Node ast]
	: n=INTEGER
		{$ast = new IntNode(Integer.parseInt($n.text));}
	| TRUE
		{$ast = new BoolNode(true);}
	| FALSE
		{$ast = new BoolNode(false);}
	| NULL
		{$ast = new EmptyNode();}
	| NEW i=ID
		{
			STentry entry = null;
			if (classTable.containsKey($i.text)) {
				/*
				 * Trattandosi di una classe, ricerco la STentry direttamente
				 * dal livello 0 della symbol table.
				 */
				entry = symTable.get(0).get($i.text);
			} else {
				System.out.println("Id "+$i.text+" at line "+$i.line+" is not a class");
			 	System.exit(0);
			}
		}
		LPAR
		{ArrayList<Node> arglist = new ArrayList<Node>();}
		(a=exp {arglist.add($a.ast);}
			(COMMA a=exp {arglist.add($a.ast);})*)?
		RPAR
		{$ast = new NewNode($i.text,entry,arglist,nestingLevel);}
	| LPAR e=exp RPAR
		{$ast = $e.ast;}
	| IF x=exp THEN CLPAR y=exp CRPAR
		ELSE CLPAR z=exp CRPAR
		{$ast = new IfNode($x.ast,$y.ast,$z.ast);}
	| NOT LPAR e=exp RPAR
		{$ast = new NotNode($e.ast);}
	| PRINT LPAR e=exp RPAR
		{$ast = new PrintNode($e.ast);}
	| i=ID
		{
			// ricerca della dichiarazione
			int j = nestingLevel;
			STentry entry = null;
			while (j >= 0 && entry == null)
				entry = (symTable.get(j--)).get($i.text);
			if (entry == null) {
				System.out.println("Id "+$i.text+" at line "+$i.line+" not declared");
			 	System.exit(0);
			}
			$ast = new IdNode($i.text,entry,nestingLevel);
		}
		(LPAR
	   	{ArrayList<Node> arglist = new ArrayList<Node>();}
	   	(a=exp {arglist.add($a.ast);}
	   		(COMMA a=exp {arglist.add($a.ast);})*
	   	)?
	   	RPAR
	   	{$ast = new CallNode($i.text,entry,arglist,nestingLevel);}
	   		| DOT mi=ID
	   			{
	   				HashMap<String,STentry> vt = null;
	   				// controllo che il tipo dentro STentry di ID1 sia un RefTypeNode
	   				if (entry.getType() instanceof RefTypeNode) {
	   					// cerco la virtual table della classe del tipo RefTypeNode di ID1
	   					vt = classTable.get(((RefTypeNode)entry.getType()).getClassId());
	   					if (!vt.containsKey($mi.text)) {
	   						System.out.println("Method "+$mi.text+" at line "+$mi.line+" is not defined by the class of object "+$i.text);
	   						System.exit(0);
	   					}
	   				} else {
	   					System.out.println("Id "+$i.text+" at line "+$i.line+" is not RefType");
	   					System.exit(0);
	   				}
	   			}
	   			LPAR
		   		{ArrayList<Node> arglist = new ArrayList<Node>();}
			   	(a=exp {arglist.add($a.ast);}
			   		(COMMA a=exp {arglist.add($a.ast);})*
			   	)?
			   	RPAR
		   		{$ast = new ClassCallNode($i.text,$mi.text,entry,vt.get($mi.text),arglist,nestingLevel);}
	   )?
 	;

hotype returns [Node ast]
	: f=type {$ast = $f.ast;}
	| l=arrow {$ast = $l.ast;}
	;

type returns [Node ast]
	: INT	{$ast = new IntTypeNode();}
	| BOOL	{$ast = new BoolTypeNode();}
	| c=ID	{$ast = new RefTypeNode($c.text);}
	;
	
arrow returns [Node ast]
	: LPAR
	  {ArrayList<Node> arglist = new ArrayList<Node>();}
	  ( a=hotype {arglist.add($a.ast);}
	  		(COMMA a=hotype {arglist.add($a.ast);})*
	  )?
	  RPAR
	  ARROW
	  f=type {$ast = new ArrowTypeNode(arglist,$f.ast);}
	;
	
/*------------------------------------------------------------------
 * LEXER RULES
 *------------------------------------------------------------------*/

COLON	: ':' ;
COMMA	: ',' ;
DOT		: '.' ;
ASS	: '=' ;
//
SEMIC : ';' ;
EQ  : '==' ;
GE	: '>=' ;
LE	: '<=' ;
OR	: '||' ;		// logical OR: does not evaluate additional arguments if the left operand is true
AND	: '&&' ;		// logical AND: does not evaluate additional arguments if the left operand is false
NOT	: '!' ;
PLUS	: '+' ;
MINUS	: '-' ;
TIMES	: '*' ;
DIV		: '/' ;
INTEGER : ('-')?(('1'..'9')('0'..'9')*) | '0' ;
TRUE	: 'true' ;
FALSE	: 'false' ;
LPAR 	: '(' ;
RPAR	: ')' ;
CLPAR 	: '{' ;
CRPAR	: '}' ;
IF 	: 'if' ;
THEN 	: 'then' ;
ELSE 	: 'else' ;
PRINT	: 'print' ;
//
LET	: 'let' ;
IN	: 'in' ;
VAR	: 'var' ;
FUN	: 'fun' ;
CLASS	: 'class' ;
EXTENDS	: 'extends' ;
NEW		: 'new' ;
NULL	: 'null' ;
INT	: 'int' ;
BOOL	: 'bool' ;
ARROW	: '->';

ID 	: ('a'..'z'|'A'..'Z') ('a'..'z'|'A'..'Z'|'0'..'9')* ;

WHITESP : (' '|'\t'|'\n'|'\r')+ -> channel(HIDDEN) ;

COMMENT : '/*' (.)*? '*/' -> channel(HIDDEN) ;

ERR     : . { System.out.println("Invalid char: "+ getText()); lexicalErrors++; } -> channel(HIDDEN);
