// Generated from FOOL.g4 by ANTLR 4.7

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import ast.*;
import lib.FOOLlib;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FOOLParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		COLON=1, COMMA=2, DOT=3, ASS=4, SEMIC=5, EQ=6, GE=7, LE=8, OR=9, AND=10, 
		NOT=11, PLUS=12, MINUS=13, TIMES=14, DIV=15, INTEGER=16, TRUE=17, FALSE=18, 
		LPAR=19, RPAR=20, CLPAR=21, CRPAR=22, IF=23, THEN=24, ELSE=25, PRINT=26, 
		LET=27, IN=28, VAR=29, FUN=30, CLASS=31, EXTENDS=32, NEW=33, NULL=34, 
		INT=35, BOOL=36, ARROW=37, ID=38, WHITESP=39, COMMENT=40, ERR=41;
	public static final int
		RULE_prog = 0, RULE_clist = 1, RULE_declist = 2, RULE_exp = 3, RULE_term = 4, 
		RULE_factor = 5, RULE_value = 6, RULE_hotype = 7, RULE_type = 8, RULE_arrow = 9;
	public static final String[] ruleNames = {
		"prog", "clist", "declist", "exp", "term", "factor", "value", "hotype", 
		"type", "arrow"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "':'", "','", "'.'", "'='", "';'", "'=='", "'>='", "'<='", "'||'", 
		"'&&'", "'!'", "'+'", "'-'", "'*'", "'/'", null, "'true'", "'false'", 
		"'('", "')'", "'{'", "'}'", "'if'", "'then'", "'else'", "'print'", "'let'", 
		"'in'", "'var'", "'fun'", "'class'", "'extends'", "'new'", "'null'", "'int'", 
		"'bool'", "'->'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "COLON", "COMMA", "DOT", "ASS", "SEMIC", "EQ", "GE", "LE", "OR", 
		"AND", "NOT", "PLUS", "MINUS", "TIMES", "DIV", "INTEGER", "TRUE", "FALSE", 
		"LPAR", "RPAR", "CLPAR", "CRPAR", "IF", "THEN", "ELSE", "PRINT", "LET", 
		"IN", "VAR", "FUN", "CLASS", "EXTENDS", "NEW", "NULL", "INT", "BOOL", 
		"ARROW", "ID", "WHITESP", "COMMENT", "ERR"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "FOOL.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }


	/*
	 * Variabile globale per la gestione del nesting level.
	 */
	private int nestingLevel = 0;
	/*
	 * Dichiarazione della symbol table.
	 * - il livello ambiente con dichiarazioni piu' esterno � 0 (prima posizione ArrayList) invece che 1 (slides);
	 * - il "fronte" della lista di tabelle � symTable.get(nestingLevel).
	 */
	private ArrayList<HashMap<String,STentry>> symTable = new ArrayList<HashMap<String,STentry>>();
	/*
	 * Dichiarazione della class table.
	 * � una struttura dati memorizzante, per ogni classe dichiarata, il riferimento alla relativa Virtual Table.
	 * - evita che la Virtual Table di una classe venga persa (consentendo la gestione dell'ereditariet� e
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

	public FOOLParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ProgContext extends ParserRuleContext {
		public Node ast;
		public ExpContext e;
		public ClistContext c;
		public DeclistContext d;
		public TerminalNode SEMIC() { return getToken(FOOLParser.SEMIC, 0); }
		public TerminalNode LET() { return getToken(FOOLParser.LET, 0); }
		public TerminalNode IN() { return getToken(FOOLParser.IN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public ClistContext clist() {
			return getRuleContext(ClistContext.class,0);
		}
		public DeclistContext declist() {
			return getRuleContext(DeclistContext.class,0);
		}
		public ProgContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_prog; }
	}

	public final ProgContext prog() throws RecognitionException {
		ProgContext _localctx = new ProgContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_prog);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			HashMap<String,STentry> hm = new HashMap<String,STentry>();
			       symTable.add(hm);
			setState(43);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case NOT:
			case INTEGER:
			case TRUE:
			case FALSE:
			case LPAR:
			case IF:
			case PRINT:
			case NEW:
			case NULL:
			case ID:
				{
				setState(21);
				((ProgContext)_localctx).e = exp();
				((ProgContext)_localctx).ast =  new ProgNode(((ProgContext)_localctx).e.ast);
				}
				break;
			case LET:
				{
				setState(24);
				match(LET);

				      		ArrayList<Node> cAndDecList = new ArrayList<Node>();
				      		boolean declistYN = false;
				      	
				setState(37);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(26);
					((ProgContext)_localctx).c = clist();
					setState(30);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(27);
						((ProgContext)_localctx).d = declist();
						declistYN = true;
						}
					}


					      		cAndDecList.addAll(((ProgContext)_localctx).c.astlist);
					      		if (declistYN)
					        		cAndDecList.addAll(((ProgContext)_localctx).d.astlist);
					      	
					}
					break;
				case VAR:
				case FUN:
					{
					setState(34);
					((ProgContext)_localctx).d = declist();
					 cAndDecList.addAll(((ProgContext)_localctx).d.astlist); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(39);
				match(IN);
				setState(40);
				((ProgContext)_localctx).e = exp();

				        	((ProgContext)_localctx).ast =  new ProgLetInNode(cAndDecList,((ProgContext)_localctx).e.ast);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			symTable.remove(nestingLevel);
			setState(46);
			match(SEMIC);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ClistContext extends ParserRuleContext {
		public ArrayList<Node> astlist;
		public Token i;
		public Token e;
		public Token fid;
		public TypeContext fty;
		public Token id;
		public TypeContext ty;
		public Token mi;
		public TypeContext mty;
		public Token pid;
		public HotypeContext pt;
		public Token pi;
		public HotypeContext pty;
		public Token vi;
		public TypeContext vty;
		public ExpContext ve;
		public ExpContext me;
		public List<TerminalNode> CLASS() { return getTokens(FOOLParser.CLASS); }
		public TerminalNode CLASS(int i) {
			return getToken(FOOLParser.CLASS, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<TerminalNode> EXTENDS() { return getTokens(FOOLParser.EXTENDS); }
		public TerminalNode EXTENDS(int i) {
			return getToken(FOOLParser.EXTENDS, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public ClistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_clist; }
	}

	public final ClistContext clist() throws RecognitionException {
		ClistContext _localctx = new ClistContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_clist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((ClistContext)_localctx).astlist =  new ArrayList<Node>();
			setState(136); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				boolean inheritance = false;
				setState(50);
				match(CLASS);
				setState(51);
				((ClistContext)_localctx).i = match(ID);
				setState(55);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(52);
					match(EXTENDS);
					setState(53);
					((ClistContext)_localctx).e = match(ID);
					inheritance = true;
					}
				}


							ClassNode c = new ClassNode((((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getText():null));
							_localctx.astlist.add(c);
							/*
							 * Struttura dati usata per la rilevazione di ridefinzioni (erronee) di campi e metodi
							 * con stesso nome all'interno della stessa classe.
							 */
							HashSet<String> localDec = new HashSet<String>();
							// dichiaro il tipo della classe e la relativa Virtual Table
							ClassTypeNode ct = null;
							HashMap<String,STentry> vt = null;
							if (inheritance) {	// se eredito
								if (classTable.containsKey((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null))) {
									/*
									 * Creo il tipo copiando quello della classe da cui si eredita:
									 * recupero la STentry della classe da cui eredito (considerando nestingLevel=0,
									 * dal momento che tutte le classi sono definite nell'ambiente globale).
									 */
									STentry superEntry = symTable.get(nestingLevel).get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null));
									ct = (ClassTypeNode)((ClassTypeNode)superEntry.getType()).clone();
									/*
									 * Copio la Virtual Table della classe da cui si eredita:
									 * recupero la vt attraverso la ClassTable e la clono.
									 */
									vt = (HashMap<String,STentry>)classTable.get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)).clone();
									/*
									 * Memorizzo la relazione di sottotipo attraverso la struttura dati supertype.
									 */
									FOOLlib.addSuperTypeRelation((((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getText():null),(((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null));
									/*
									 * Memorizzo l'entry della classe super nella classe corrente.
									 */
									c.setSuperEntry(superEntry);
								} else {
									System.out.println("Id "+(((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)+" at line "+(((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getLine():0)+" not valid");
									System.exit(0);
								}
							} else {			// se non eredito
								ct = new ClassTypeNode();										// creo un ClassTypeNode inizialmente vuoto
								vt = new HashMap<String,STentry>();								// creo una Virtual Table vuota
							}
							// mappo il nome della classe con la VT creata o copiata
							classTable.put((((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getText():null),vt);
							// inserisco la entry della classe in symbol table
							HashMap<String,STentry> hm = symTable.get(nestingLevel);
							if (hm.put((((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getText():null),new STentry(nestingLevel,ct,offsetNlZero--,false)) != null)
				        	{
				        		System.out.println("Class id "+(((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getText():null)+" at line "+(((ClistContext)_localctx).i!=null?((ClistContext)_localctx).i.getLine():0)+" already declared");
				        		System.exit(0);
				        	}
				        	// creo un nuovo livello per la symbol table
				            nestingLevel++;
				            // setto il livello con la nuova virtual table creata
				            symTable.add(vt);
						
				setState(58);
				match(LPAR);

							/*
							 * Se non si eredita, il contatore di offset per i campi � settato inizialmente in base
							 * al layout di oggetti: -1.
							 * Altrimenti, � impostato al primo offset libero in base alla lunghezza di allFields
							 * nel ClassTypeNode legato alla classe da cui si eredita.
							 */
							int fieldOffset = (inheritance) ?
								-((ClassTypeNode)(symTable.get(0).get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)).getType())).getAllFields().size()-1 : -1;
						
				setState(75);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(60);
					((ClistContext)_localctx).fid = match(ID);
					setState(61);
					match(COLON);
					setState(62);
					((ClistContext)_localctx).fty = type();

								if (!localDec.add((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null))) {
									System.out.println("Erroneous redefinition of the field "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null)+" at line "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getLine():0));
									System.exit(0);
								}
								FieldNode f = new FieldNode((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null),((ClistContext)_localctx).fty.ast);
								if (!vt.containsKey((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null))) {													// nuovo campo
									// setto l'offset nel nodo
									f.addOffset(fieldOffset);
									// aggiorno la Virtual Table
									vt.put((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).fty.ast,fieldOffset--,false));
									// aggiorno l'oggetto ClassTypeNode
									ct.addFieldType(((ClistContext)_localctx).fty.ast);
								} else {																			// overriding
									STentry tmpEntry = vt.get((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null));
									if (!tmpEntry.isMethod()) {
										// setto l'offset nel nodo
										f.addOffset(fieldOffset);
										// aggiorno la Virtual Table (preservando l'offset)
										vt.put((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).fty.ast,tmpEntry.getOffset(),false));
										// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
										ct.setFieldType(-tmpEntry.getOffset()-1, ((ClistContext)_localctx).fty.ast);
									} else {
										System.out.println("Field id "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null)+" at line "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getLine():0)+" cannot be overridden by a method");
										System.exit(0);
									}
								}
								// memorizzo il campo in ClassNode
								c.addField(f);
							
					setState(72);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(64);
						match(COMMA);
						setState(65);
						((ClistContext)_localctx).id = match(ID);
						setState(66);
						match(COLON);
						setState(67);
						((ClistContext)_localctx).ty = type();

									if (!localDec.add((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null))) {
										System.out.println("Erroneous redefinition of the field "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null)+" at line "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getLine():0));
										System.exit(0);
									}
									f = new FieldNode((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null),((ClistContext)_localctx).ty.ast);
									if (!vt.containsKey((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null))) {													// nuovo campo
										// setto l'offset nel nodo
										f.addOffset(fieldOffset);
										// aggiorno la Virtual Table
										vt.put((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).ty.ast,fieldOffset--,false));
										// aggiorno l'oggetto ClassTypeNode
										ct.addFieldType(((ClistContext)_localctx).ty.ast);
									} else {																			// overriding
										STentry tmpEntry = vt.get((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null));
										if (!tmpEntry.isMethod())
										{
											// setto l'offset nel nodo
											f.addOffset(fieldOffset);
											// aggiorno la Virtual Table (preservando l'offset)
											vt.put((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).ty.ast,tmpEntry.getOffset(),false));
											// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
											ct.setFieldType(-tmpEntry.getOffset()-1,((ClistContext)_localctx).ty.ast);
										} else {
											System.out.println("Field id "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null)+" at line "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getLine():0)+" cannot be overridden by a method");
											System.exit(0);
										}				
									}
									// memorizzo il campo in ClassNode
									c.addField(f);
								
						}
						}
						setState(74);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(77);
				match(RPAR);
				setState(78);
				match(CLPAR);

							/*
							 * Se non si eredita, il contatore di offset per i metodi � settato inizialmente in base
							 * al layout delle dispatch tables: 0.
							 * Altrimenti, � impostato al primo offset libero in base alla lunghezza di allMethods
							 * nel ClassTypeNode legato alla classe da cui si eredita.
							 */
							int methodOffset = (inheritance) ?
								((ClassTypeNode)(symTable.get(0).get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)).getType())).getAllMethods().size() : 0;
						
				setState(131);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(80);
					match(FUN);
					setState(81);
					((ClistContext)_localctx).mi = match(ID);
					setState(82);
					match(COLON);
					setState(83);
					((ClistContext)_localctx).mty = type();

									if (!localDec.add((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null))) {
										System.out.println("Erroneous redefinition of the method "+(((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null)+" at line "+(((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getLine():0));
										System.exit(0);
									}
									MethodNode m = new MethodNode((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null),((ClistContext)_localctx).mty.ast);
									STentry entry = null;
									if (!vt.containsKey((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null))) {													// nuovo metodo
										// memorizzo in MethodNode l'offset messo in symbol table
										m.addOffset(methodOffset);
										// aggiorno la Virtual Table
										entry = new STentry(nestingLevel,methodOffset++,true);
										vt.put((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null),entry);
										// aggiorno l'oggetto ClassTypeNode
										ct.addMethodType(((ClistContext)_localctx).mty.ast);
									} else {																			// overriding				
										STentry tmpEntry = vt.get((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null));
										if (tmpEntry.isMethod()) {
											// memorizzo in MethodNode l'offset messo in symbol table
											m.addOffset(tmpEntry.getOffset());
											// aggiorno la Virtual Table (preservando l'offset)
											entry = new STentry(nestingLevel,tmpEntry.getOffset(),true);
											vt.put((((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null),entry);
											// aggiorno l'oggetto ClassTypeNode (con trasformazione offset - posizione array)
											ct.setMethodType(tmpEntry.getOffset(),((ClistContext)_localctx).mty.ast);
										} else {
											System.out.println("Method id "+(((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getText():null)+" at line "+(((ClistContext)_localctx).mi!=null?((ClistContext)_localctx).mi.getLine():0)+" cannot be overridden by a field");
											System.exit(0);
										}
									}
									// memorizzo il metodo in ClassNode
									c.addMethod(m);
									// creo una nuova hashmap per la symTable
					                nestingLevel++;
					                HashMap<String,STentry> hmm = new HashMap<String,STentry>();
					                symTable.add(hmm);
								
					setState(85);
					match(LPAR);
					ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset=0;
					setState(102);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(87);
						((ClistContext)_localctx).pid = match(ID);
						setState(88);
						match(COLON);
						setState(89);
						((ClistContext)_localctx).pt = hotype();

										parTypes.add(((ClistContext)_localctx).pt.ast);
										ParNode mpar = new ParNode((((ClistContext)_localctx).pid!=null?((ClistContext)_localctx).pid.getText():null),((ClistContext)_localctx).pt.ast);											// creo un nodo ParNode
										m.addPar(mpar);																			// lo collego al MethodNode con addPar
						                parOffset = (((ClistContext)_localctx).pt.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
						                // aggiungo la dichiarazione nell'hashmap del livello corrente
										if (hmm.put((((ClistContext)_localctx).pid!=null?((ClistContext)_localctx).pid.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).pt.ast,parOffset,false)) != null)
										{
											System.out.println("Parameter id "+(((ClistContext)_localctx).pid!=null?((ClistContext)_localctx).pid.getText():null)+" at line "+(((ClistContext)_localctx).pid!=null?((ClistContext)_localctx).pid.getLine():0)+" already declared");
											System.exit(0);
										}
									
						setState(99);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(91);
							match(COMMA);
							setState(92);
							((ClistContext)_localctx).pi = match(ID);
							setState(93);
							match(COLON);
							setState(94);
							((ClistContext)_localctx).pty = hotype();

											parTypes.add(((ClistContext)_localctx).pty.ast);
											ParNode par = new ParNode((((ClistContext)_localctx).pi!=null?((ClistContext)_localctx).pi.getText():null),((ClistContext)_localctx).pty.ast);
											m.addPar(par);
											parOffset = (((ClistContext)_localctx).pty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
											// aggiungo la dichiarazione nell'hashmap del livello corrente
											if (hmm.put((((ClistContext)_localctx).pi!=null?((ClistContext)_localctx).pi.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).pty.ast,parOffset,false)) != null)
											{
												System.out.println("Parameter id "+(((ClistContext)_localctx).pi!=null?((ClistContext)_localctx).pi.getText():null)+" at line "+(((ClistContext)_localctx).pi!=null?((ClistContext)_localctx).pi.getLine():0)+" already declared");
												System.exit(0);
											}
										
							}
							}
							setState(101);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(104);
					match(RPAR);

									ArrowTypeNode symType = new ArrowTypeNode(parTypes,((ClistContext)_localctx).mty.ast);
									entry.addType(symType);
									// memorizzo in MethodNode il tipo messo in symbol table
									m.addSymType(symType);
								
					setState(123);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(106);
						match(LET);
						int varOffset=-2;
						setState(119);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(108);
							match(VAR);
							setState(109);
							((ClistContext)_localctx).vi = match(ID);
							setState(110);
							match(COLON);
							setState(111);
							((ClistContext)_localctx).vty = type();
							setState(112);
							match(ASS);
							setState(113);
							((ClistContext)_localctx).ve = exp();

											VarNode v = new VarNode((((ClistContext)_localctx).vi!=null?((ClistContext)_localctx).vi.getText():null),((ClistContext)_localctx).vty.ast,((ClistContext)_localctx).ve.ast);
											// memorizzo la dichiarazione interna al metodo
											m.addDec(v);
											// aggiungo la dichiarazione nell'hashmap del livello corrente
											if (hmm.put((((ClistContext)_localctx).vi!=null?((ClistContext)_localctx).vi.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).vty.ast,varOffset--,false)) != null)
											{
												System.out.println("Var id "+(((ClistContext)_localctx).vi!=null?((ClistContext)_localctx).vi.getText():null)+" at line "+(((ClistContext)_localctx).vi!=null?((ClistContext)_localctx).vi.getLine():0)+" already declared");
												System.exit(0);
											}
										
							setState(115);
							match(SEMIC);
							}
							}
							setState(121);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(122);
						match(IN);
						}
					}

					setState(125);
					((ClistContext)_localctx).me = exp();

									m.addBody(((ClistContext)_localctx).me.ast);
									// rimuovo la hashmap corrente poich� esco dallo scope del metodo
									symTable.remove(nestingLevel--);
								
					setState(127);
					match(SEMIC);
					}
					}
					setState(133);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(134);
				match(CRPAR);

							// memorizzo in ClassNode il tipo messo in symbol table
							c.addSymType(ct);
							// rimuovo la hashmap corrente poich� esco dallo scope della classe
							symTable.remove(nestingLevel--);
						
				}
				}
				setState(138); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CLASS );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DeclistContext extends ParserRuleContext {
		public ArrayList<Node> astlist;
		public Token i;
		public HotypeContext ht;
		public ExpContext e;
		public TypeContext t;
		public Token fid;
		public HotypeContext fty;
		public Token id;
		public HotypeContext ty;
		public DeclistContext d;
		public List<TerminalNode> SEMIC() { return getTokens(FOOLParser.SEMIC); }
		public TerminalNode SEMIC(int i) {
			return getToken(FOOLParser.SEMIC, i);
		}
		public List<TerminalNode> VAR() { return getTokens(FOOLParser.VAR); }
		public TerminalNode VAR(int i) {
			return getToken(FOOLParser.VAR, i);
		}
		public List<TerminalNode> COLON() { return getTokens(FOOLParser.COLON); }
		public TerminalNode COLON(int i) {
			return getToken(FOOLParser.COLON, i);
		}
		public List<TerminalNode> ASS() { return getTokens(FOOLParser.ASS); }
		public TerminalNode ASS(int i) {
			return getToken(FOOLParser.ASS, i);
		}
		public List<TerminalNode> FUN() { return getTokens(FOOLParser.FUN); }
		public TerminalNode FUN(int i) {
			return getToken(FOOLParser.FUN, i);
		}
		public List<TerminalNode> LPAR() { return getTokens(FOOLParser.LPAR); }
		public TerminalNode LPAR(int i) {
			return getToken(FOOLParser.LPAR, i);
		}
		public List<TerminalNode> RPAR() { return getTokens(FOOLParser.RPAR); }
		public TerminalNode RPAR(int i) {
			return getToken(FOOLParser.RPAR, i);
		}
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TypeContext> type() {
			return getRuleContexts(TypeContext.class);
		}
		public TypeContext type(int i) {
			return getRuleContext(TypeContext.class,i);
		}
		public List<TerminalNode> LET() { return getTokens(FOOLParser.LET); }
		public TerminalNode LET(int i) {
			return getToken(FOOLParser.LET, i);
		}
		public List<TerminalNode> IN() { return getTokens(FOOLParser.IN); }
		public TerminalNode IN(int i) {
			return getToken(FOOLParser.IN, i);
		}
		public List<DeclistContext> declist() {
			return getRuleContexts(DeclistContext.class);
		}
		public DeclistContext declist(int i) {
			return getRuleContext(DeclistContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public DeclistContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_declist; }
	}

	public final DeclistContext declist() throws RecognitionException {
		DeclistContext _localctx = new DeclistContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_declist);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			((DeclistContext)_localctx).astlist =  new ArrayList<Node>();
				   int offset = (nestingLevel==0)?offsetNlZero:-2;
			setState(189); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(185);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(141);
					match(VAR);
					setState(142);
					((DeclistContext)_localctx).i = match(ID);
					setState(143);
					match(COLON);
					setState(144);
					((DeclistContext)_localctx).ht = hotype();
					setState(145);
					match(ASS);
					setState(146);
					((DeclistContext)_localctx).e = exp();

					            	VarNode v = new VarNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).ht.ast,((DeclistContext)_localctx).e.ast);
					            	_localctx.astlist.add(v);
					            	HashMap<String,STentry> hm = symTable.get(nestingLevel);
					            	if (hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).ht.ast,offset,false)) != null)
					            	{
					            		System.out.println("Var id "+(((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null)+" at line "+(((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0)+" already declared");
					            		System.exit(0);
					            	}
					            	offset = (((DeclistContext)_localctx).ht.ast instanceof ArrowTypeNode) ? (offset - 2) : (offset - 1);
					            
					}
					break;
				case FUN:
					{
					setState(149);
					match(FUN);
					setState(150);
					((DeclistContext)_localctx).i = match(ID);
					setState(151);
					match(COLON);
					setState(152);
					((DeclistContext)_localctx).t = type();

					            	// inserisco ID nella symbol table
					            	FunNode f = new FunNode((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),((DeclistContext)_localctx).t.ast);
					            	_localctx.astlist.add(f);
					            	HashMap<String,STentry> hm = symTable.get(nestingLevel);
					            	STentry entry = new STentry(nestingLevel,offset,false);
					            	offset-=2;
					            	if (hm.put((((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null),entry) != null)
					            	{
					            		System.out.println("Fun id "+(((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getText():null)+" at line "+(((DeclistContext)_localctx).i!=null?((DeclistContext)_localctx).i.getLine():0)+" already declared");
					            		System.exit(0);
					            	}
					                // creo una nuova hashmap per la symTable
					                nestingLevel++;
					                HashMap<String,STentry> hmn = new HashMap<String,STentry>();
					                symTable.add(hmn);
								
					setState(154);
					match(LPAR);
					ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset=0;
					setState(171);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(156);
						((DeclistContext)_localctx).fid = match(ID);
						setState(157);
						match(COLON);
						setState(158);
						((DeclistContext)_localctx).fty = hotype();

										parTypes.add(((DeclistContext)_localctx).fty.ast);
										ParNode fpar = new ParNode((((DeclistContext)_localctx).fid!=null?((DeclistContext)_localctx).fid.getText():null),((DeclistContext)_localctx).fty.ast);											// creo nodo ParNode
										f.addPar(fpar);																			// lo collego al FunNode con addPar
						                parOffset = (((DeclistContext)_localctx).fty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
										if (hmn.put((((DeclistContext)_localctx).fid!=null?((DeclistContext)_localctx).fid.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).fty.ast,parOffset,false)) != null)		// aggiungo dich a hmn
										{
											System.out.println("Parameter id "+(((DeclistContext)_localctx).fid!=null?((DeclistContext)_localctx).fid.getText():null)+" at line "+(((DeclistContext)_localctx).fid!=null?((DeclistContext)_localctx).fid.getLine():0)+" already declared");
											System.exit(0);
										}
									
						setState(168);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(160);
							match(COMMA);
							setState(161);
							((DeclistContext)_localctx).id = match(ID);
							setState(162);
							match(COLON);
							setState(163);
							((DeclistContext)_localctx).ty = hotype();

											parTypes.add(((DeclistContext)_localctx).ty.ast);
											ParNode par = new ParNode((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),((DeclistContext)_localctx).ty.ast);
											f.addPar(par);
											parOffset = (((DeclistContext)_localctx).ty.ast instanceof ArrowTypeNode) ? (parOffset + 2) : (parOffset + 1);
											if (hmn.put((((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null),new STentry(nestingLevel,((DeclistContext)_localctx).ty.ast,parOffset,false)) != null)
											{
												System.out.println("Parameter id "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getText():null)+" at line "+(((DeclistContext)_localctx).id!=null?((DeclistContext)_localctx).id.getLine():0)+" already declared");
												System.exit(0);
											}
										
							}
							}
							setState(170);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(173);
					match(RPAR);

									ArrowTypeNode symType = new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast);
									entry.addType(symType);
									f.addSymType(symType);
								
					setState(180);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(175);
						match(LET);
						setState(176);
						((DeclistContext)_localctx).d = declist();
						setState(177);
						match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(182);
					((DeclistContext)_localctx).e = exp();

									f.addBody(((DeclistContext)_localctx).e.ast);
									// rimuovo la hashmap corrente poich� esco dallo scope
									symTable.remove(nestingLevel--);
								
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(187);
				match(SEMIC);
				}
				}
				setState(191); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==VAR || _la==FUN );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public Node ast;
		public TermContext f;
		public TermContext l;
		public List<TermContext> term() {
			return getRuleContexts(TermContext.class);
		}
		public TermContext term(int i) {
			return getRuleContext(TermContext.class,i);
		}
		public List<TerminalNode> PLUS() { return getTokens(FOOLParser.PLUS); }
		public TerminalNode PLUS(int i) {
			return getToken(FOOLParser.PLUS, i);
		}
		public List<TerminalNode> MINUS() { return getTokens(FOOLParser.MINUS); }
		public TerminalNode MINUS(int i) {
			return getToken(FOOLParser.MINUS, i);
		}
		public List<TerminalNode> OR() { return getTokens(FOOLParser.OR); }
		public TerminalNode OR(int i) {
			return getToken(FOOLParser.OR, i);
		}
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exp);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(193);
			((ExpContext)_localctx).f = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).f.ast;
			setState(209);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << PLUS) | (1L << MINUS))) != 0)) {
				{
				setState(207);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(195);
					match(PLUS);
					setState(196);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				case MINUS:
					{
					setState(199);
					match(MINUS);
					setState(200);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				case OR:
					{
					setState(203);
					match(OR);
					setState(204);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(211);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TermContext extends ParserRuleContext {
		public Node ast;
		public FactorContext f;
		public FactorContext l;
		public List<FactorContext> factor() {
			return getRuleContexts(FactorContext.class);
		}
		public FactorContext factor(int i) {
			return getRuleContext(FactorContext.class,i);
		}
		public List<TerminalNode> TIMES() { return getTokens(FOOLParser.TIMES); }
		public TerminalNode TIMES(int i) {
			return getToken(FOOLParser.TIMES, i);
		}
		public List<TerminalNode> DIV() { return getTokens(FOOLParser.DIV); }
		public TerminalNode DIV(int i) {
			return getToken(FOOLParser.DIV, i);
		}
		public List<TerminalNode> AND() { return getTokens(FOOLParser.AND); }
		public TerminalNode AND(int i) {
			return getToken(FOOLParser.AND, i);
		}
		public TermContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_term; }
	}

	public final TermContext term() throws RecognitionException {
		TermContext _localctx = new TermContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_term);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(212);
			((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(228);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << TIMES) | (1L << DIV))) != 0)) {
				{
				setState(226);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(214);
					match(TIMES);
					setState(215);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new MultNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				case DIV:
					{
					setState(218);
					match(DIV);
					setState(219);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				case AND:
					{
					setState(222);
					match(AND);
					setState(223);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(230);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FactorContext extends ParserRuleContext {
		public Node ast;
		public ValueContext f;
		public ValueContext l;
		public List<ValueContext> value() {
			return getRuleContexts(ValueContext.class);
		}
		public ValueContext value(int i) {
			return getRuleContext(ValueContext.class,i);
		}
		public List<TerminalNode> EQ() { return getTokens(FOOLParser.EQ); }
		public TerminalNode EQ(int i) {
			return getToken(FOOLParser.EQ, i);
		}
		public List<TerminalNode> GE() { return getTokens(FOOLParser.GE); }
		public TerminalNode GE(int i) {
			return getToken(FOOLParser.GE, i);
		}
		public List<TerminalNode> LE() { return getTokens(FOOLParser.LE); }
		public TerminalNode LE(int i) {
			return getToken(FOOLParser.LE, i);
		}
		public FactorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_factor; }
	}

	public final FactorContext factor() throws RecognitionException {
		FactorContext _localctx = new FactorContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_factor);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			((FactorContext)_localctx).f = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).f.ast;
			setState(247);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << GE) | (1L << LE))) != 0)) {
				{
				setState(245);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(233);
					match(EQ);
					setState(234);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				case GE:
					{
					setState(237);
					match(GE);
					setState(238);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new GreaterOrEqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				case LE:
					{
					setState(241);
					match(LE);
					setState(242);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LessOrEqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ValueContext extends ParserRuleContext {
		public Node ast;
		public Token n;
		public Token i;
		public ExpContext a;
		public ExpContext e;
		public ExpContext x;
		public ExpContext y;
		public ExpContext z;
		public Token mi;
		public TerminalNode INTEGER() { return getToken(FOOLParser.INTEGER, 0); }
		public TerminalNode TRUE() { return getToken(FOOLParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(FOOLParser.FALSE, 0); }
		public TerminalNode NULL() { return getToken(FOOLParser.NULL, 0); }
		public TerminalNode NEW() { return getToken(FOOLParser.NEW, 0); }
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public List<TerminalNode> ID() { return getTokens(FOOLParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(FOOLParser.ID, i);
		}
		public List<ExpContext> exp() {
			return getRuleContexts(ExpContext.class);
		}
		public ExpContext exp(int i) {
			return getRuleContext(ExpContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public TerminalNode IF() { return getToken(FOOLParser.IF, 0); }
		public TerminalNode THEN() { return getToken(FOOLParser.THEN, 0); }
		public List<TerminalNode> CLPAR() { return getTokens(FOOLParser.CLPAR); }
		public TerminalNode CLPAR(int i) {
			return getToken(FOOLParser.CLPAR, i);
		}
		public List<TerminalNode> CRPAR() { return getTokens(FOOLParser.CRPAR); }
		public TerminalNode CRPAR(int i) {
			return getToken(FOOLParser.CRPAR, i);
		}
		public TerminalNode ELSE() { return getToken(FOOLParser.ELSE, 0); }
		public TerminalNode NOT() { return getToken(FOOLParser.NOT, 0); }
		public TerminalNode PRINT() { return getToken(FOOLParser.PRINT, 0); }
		public TerminalNode DOT() { return getToken(FOOLParser.DOT, 0); }
		public ValueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_value; }
	}

	public final ValueContext value() throws RecognitionException {
		ValueContext _localctx = new ValueContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_value);
		int _la;
		try {
			setState(348);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(250);
				((ValueContext)_localctx).n = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(252);
				match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(254);
				match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(256);
				match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(258);
				match(NEW);
				setState(259);
				((ValueContext)_localctx).i = match(ID);

							STentry entry = null;
							if (classTable.containsKey((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null))) {
								/*
								 * Trattandosi di una classe, ricerco la STentry direttamente
								 * dal livello 0 della symbol table.
								 */
								entry = symTable.get(0).get((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
							} else {
								System.out.println("Id "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)+" at line "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0)+" is not a class");
							 	System.exit(0);
							}
						
				setState(261);
				match(LPAR);
				ArrayList<Node> arglist = new ArrayList<Node>();
				setState(274);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
					{
					setState(263);
					((ValueContext)_localctx).a = exp();
					arglist.add(((ValueContext)_localctx).a.ast);
					setState(271);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(265);
						match(COMMA);
						setState(266);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						}
						}
						setState(273);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(276);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NewNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(278);
				match(LPAR);
				setState(279);
				((ValueContext)_localctx).e = exp();
				setState(280);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(283);
				match(IF);
				setState(284);
				((ValueContext)_localctx).x = exp();
				setState(285);
				match(THEN);
				setState(286);
				match(CLPAR);
				setState(287);
				((ValueContext)_localctx).y = exp();
				setState(288);
				match(CRPAR);
				setState(289);
				match(ELSE);
				setState(290);
				match(CLPAR);
				setState(291);
				((ValueContext)_localctx).z = exp();
				setState(292);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).x.ast,((ValueContext)_localctx).y.ast,((ValueContext)_localctx).z.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(295);
				match(NOT);
				setState(296);
				match(LPAR);
				setState(297);
				((ValueContext)_localctx).e = exp();
				setState(298);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(301);
				match(PRINT);
				setState(302);
				match(LPAR);
				setState(303);
				((ValueContext)_localctx).e = exp();
				setState(304);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(307);
				((ValueContext)_localctx).i = match(ID);

							// ricerca della dichiarazione
							int j = nestingLevel;
							STentry entry = null;
							while (j >= 0 && entry == null)
								entry = (symTable.get(j--)).get((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
							if (entry == null) {
								System.out.println("Id "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)+" at line "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0)+" not declared");
							 	System.exit(0);
							}
							((ValueContext)_localctx).ast =  new IdNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,nestingLevel);
						
				setState(346);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(309);
					match(LPAR);
					ArrayList<Node> arglist = new ArrayList<Node>();
					setState(322);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
						{
						setState(311);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						setState(319);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(313);
							match(COMMA);
							setState(314);
							((ValueContext)_localctx).a = exp();
							arglist.add(((ValueContext)_localctx).a.ast);
							}
							}
							setState(321);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(324);
					match(RPAR);
					((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel);
					}
					break;
				case DOT:
					{
					setState(326);
					match(DOT);
					setState(327);
					((ValueContext)_localctx).mi = match(ID);

						   				HashMap<String,STentry> vt = null;
						   				// controllo che il tipo dentro STentry di ID1 sia un RefTypeNode
						   				if (entry.getType() instanceof RefTypeNode) {
						   					// cerco la virtual table della classe del tipo RefTypeNode di ID1
						   					vt = classTable.get(((RefTypeNode)entry.getType()).getClassId());
						   					if (!vt.containsKey((((ValueContext)_localctx).mi!=null?((ValueContext)_localctx).mi.getText():null))) {
						   						System.out.println("Method "+(((ValueContext)_localctx).mi!=null?((ValueContext)_localctx).mi.getText():null)+" at line "+(((ValueContext)_localctx).mi!=null?((ValueContext)_localctx).mi.getLine():0)+" is not defined by the class of object "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null));
						   						System.exit(0);
						   					}
						   				} else {
						   					System.out.println("Id "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null)+" at line "+(((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getLine():0)+" is not RefType");
						   					System.exit(0);
						   				}
						   			
					setState(329);
					match(LPAR);
					ArrayList<Node> arglist = new ArrayList<Node>();
					setState(342);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
						{
						setState(331);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						setState(339);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(333);
							match(COMMA);
							setState(334);
							((ValueContext)_localctx).a = exp();
							arglist.add(((ValueContext)_localctx).a.ast);
							}
							}
							setState(341);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(344);
					match(RPAR);
					((ValueContext)_localctx).ast =  new ClassCallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),(((ValueContext)_localctx).mi!=null?((ValueContext)_localctx).mi.getText():null),entry,vt.get((((ValueContext)_localctx).mi!=null?((ValueContext)_localctx).mi.getText():null)),arglist,nestingLevel);
					}
					break;
				case COMMA:
				case SEMIC:
				case EQ:
				case GE:
				case LE:
				case OR:
				case AND:
				case PLUS:
				case MINUS:
				case TIMES:
				case DIV:
				case RPAR:
				case CRPAR:
				case THEN:
					break;
				default:
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class HotypeContext extends ParserRuleContext {
		public Node ast;
		public TypeContext f;
		public ArrowContext l;
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public ArrowContext arrow() {
			return getRuleContext(ArrowContext.class,0);
		}
		public HotypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_hotype; }
	}

	public final HotypeContext hotype() throws RecognitionException {
		HotypeContext _localctx = new HotypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_hotype);
		try {
			setState(356);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(350);
				((HotypeContext)_localctx).f = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).f.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(353);
				((HotypeContext)_localctx).l = arrow();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).l.ast;
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public Node ast;
		public Token c;
		public TerminalNode INT() { return getToken(FOOLParser.INT, 0); }
		public TerminalNode BOOL() { return getToken(FOOLParser.BOOL, 0); }
		public TerminalNode ID() { return getToken(FOOLParser.ID, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_type);
		try {
			setState(364);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(358);
				match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(360);
				match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(362);
				((TypeContext)_localctx).c = match(ID);
				((TypeContext)_localctx).ast =  new RefTypeNode((((TypeContext)_localctx).c!=null?((TypeContext)_localctx).c.getText():null));
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ArrowContext extends ParserRuleContext {
		public Node ast;
		public HotypeContext a;
		public TypeContext f;
		public TerminalNode LPAR() { return getToken(FOOLParser.LPAR, 0); }
		public TerminalNode RPAR() { return getToken(FOOLParser.RPAR, 0); }
		public TerminalNode ARROW() { return getToken(FOOLParser.ARROW, 0); }
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public List<HotypeContext> hotype() {
			return getRuleContexts(HotypeContext.class);
		}
		public HotypeContext hotype(int i) {
			return getRuleContext(HotypeContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FOOLParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FOOLParser.COMMA, i);
		}
		public ArrowContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrow; }
	}

	public final ArrowContext arrow() throws RecognitionException {
		ArrowContext _localctx = new ArrowContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrow);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			match(LPAR);
			ArrayList<Node> arglist = new ArrayList<Node>();
			setState(379);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(368);
				((ArrowContext)_localctx).a = hotype();
				arglist.add(((ArrowContext)_localctx).a.ast);
				setState(376);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(370);
					match(COMMA);
					setState(371);
					((ArrowContext)_localctx).a = hotype();
					arglist.add(((ArrowContext)_localctx).a.ast);
					}
					}
					setState(378);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(381);
			match(RPAR);
			setState(382);
			match(ARROW);
			setState(383);
			((ArrowContext)_localctx).f = type();
			((ArrowContext)_localctx).ast =  new ArrowTypeNode(arglist,((ArrowContext)_localctx).f.ast);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u0185\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2!\n\2\3\2\3\2\3\2\3\2\3"+
		"\2\5\2(\n\2\3\2\3\2\3\2\3\2\5\2.\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\5\3:\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7"+
		"\3I\n\3\f\3\16\3L\13\3\5\3N\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3d\n\3\f\3\16\3g\13\3\5\3"+
		"i\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3x\n\3\f\3"+
		"\16\3{\13\3\3\3\5\3~\n\3\3\3\3\3\3\3\3\3\7\3\u0084\n\3\f\3\16\3\u0087"+
		"\13\3\3\3\3\3\6\3\u008b\n\3\r\3\16\3\u008c\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\7\4\u00a9\n\4\f\4\16\4\u00ac\13\4\5\4\u00ae\n\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\5\4\u00b7\n\4\3\4\3\4\3\4\5\4\u00bc\n\4\3\4\3\4\6\4\u00c0"+
		"\n\4\r\4\16\4\u00c1\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\7\5\u00d2\n\5\f\5\16\5\u00d5\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\7\6\u00e5\n\6\f\6\16\6\u00e8\13\6\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00f8\n\7\f\7\16\7"+
		"\u00fb\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\7\b\u0110\n\b\f\b\16\b\u0113\13\b\5\b\u0115\n\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\7\b\u0140\n\b\f\b\16\b\u0143\13\b\5\b\u0145\n\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0154\n\b\f\b\16"+
		"\b\u0157\13\b\5\b\u0159\n\b\3\b\3\b\5\b\u015d\n\b\5\b\u015f\n\b\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\5\t\u0167\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u016f\n\n"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0179\n\13\f\13\16\13\u017c"+
		"\13\13\5\13\u017e\n\13\3\13\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f"+
		"\16\20\22\24\2\2\2\u01aa\2\26\3\2\2\2\4\62\3\2\2\2\6\u008e\3\2\2\2\b\u00c3"+
		"\3\2\2\2\n\u00d6\3\2\2\2\f\u00e9\3\2\2\2\16\u015e\3\2\2\2\20\u0166\3\2"+
		"\2\2\22\u016e\3\2\2\2\24\u0170\3\2\2\2\26-\b\2\1\2\27\30\5\b\5\2\30\31"+
		"\b\2\1\2\31.\3\2\2\2\32\33\7\35\2\2\33\'\b\2\1\2\34 \5\4\3\2\35\36\5\6"+
		"\4\2\36\37\b\2\1\2\37!\3\2\2\2 \35\3\2\2\2 !\3\2\2\2!\"\3\2\2\2\"#\b\2"+
		"\1\2#(\3\2\2\2$%\5\6\4\2%&\b\2\1\2&(\3\2\2\2\'\34\3\2\2\2\'$\3\2\2\2("+
		")\3\2\2\2)*\7\36\2\2*+\5\b\5\2+,\b\2\1\2,.\3\2\2\2-\27\3\2\2\2-\32\3\2"+
		"\2\2./\3\2\2\2/\60\b\2\1\2\60\61\7\7\2\2\61\3\3\2\2\2\62\u008a\b\3\1\2"+
		"\63\64\b\3\1\2\64\65\7!\2\2\659\7(\2\2\66\67\7\"\2\2\678\7(\2\28:\b\3"+
		"\1\29\66\3\2\2\29:\3\2\2\2:;\3\2\2\2;<\b\3\1\2<=\7\25\2\2=M\b\3\1\2>?"+
		"\7(\2\2?@\7\3\2\2@A\5\22\n\2AJ\b\3\1\2BC\7\4\2\2CD\7(\2\2DE\7\3\2\2EF"+
		"\5\22\n\2FG\b\3\1\2GI\3\2\2\2HB\3\2\2\2IL\3\2\2\2JH\3\2\2\2JK\3\2\2\2"+
		"KN\3\2\2\2LJ\3\2\2\2M>\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\26\2\2PQ\7\27\2"+
		"\2Q\u0085\b\3\1\2RS\7 \2\2ST\7(\2\2TU\7\3\2\2UV\5\22\n\2VW\b\3\1\2WX\7"+
		"\25\2\2Xh\b\3\1\2YZ\7(\2\2Z[\7\3\2\2[\\\5\20\t\2\\e\b\3\1\2]^\7\4\2\2"+
		"^_\7(\2\2_`\7\3\2\2`a\5\20\t\2ab\b\3\1\2bd\3\2\2\2c]\3\2\2\2dg\3\2\2\2"+
		"ec\3\2\2\2ef\3\2\2\2fi\3\2\2\2ge\3\2\2\2hY\3\2\2\2hi\3\2\2\2ij\3\2\2\2"+
		"jk\7\26\2\2k}\b\3\1\2lm\7\35\2\2my\b\3\1\2no\7\37\2\2op\7(\2\2pq\7\3\2"+
		"\2qr\5\22\n\2rs\7\6\2\2st\5\b\5\2tu\b\3\1\2uv\7\7\2\2vx\3\2\2\2wn\3\2"+
		"\2\2x{\3\2\2\2yw\3\2\2\2yz\3\2\2\2z|\3\2\2\2{y\3\2\2\2|~\7\36\2\2}l\3"+
		"\2\2\2}~\3\2\2\2~\177\3\2\2\2\177\u0080\5\b\5\2\u0080\u0081\b\3\1\2\u0081"+
		"\u0082\7\7\2\2\u0082\u0084\3\2\2\2\u0083R\3\2\2\2\u0084\u0087\3\2\2\2"+
		"\u0085\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0088\3\2\2\2\u0087\u0085"+
		"\3\2\2\2\u0088\u0089\7\30\2\2\u0089\u008b\b\3\1\2\u008a\63\3\2\2\2\u008b"+
		"\u008c\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d\5\3\2\2\2"+
		"\u008e\u00bf\b\4\1\2\u008f\u0090\7\37\2\2\u0090\u0091\7(\2\2\u0091\u0092"+
		"\7\3\2\2\u0092\u0093\5\20\t\2\u0093\u0094\7\6\2\2\u0094\u0095\5\b\5\2"+
		"\u0095\u0096\b\4\1\2\u0096\u00bc\3\2\2\2\u0097\u0098\7 \2\2\u0098\u0099"+
		"\7(\2\2\u0099\u009a\7\3\2\2\u009a\u009b\5\22\n\2\u009b\u009c\b\4\1\2\u009c"+
		"\u009d\7\25\2\2\u009d\u00ad\b\4\1\2\u009e\u009f\7(\2\2\u009f\u00a0\7\3"+
		"\2\2\u00a0\u00a1\5\20\t\2\u00a1\u00aa\b\4\1\2\u00a2\u00a3\7\4\2\2\u00a3"+
		"\u00a4\7(\2\2\u00a4\u00a5\7\3\2\2\u00a5\u00a6\5\20\t\2\u00a6\u00a7\b\4"+
		"\1\2\u00a7\u00a9\3\2\2\2\u00a8\u00a2\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa"+
		"\u00a8\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00aa\3\2"+
		"\2\2\u00ad\u009e\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\u00af\3\2\2\2\u00af"+
		"\u00b0\7\26\2\2\u00b0\u00b6\b\4\1\2\u00b1\u00b2\7\35\2\2\u00b2\u00b3\5"+
		"\6\4\2\u00b3\u00b4\7\36\2\2\u00b4\u00b5\b\4\1\2\u00b5\u00b7\3\2\2\2\u00b6"+
		"\u00b1\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\5\b"+
		"\5\2\u00b9\u00ba\b\4\1\2\u00ba\u00bc\3\2\2\2\u00bb\u008f\3\2\2\2\u00bb"+
		"\u0097\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\7\7\2\2\u00be\u00c0\3\2"+
		"\2\2\u00bf\u00bb\3\2\2\2\u00c0\u00c1\3\2\2\2\u00c1\u00bf\3\2\2\2\u00c1"+
		"\u00c2\3\2\2\2\u00c2\7\3\2\2\2\u00c3\u00c4\5\n\6\2\u00c4\u00d3\b\5\1\2"+
		"\u00c5\u00c6\7\16\2\2\u00c6\u00c7\5\n\6\2\u00c7\u00c8\b\5\1\2\u00c8\u00d2"+
		"\3\2\2\2\u00c9\u00ca\7\17\2\2\u00ca\u00cb\5\n\6\2\u00cb\u00cc\b\5\1\2"+
		"\u00cc\u00d2\3\2\2\2\u00cd\u00ce\7\13\2\2\u00ce\u00cf\5\n\6\2\u00cf\u00d0"+
		"\b\5\1\2\u00d0\u00d2\3\2\2\2\u00d1\u00c5\3\2\2\2\u00d1\u00c9\3\2\2\2\u00d1"+
		"\u00cd\3\2\2\2\u00d2\u00d5\3\2\2\2\u00d3\u00d1\3\2\2\2\u00d3\u00d4\3\2"+
		"\2\2\u00d4\t\3\2\2\2\u00d5\u00d3\3\2\2\2\u00d6\u00d7\5\f\7\2\u00d7\u00e6"+
		"\b\6\1\2\u00d8\u00d9\7\20\2\2\u00d9\u00da\5\f\7\2\u00da\u00db\b\6\1\2"+
		"\u00db\u00e5\3\2\2\2\u00dc\u00dd\7\21\2\2\u00dd\u00de\5\f\7\2\u00de\u00df"+
		"\b\6\1\2\u00df\u00e5\3\2\2\2\u00e0\u00e1\7\f\2\2\u00e1\u00e2\5\f\7\2\u00e2"+
		"\u00e3\b\6\1\2\u00e3\u00e5\3\2\2\2\u00e4\u00d8\3\2\2\2\u00e4\u00dc\3\2"+
		"\2\2\u00e4\u00e0\3\2\2\2\u00e5\u00e8\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\13\3\2\2\2\u00e8\u00e6\3\2\2\2\u00e9\u00ea\5\16\b"+
		"\2\u00ea\u00f9\b\7\1\2\u00eb\u00ec\7\b\2\2\u00ec\u00ed\5\16\b\2\u00ed"+
		"\u00ee\b\7\1\2\u00ee\u00f8\3\2\2\2\u00ef\u00f0\7\t\2\2\u00f0\u00f1\5\16"+
		"\b\2\u00f1\u00f2\b\7\1\2\u00f2\u00f8\3\2\2\2\u00f3\u00f4\7\n\2\2\u00f4"+
		"\u00f5\5\16\b\2\u00f5\u00f6\b\7\1\2\u00f6\u00f8\3\2\2\2\u00f7\u00eb\3"+
		"\2\2\2\u00f7\u00ef\3\2\2\2\u00f7\u00f3\3\2\2\2\u00f8\u00fb\3\2\2\2\u00f9"+
		"\u00f7\3\2\2\2\u00f9\u00fa\3\2\2\2\u00fa\r\3\2\2\2\u00fb\u00f9\3\2\2\2"+
		"\u00fc\u00fd\7\22\2\2\u00fd\u015f\b\b\1\2\u00fe\u00ff\7\23\2\2\u00ff\u015f"+
		"\b\b\1\2\u0100\u0101\7\24\2\2\u0101\u015f\b\b\1\2\u0102\u0103\7$\2\2\u0103"+
		"\u015f\b\b\1\2\u0104\u0105\7#\2\2\u0105\u0106\7(\2\2\u0106\u0107\b\b\1"+
		"\2\u0107\u0108\7\25\2\2\u0108\u0114\b\b\1\2\u0109\u010a\5\b\5\2\u010a"+
		"\u0111\b\b\1\2\u010b\u010c\7\4\2\2\u010c\u010d\5\b\5\2\u010d\u010e\b\b"+
		"\1\2\u010e\u0110\3\2\2\2\u010f\u010b\3\2\2\2\u0110\u0113\3\2\2\2\u0111"+
		"\u010f\3\2\2\2\u0111\u0112\3\2\2\2\u0112\u0115\3\2\2\2\u0113\u0111\3\2"+
		"\2\2\u0114\u0109\3\2\2\2\u0114\u0115\3\2\2\2\u0115\u0116\3\2\2\2\u0116"+
		"\u0117\7\26\2\2\u0117\u015f\b\b\1\2\u0118\u0119\7\25\2\2\u0119\u011a\5"+
		"\b\5\2\u011a\u011b\7\26\2\2\u011b\u011c\b\b\1\2\u011c\u015f\3\2\2\2\u011d"+
		"\u011e\7\31\2\2\u011e\u011f\5\b\5\2\u011f\u0120\7\32\2\2\u0120\u0121\7"+
		"\27\2\2\u0121\u0122\5\b\5\2\u0122\u0123\7\30\2\2\u0123\u0124\7\33\2\2"+
		"\u0124\u0125\7\27\2\2\u0125\u0126\5\b\5\2\u0126\u0127\7\30\2\2\u0127\u0128"+
		"\b\b\1\2\u0128\u015f\3\2\2\2\u0129\u012a\7\r\2\2\u012a\u012b\7\25\2\2"+
		"\u012b\u012c\5\b\5\2\u012c\u012d\7\26\2\2\u012d\u012e\b\b\1\2\u012e\u015f"+
		"\3\2\2\2\u012f\u0130\7\34\2\2\u0130\u0131\7\25\2\2\u0131\u0132\5\b\5\2"+
		"\u0132\u0133\7\26\2\2\u0133\u0134\b\b\1\2\u0134\u015f\3\2\2\2\u0135\u0136"+
		"\7(\2\2\u0136\u015c\b\b\1\2\u0137\u0138\7\25\2\2\u0138\u0144\b\b\1\2\u0139"+
		"\u013a\5\b\5\2\u013a\u0141\b\b\1\2\u013b\u013c\7\4\2\2\u013c\u013d\5\b"+
		"\5\2\u013d\u013e\b\b\1\2\u013e\u0140\3\2\2\2\u013f\u013b\3\2\2\2\u0140"+
		"\u0143\3\2\2\2\u0141\u013f\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0145\3\2"+
		"\2\2\u0143\u0141\3\2\2\2\u0144\u0139\3\2\2\2\u0144\u0145\3\2\2\2\u0145"+
		"\u0146\3\2\2\2\u0146\u0147\7\26\2\2\u0147\u015d\b\b\1\2\u0148\u0149\7"+
		"\5\2\2\u0149\u014a\7(\2\2\u014a\u014b\b\b\1\2\u014b\u014c\7\25\2\2\u014c"+
		"\u0158\b\b\1\2\u014d\u014e\5\b\5\2\u014e\u0155\b\b\1\2\u014f\u0150\7\4"+
		"\2\2\u0150\u0151\5\b\5\2\u0151\u0152\b\b\1\2\u0152\u0154\3\2\2\2\u0153"+
		"\u014f\3\2\2\2\u0154\u0157\3\2\2\2\u0155\u0153\3\2\2\2\u0155\u0156\3\2"+
		"\2\2\u0156\u0159\3\2\2\2\u0157\u0155\3\2\2\2\u0158\u014d\3\2\2\2\u0158"+
		"\u0159\3\2\2\2\u0159\u015a\3\2\2\2\u015a\u015b\7\26\2\2\u015b\u015d\b"+
		"\b\1\2\u015c\u0137\3\2\2\2\u015c\u0148\3\2\2\2\u015c\u015d\3\2\2\2\u015d"+
		"\u015f\3\2\2\2\u015e\u00fc\3\2\2\2\u015e\u00fe\3\2\2\2\u015e\u0100\3\2"+
		"\2\2\u015e\u0102\3\2\2\2\u015e\u0104\3\2\2\2\u015e\u0118\3\2\2\2\u015e"+
		"\u011d\3\2\2\2\u015e\u0129\3\2\2\2\u015e\u012f\3\2\2\2\u015e\u0135\3\2"+
		"\2\2\u015f\17\3\2\2\2\u0160\u0161\5\22\n\2\u0161\u0162\b\t\1\2\u0162\u0167"+
		"\3\2\2\2\u0163\u0164\5\24\13\2\u0164\u0165\b\t\1\2\u0165\u0167\3\2\2\2"+
		"\u0166\u0160\3\2\2\2\u0166\u0163\3\2\2\2\u0167\21\3\2\2\2\u0168\u0169"+
		"\7%\2\2\u0169\u016f\b\n\1\2\u016a\u016b\7&\2\2\u016b\u016f\b\n\1\2\u016c"+
		"\u016d\7(\2\2\u016d\u016f\b\n\1\2\u016e\u0168\3\2\2\2\u016e\u016a\3\2"+
		"\2\2\u016e\u016c\3\2\2\2\u016f\23\3\2\2\2\u0170\u0171\7\25\2\2\u0171\u017d"+
		"\b\13\1\2\u0172\u0173\5\20\t\2\u0173\u017a\b\13\1\2\u0174\u0175\7\4\2"+
		"\2\u0175\u0176\5\20\t\2\u0176\u0177\b\13\1\2\u0177\u0179\3\2\2\2\u0178"+
		"\u0174\3\2\2\2\u0179\u017c\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2"+
		"\2\2\u017b\u017e\3\2\2\2\u017c\u017a\3\2\2\2\u017d\u0172\3\2\2\2\u017d"+
		"\u017e\3\2\2\2\u017e\u017f\3\2\2\2\u017f\u0180\7\26\2\2\u0180\u0181\7"+
		"\'\2\2\u0181\u0182\5\22\n\2\u0182\u0183\b\13\1\2\u0183\25\3\2\2\2% \'"+
		"-9JMehy}\u0085\u008c\u00aa\u00ad\u00b6\u00bb\u00c1\u00d1\u00d3\u00e4\u00e6"+
		"\u00f7\u00f9\u0111\u0114\u0141\u0144\u0155\u0158\u015c\u015e\u0166\u016e"+
		"\u017a\u017d";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}