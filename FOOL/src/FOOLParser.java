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
			setState(41);
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
				setState(35);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case CLASS:
					{
					setState(26);
					((ProgContext)_localctx).c = clist();
					setState(28);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==VAR || _la==FUN) {
						{
						setState(27);
						((ProgContext)_localctx).d = declist();
						}
					}


					      		cAndDecList.addAll(((ProgContext)_localctx).c.astlist); 
					        	cAndDecList.addAll(((ProgContext)_localctx).d.astlist);
					      	
					}
					break;
				case VAR:
				case FUN:
					{
					setState(32);
					((ProgContext)_localctx).d = declist();
					 cAndDecList.addAll(((ProgContext)_localctx).d.astlist); 
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(37);
				match(IN);
				setState(38);
				((ProgContext)_localctx).e = exp();

				        	((ProgContext)_localctx).ast =  new ProgLetInNode(cAndDecList,((ProgContext)_localctx).e.ast);
				        
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			symTable.remove(nestingLevel);
			setState(44);
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
			setState(134); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				boolean inheritance = false;
				setState(48);
				match(CLASS);
				setState(49);
				((ClistContext)_localctx).i = match(ID);
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==EXTENDS) {
					{
					setState(50);
					match(EXTENDS);
					setState(51);
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
						
				setState(56);
				match(LPAR);

							/*
							 * Se non si eredita, il contatore di offset per i campi � settato inizialmente in base
							 * al layout di oggetti: -1.
							 * Altrimenti, � impostato al primo offset libero in base alla lunghezza di allFields
							 * nel ClassTypeNode legato alla classe da cui si eredita.
							 */
							int fieldOffset = (inheritance) ?
								-((ClassTypeNode)(symTable.get(0).get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)).getType())).getAllFields().size()-1 : -1;
						
				setState(73);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==ID) {
					{
					setState(58);
					((ClistContext)_localctx).fid = match(ID);
					setState(59);
					match(COLON);
					setState(60);
					((ClistContext)_localctx).fty = type();

								if (!localDec.add((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null))) {
									System.out.println("Erroneous redefinition of the field "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null)+" at line "+(((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getLine():0));
									System.exit(0);
								}
								if (!vt.containsKey((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null))) {													// nuovo campo
									// aggiorno la Virtual Table
									vt.put((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).fty.ast,fieldOffset--,false));
									// aggiorno l'oggetto ClassTypeNode
									ct.addFieldType(((ClistContext)_localctx).fty.ast);
								} else {																			// overriding
									STentry tmpEntry = vt.get((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null));
									if (!tmpEntry.isMethod()) {
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
								c.addField(new FieldNode((((ClistContext)_localctx).fid!=null?((ClistContext)_localctx).fid.getText():null),((ClistContext)_localctx).fty.ast));
							
					setState(70);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(62);
						match(COMMA);
						setState(63);
						((ClistContext)_localctx).id = match(ID);
						setState(64);
						match(COLON);
						setState(65);
						((ClistContext)_localctx).ty = type();

									if (!localDec.add((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null))) {
										System.out.println("Erroneous redefinition of the field "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null)+" at line "+(((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getLine():0));
										System.exit(0);
									}
									if (!vt.containsKey((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null))) {													// nuovo campo
										// aggiorno la Virtual Table
										vt.put((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null),new STentry(nestingLevel,((ClistContext)_localctx).ty.ast,fieldOffset--,false));
										// aggiorno l'oggetto ClassTypeNode
										ct.addFieldType(((ClistContext)_localctx).ty.ast);
									} else {																			// overriding
										STentry tmpEntry = vt.get((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null));
										if (!tmpEntry.isMethod())
										{
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
									c.addField(new FieldNode((((ClistContext)_localctx).id!=null?((ClistContext)_localctx).id.getText():null),((ClistContext)_localctx).ty.ast));
								
						}
						}
						setState(72);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(75);
				match(RPAR);
				setState(76);
				match(CLPAR);

							/*
							 * Se non si eredita, il contatore di offset per i metodi � settato inizialmente in base
							 * al layout delle dispatch tables: 0.
							 * Altrimenti, � impostato al primo offset libero in base alla lunghezza di allMethods
							 * nel ClassTypeNode legato alla classe da cui si eredita.
							 */
							int methodOffset = (inheritance) ?
								((ClassTypeNode)(symTable.get(0).get((((ClistContext)_localctx).e!=null?((ClistContext)_localctx).e.getText():null)).getType())).getAllMethods().size() : 0;
						
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==FUN) {
					{
					{
					setState(78);
					match(FUN);
					setState(79);
					((ClistContext)_localctx).mi = match(ID);
					setState(80);
					match(COLON);
					setState(81);
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
								
					setState(83);
					match(LPAR);
					ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset=0;
					setState(100);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(85);
						((ClistContext)_localctx).pid = match(ID);
						setState(86);
						match(COLON);
						setState(87);
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
									
						setState(97);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(89);
							match(COMMA);
							setState(90);
							((ClistContext)_localctx).pi = match(ID);
							setState(91);
							match(COLON);
							setState(92);
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
							setState(99);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(102);
					match(RPAR);

									ArrowTypeNode symType = new ArrowTypeNode(parTypes,((ClistContext)_localctx).mty.ast);
									entry.addType(symType);
									// memorizzo in MethodNode il tipo messo in symbol table
									m.addSymType(symType);
								
					setState(121);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(104);
						match(LET);
						int varOffset=-2;
						setState(117);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==VAR) {
							{
							{
							setState(106);
							match(VAR);
							setState(107);
							((ClistContext)_localctx).vi = match(ID);
							setState(108);
							match(COLON);
							setState(109);
							((ClistContext)_localctx).vty = type();
							setState(110);
							match(ASS);
							setState(111);
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
										
							setState(113);
							match(SEMIC);
							}
							}
							setState(119);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						setState(120);
						match(IN);
						}
					}

					setState(123);
					((ClistContext)_localctx).me = exp();

									m.addBody(((ClistContext)_localctx).me.ast);
									// rimuovo la hashmap corrente poich� esco dallo scope del metodo
									symTable.remove(nestingLevel--);
								
					setState(125);
					match(SEMIC);
					}
					}
					setState(131);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(132);
				match(CRPAR);

							// memorizzo in ClassNode il tipo messo in symbol table
							c.addSymType(ct);
							// rimuovo la hashmap corrente poich� esco dallo scope della classe
							symTable.remove(nestingLevel--);
						
				}
				}
				setState(136); 
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
			setState(187); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case VAR:
					{
					setState(139);
					match(VAR);
					setState(140);
					((DeclistContext)_localctx).i = match(ID);
					setState(141);
					match(COLON);
					setState(142);
					((DeclistContext)_localctx).ht = hotype();
					setState(143);
					match(ASS);
					setState(144);
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
					setState(147);
					match(FUN);
					setState(148);
					((DeclistContext)_localctx).i = match(ID);
					setState(149);
					match(COLON);
					setState(150);
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
								
					setState(152);
					match(LPAR);
					ArrayList<Node> parTypes = new ArrayList<Node>();
									int parOffset=0;
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==ID) {
						{
						setState(154);
						((DeclistContext)_localctx).fid = match(ID);
						setState(155);
						match(COLON);
						setState(156);
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
									
						setState(166);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(158);
							match(COMMA);
							setState(159);
							((DeclistContext)_localctx).id = match(ID);
							setState(160);
							match(COLON);
							setState(161);
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
							setState(168);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(171);
					match(RPAR);

									ArrowTypeNode symType = new ArrowTypeNode(parTypes,((DeclistContext)_localctx).t.ast);
									entry.addType(symType);
									f.addSymType(symType);
								
					setState(178);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==LET) {
						{
						setState(173);
						match(LET);
						setState(174);
						((DeclistContext)_localctx).d = declist();
						setState(175);
						match(IN);
						f.addDec(((DeclistContext)_localctx).d.astlist);
						}
					}

					setState(180);
					((DeclistContext)_localctx).e = exp();

									f.addBody(((DeclistContext)_localctx).e.ast);
									// rimuovo la hashmap corrente poich� esco dallo scope
									symTable.remove(nestingLevel--);
								
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(185);
				match(SEMIC);
				}
				}
				setState(189); 
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
			setState(191);
			((ExpContext)_localctx).f = term();
			((ExpContext)_localctx).ast =  ((ExpContext)_localctx).f.ast;
			setState(207);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << OR) | (1L << PLUS) | (1L << MINUS))) != 0)) {
				{
				setState(205);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case PLUS:
					{
					setState(193);
					match(PLUS);
					setState(194);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new PlusNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				case MINUS:
					{
					setState(197);
					match(MINUS);
					setState(198);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new MinusNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				case OR:
					{
					setState(201);
					match(OR);
					setState(202);
					((ExpContext)_localctx).l = term();
					((ExpContext)_localctx).ast =  new OrNode(_localctx.ast,((ExpContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(209);
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
			setState(210);
			((TermContext)_localctx).f = factor();
			((TermContext)_localctx).ast =  ((TermContext)_localctx).f.ast;
			setState(226);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << AND) | (1L << TIMES) | (1L << DIV))) != 0)) {
				{
				setState(224);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case TIMES:
					{
					setState(212);
					match(TIMES);
					setState(213);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new MultNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				case DIV:
					{
					setState(216);
					match(DIV);
					setState(217);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new DivNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				case AND:
					{
					setState(220);
					match(AND);
					setState(221);
					((TermContext)_localctx).l = factor();
					((TermContext)_localctx).ast =  new AndNode(_localctx.ast,((TermContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(228);
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
			setState(229);
			((FactorContext)_localctx).f = value();
			((FactorContext)_localctx).ast =  ((FactorContext)_localctx).f.ast;
			setState(245);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << EQ) | (1L << GE) | (1L << LE))) != 0)) {
				{
				setState(243);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case EQ:
					{
					setState(231);
					match(EQ);
					setState(232);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new EqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				case GE:
					{
					setState(235);
					match(GE);
					setState(236);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new GreaterOrEqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				case LE:
					{
					setState(239);
					match(LE);
					setState(240);
					((FactorContext)_localctx).l = value();
					((FactorContext)_localctx).ast =  new LessOrEqualNode(_localctx.ast,((FactorContext)_localctx).l.ast);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(247);
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
			setState(346);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INTEGER:
				enterOuterAlt(_localctx, 1);
				{
				setState(248);
				((ValueContext)_localctx).n = match(INTEGER);
				((ValueContext)_localctx).ast =  new IntNode(Integer.parseInt((((ValueContext)_localctx).n!=null?((ValueContext)_localctx).n.getText():null)));
				}
				break;
			case TRUE:
				enterOuterAlt(_localctx, 2);
				{
				setState(250);
				match(TRUE);
				((ValueContext)_localctx).ast =  new BoolNode(true);
				}
				break;
			case FALSE:
				enterOuterAlt(_localctx, 3);
				{
				setState(252);
				match(FALSE);
				((ValueContext)_localctx).ast =  new BoolNode(false);
				}
				break;
			case NULL:
				enterOuterAlt(_localctx, 4);
				{
				setState(254);
				match(NULL);
				((ValueContext)_localctx).ast =  new EmptyNode();
				}
				break;
			case NEW:
				enterOuterAlt(_localctx, 5);
				{
				setState(256);
				match(NEW);
				setState(257);
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
						
				setState(259);
				match(LPAR);
				ArrayList<Node> arglist = new ArrayList<Node>();
				setState(272);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
					{
					setState(261);
					((ValueContext)_localctx).a = exp();
					arglist.add(((ValueContext)_localctx).a.ast);
					setState(269);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==COMMA) {
						{
						{
						setState(263);
						match(COMMA);
						setState(264);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						}
						}
						setState(271);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					}
				}

				setState(274);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NewNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel);
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 6);
				{
				setState(276);
				match(LPAR);
				setState(277);
				((ValueContext)_localctx).e = exp();
				setState(278);
				match(RPAR);
				((ValueContext)_localctx).ast =  ((ValueContext)_localctx).e.ast;
				}
				break;
			case IF:
				enterOuterAlt(_localctx, 7);
				{
				setState(281);
				match(IF);
				setState(282);
				((ValueContext)_localctx).x = exp();
				setState(283);
				match(THEN);
				setState(284);
				match(CLPAR);
				setState(285);
				((ValueContext)_localctx).y = exp();
				setState(286);
				match(CRPAR);
				setState(287);
				match(ELSE);
				setState(288);
				match(CLPAR);
				setState(289);
				((ValueContext)_localctx).z = exp();
				setState(290);
				match(CRPAR);
				((ValueContext)_localctx).ast =  new IfNode(((ValueContext)_localctx).x.ast,((ValueContext)_localctx).y.ast,((ValueContext)_localctx).z.ast);
				}
				break;
			case NOT:
				enterOuterAlt(_localctx, 8);
				{
				setState(293);
				match(NOT);
				setState(294);
				match(LPAR);
				setState(295);
				((ValueContext)_localctx).e = exp();
				setState(296);
				match(RPAR);
				((ValueContext)_localctx).ast =  new NotNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case PRINT:
				enterOuterAlt(_localctx, 9);
				{
				setState(299);
				match(PRINT);
				setState(300);
				match(LPAR);
				setState(301);
				((ValueContext)_localctx).e = exp();
				setState(302);
				match(RPAR);
				((ValueContext)_localctx).ast =  new PrintNode(((ValueContext)_localctx).e.ast);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 10);
				{
				setState(305);
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
						
				setState(344);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case LPAR:
					{
					setState(307);
					match(LPAR);
					ArrayList<Node> arglist = new ArrayList<Node>();
					setState(320);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
						{
						setState(309);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						setState(317);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(311);
							match(COMMA);
							setState(312);
							((ValueContext)_localctx).a = exp();
							arglist.add(((ValueContext)_localctx).a.ast);
							}
							}
							setState(319);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(322);
					match(RPAR);
					((ValueContext)_localctx).ast =  new CallNode((((ValueContext)_localctx).i!=null?((ValueContext)_localctx).i.getText():null),entry,arglist,nestingLevel);
					}
					break;
				case DOT:
					{
					setState(324);
					match(DOT);
					setState(325);
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
						   			
					setState(327);
					match(LPAR);
					ArrayList<Node> arglist = new ArrayList<Node>();
					setState(340);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << NOT) | (1L << INTEGER) | (1L << TRUE) | (1L << FALSE) | (1L << LPAR) | (1L << IF) | (1L << PRINT) | (1L << NEW) | (1L << NULL) | (1L << ID))) != 0)) {
						{
						setState(329);
						((ValueContext)_localctx).a = exp();
						arglist.add(((ValueContext)_localctx).a.ast);
						setState(337);
						_errHandler.sync(this);
						_la = _input.LA(1);
						while (_la==COMMA) {
							{
							{
							setState(331);
							match(COMMA);
							setState(332);
							((ValueContext)_localctx).a = exp();
							arglist.add(((ValueContext)_localctx).a.ast);
							}
							}
							setState(339);
							_errHandler.sync(this);
							_la = _input.LA(1);
						}
						}
					}

					setState(342);
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
			setState(354);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
			case BOOL:
			case ID:
				enterOuterAlt(_localctx, 1);
				{
				setState(348);
				((HotypeContext)_localctx).f = type();
				((HotypeContext)_localctx).ast =  ((HotypeContext)_localctx).f.ast;
				}
				break;
			case LPAR:
				enterOuterAlt(_localctx, 2);
				{
				setState(351);
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
			setState(362);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case INT:
				enterOuterAlt(_localctx, 1);
				{
				setState(356);
				match(INT);
				((TypeContext)_localctx).ast =  new IntTypeNode();
				}
				break;
			case BOOL:
				enterOuterAlt(_localctx, 2);
				{
				setState(358);
				match(BOOL);
				((TypeContext)_localctx).ast =  new BoolTypeNode();
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 3);
				{
				setState(360);
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
			setState(364);
			match(LPAR);
			ArrayList<Node> arglist = new ArrayList<Node>();
			setState(377);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << LPAR) | (1L << INT) | (1L << BOOL) | (1L << ID))) != 0)) {
				{
				setState(366);
				((ArrowContext)_localctx).a = hotype();
				arglist.add(((ArrowContext)_localctx).a.ast);
				setState(374);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(368);
					match(COMMA);
					setState(369);
					((ArrowContext)_localctx).a = hotype();
					arglist.add(((ArrowContext)_localctx).a.ast);
					}
					}
					setState(376);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			setState(379);
			match(RPAR);
			setState(380);
			match(ARROW);
			setState(381);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3+\u0183\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\5\2\37\n\2\3\2\3\2\3\2\3\2\3\2\5\2"+
		"&\n\2\3\2\3\2\3\2\3\2\5\2,\n\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\38\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3G\n\3"+
		"\f\3\16\3J\13\3\5\3L\n\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3b\n\3\f\3\16\3e\13\3\5\3g\n\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\7\3v\n\3\f\3\16\3y"+
		"\13\3\3\3\5\3|\n\3\3\3\3\3\3\3\3\3\7\3\u0082\n\3\f\3\16\3\u0085\13\3\3"+
		"\3\3\3\6\3\u0089\n\3\r\3\16\3\u008a\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\7\4\u00a7\n\4\f\4\16\4\u00aa\13\4\5\4\u00ac\n\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\5\4\u00b5\n\4\3\4\3\4\3\4\5\4\u00ba\n\4\3\4\3\4\6\4\u00be\n\4\r"+
		"\4\16\4\u00bf\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\7\5\u00d0\n\5\f\5\16\5\u00d3\13\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\7\6\u00e3\n\6\f\6\16\6\u00e6\13\6\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\7\7\u00f6\n\7\f\7\16\7\u00f9"+
		"\13\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\7\b\u010e\n\b\f\b\16\b\u0111\13\b\5\b\u0113\n\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3"+
		"\b\3\b\3\b\3\b\7\b\u013e\n\b\f\b\16\b\u0141\13\b\5\b\u0143\n\b\3\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\7\b\u0152\n\b\f\b\16\b\u0155"+
		"\13\b\5\b\u0157\n\b\3\b\3\b\5\b\u015b\n\b\5\b\u015d\n\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\5\t\u0165\n\t\3\n\3\n\3\n\3\n\3\n\3\n\5\n\u016d\n\n\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\13\3\13\7\13\u0177\n\13\f\13\16\13\u017a\13"+
		"\13\5\13\u017c\n\13\3\13\3\13\3\13\3\13\3\13\3\13\2\2\f\2\4\6\b\n\f\16"+
		"\20\22\24\2\2\2\u01a8\2\26\3\2\2\2\4\60\3\2\2\2\6\u008c\3\2\2\2\b\u00c1"+
		"\3\2\2\2\n\u00d4\3\2\2\2\f\u00e7\3\2\2\2\16\u015c\3\2\2\2\20\u0164\3\2"+
		"\2\2\22\u016c\3\2\2\2\24\u016e\3\2\2\2\26+\b\2\1\2\27\30\5\b\5\2\30\31"+
		"\b\2\1\2\31,\3\2\2\2\32\33\7\35\2\2\33%\b\2\1\2\34\36\5\4\3\2\35\37\5"+
		"\6\4\2\36\35\3\2\2\2\36\37\3\2\2\2\37 \3\2\2\2 !\b\2\1\2!&\3\2\2\2\"#"+
		"\5\6\4\2#$\b\2\1\2$&\3\2\2\2%\34\3\2\2\2%\"\3\2\2\2&\'\3\2\2\2\'(\7\36"+
		"\2\2()\5\b\5\2)*\b\2\1\2*,\3\2\2\2+\27\3\2\2\2+\32\3\2\2\2,-\3\2\2\2-"+
		".\b\2\1\2./\7\7\2\2/\3\3\2\2\2\60\u0088\b\3\1\2\61\62\b\3\1\2\62\63\7"+
		"!\2\2\63\67\7(\2\2\64\65\7\"\2\2\65\66\7(\2\2\668\b\3\1\2\67\64\3\2\2"+
		"\2\678\3\2\2\289\3\2\2\29:\b\3\1\2:;\7\25\2\2;K\b\3\1\2<=\7(\2\2=>\7\3"+
		"\2\2>?\5\22\n\2?H\b\3\1\2@A\7\4\2\2AB\7(\2\2BC\7\3\2\2CD\5\22\n\2DE\b"+
		"\3\1\2EG\3\2\2\2F@\3\2\2\2GJ\3\2\2\2HF\3\2\2\2HI\3\2\2\2IL\3\2\2\2JH\3"+
		"\2\2\2K<\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\26\2\2NO\7\27\2\2O\u0083\b\3"+
		"\1\2PQ\7 \2\2QR\7(\2\2RS\7\3\2\2ST\5\22\n\2TU\b\3\1\2UV\7\25\2\2Vf\b\3"+
		"\1\2WX\7(\2\2XY\7\3\2\2YZ\5\20\t\2Zc\b\3\1\2[\\\7\4\2\2\\]\7(\2\2]^\7"+
		"\3\2\2^_\5\20\t\2_`\b\3\1\2`b\3\2\2\2a[\3\2\2\2be\3\2\2\2ca\3\2\2\2cd"+
		"\3\2\2\2dg\3\2\2\2ec\3\2\2\2fW\3\2\2\2fg\3\2\2\2gh\3\2\2\2hi\7\26\2\2"+
		"i{\b\3\1\2jk\7\35\2\2kw\b\3\1\2lm\7\37\2\2mn\7(\2\2no\7\3\2\2op\5\22\n"+
		"\2pq\7\6\2\2qr\5\b\5\2rs\b\3\1\2st\7\7\2\2tv\3\2\2\2ul\3\2\2\2vy\3\2\2"+
		"\2wu\3\2\2\2wx\3\2\2\2xz\3\2\2\2yw\3\2\2\2z|\7\36\2\2{j\3\2\2\2{|\3\2"+
		"\2\2|}\3\2\2\2}~\5\b\5\2~\177\b\3\1\2\177\u0080\7\7\2\2\u0080\u0082\3"+
		"\2\2\2\u0081P\3\2\2\2\u0082\u0085\3\2\2\2\u0083\u0081\3\2\2\2\u0083\u0084"+
		"\3\2\2\2\u0084\u0086\3\2\2\2\u0085\u0083\3\2\2\2\u0086\u0087\7\30\2\2"+
		"\u0087\u0089\b\3\1\2\u0088\61\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u0088"+
		"\3\2\2\2\u008a\u008b\3\2\2\2\u008b\5\3\2\2\2\u008c\u00bd\b\4\1\2\u008d"+
		"\u008e\7\37\2\2\u008e\u008f\7(\2\2\u008f\u0090\7\3\2\2\u0090\u0091\5\20"+
		"\t\2\u0091\u0092\7\6\2\2\u0092\u0093\5\b\5\2\u0093\u0094\b\4\1\2\u0094"+
		"\u00ba\3\2\2\2\u0095\u0096\7 \2\2\u0096\u0097\7(\2\2\u0097\u0098\7\3\2"+
		"\2\u0098\u0099\5\22\n\2\u0099\u009a\b\4\1\2\u009a\u009b\7\25\2\2\u009b"+
		"\u00ab\b\4\1\2\u009c\u009d\7(\2\2\u009d\u009e\7\3\2\2\u009e\u009f\5\20"+
		"\t\2\u009f\u00a8\b\4\1\2\u00a0\u00a1\7\4\2\2\u00a1\u00a2\7(\2\2\u00a2"+
		"\u00a3\7\3\2\2\u00a3\u00a4\5\20\t\2\u00a4\u00a5\b\4\1\2\u00a5\u00a7\3"+
		"\2\2\2\u00a6\u00a0\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2\2\2\u00a8"+
		"\u00a9\3\2\2\2\u00a9\u00ac\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab\u009c\3\2"+
		"\2\2\u00ab\u00ac\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\7\26\2\2\u00ae"+
		"\u00b4\b\4\1\2\u00af\u00b0\7\35\2\2\u00b0\u00b1\5\6\4\2\u00b1\u00b2\7"+
		"\36\2\2\u00b2\u00b3\b\4\1\2\u00b3\u00b5\3\2\2\2\u00b4\u00af\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\5\b\5\2\u00b7\u00b8\b\4"+
		"\1\2\u00b8\u00ba\3\2\2\2\u00b9\u008d\3\2\2\2\u00b9\u0095\3\2\2\2\u00ba"+
		"\u00bb\3\2\2\2\u00bb\u00bc\7\7\2\2\u00bc\u00be\3\2\2\2\u00bd\u00b9\3\2"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00bd\3\2\2\2\u00bf\u00c0\3\2\2\2\u00c0"+
		"\7\3\2\2\2\u00c1\u00c2\5\n\6\2\u00c2\u00d1\b\5\1\2\u00c3\u00c4\7\16\2"+
		"\2\u00c4\u00c5\5\n\6\2\u00c5\u00c6\b\5\1\2\u00c6\u00d0\3\2\2\2\u00c7\u00c8"+
		"\7\17\2\2\u00c8\u00c9\5\n\6\2\u00c9\u00ca\b\5\1\2\u00ca\u00d0\3\2\2\2"+
		"\u00cb\u00cc\7\13\2\2\u00cc\u00cd\5\n\6\2\u00cd\u00ce\b\5\1\2\u00ce\u00d0"+
		"\3\2\2\2\u00cf\u00c3\3\2\2\2\u00cf\u00c7\3\2\2\2\u00cf\u00cb\3\2\2\2\u00d0"+
		"\u00d3\3\2\2\2\u00d1\u00cf\3\2\2\2\u00d1\u00d2\3\2\2\2\u00d2\t\3\2\2\2"+
		"\u00d3\u00d1\3\2\2\2\u00d4\u00d5\5\f\7\2\u00d5\u00e4\b\6\1\2\u00d6\u00d7"+
		"\7\20\2\2\u00d7\u00d8\5\f\7\2\u00d8\u00d9\b\6\1\2\u00d9\u00e3\3\2\2\2"+
		"\u00da\u00db\7\21\2\2\u00db\u00dc\5\f\7\2\u00dc\u00dd\b\6\1\2\u00dd\u00e3"+
		"\3\2\2\2\u00de\u00df\7\f\2\2\u00df\u00e0\5\f\7\2\u00e0\u00e1\b\6\1\2\u00e1"+
		"\u00e3\3\2\2\2\u00e2\u00d6\3\2\2\2\u00e2\u00da\3\2\2\2\u00e2\u00de\3\2"+
		"\2\2\u00e3\u00e6\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\13\3\2\2\2\u00e6\u00e4\3\2\2\2\u00e7\u00e8\5\16\b\2\u00e8\u00f7\b\7\1"+
		"\2\u00e9\u00ea\7\b\2\2\u00ea\u00eb\5\16\b\2\u00eb\u00ec\b\7\1\2\u00ec"+
		"\u00f6\3\2\2\2\u00ed\u00ee\7\t\2\2\u00ee\u00ef\5\16\b\2\u00ef\u00f0\b"+
		"\7\1\2\u00f0\u00f6\3\2\2\2\u00f1\u00f2\7\n\2\2\u00f2\u00f3\5\16\b\2\u00f3"+
		"\u00f4\b\7\1\2\u00f4\u00f6\3\2\2\2\u00f5\u00e9\3\2\2\2\u00f5\u00ed\3\2"+
		"\2\2\u00f5\u00f1\3\2\2\2\u00f6\u00f9\3\2\2\2\u00f7\u00f5\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\r\3\2\2\2\u00f9\u00f7\3\2\2\2\u00fa\u00fb\7\22\2"+
		"\2\u00fb\u015d\b\b\1\2\u00fc\u00fd\7\23\2\2\u00fd\u015d\b\b\1\2\u00fe"+
		"\u00ff\7\24\2\2\u00ff\u015d\b\b\1\2\u0100\u0101\7$\2\2\u0101\u015d\b\b"+
		"\1\2\u0102\u0103\7#\2\2\u0103\u0104\7(\2\2\u0104\u0105\b\b\1\2\u0105\u0106"+
		"\7\25\2\2\u0106\u0112\b\b\1\2\u0107\u0108\5\b\5\2\u0108\u010f\b\b\1\2"+
		"\u0109\u010a\7\4\2\2\u010a\u010b\5\b\5\2\u010b\u010c\b\b\1\2\u010c\u010e"+
		"\3\2\2\2\u010d\u0109\3\2\2\2\u010e\u0111\3\2\2\2\u010f\u010d\3\2\2\2\u010f"+
		"\u0110\3\2\2\2\u0110\u0113\3\2\2\2\u0111\u010f\3\2\2\2\u0112\u0107\3\2"+
		"\2\2\u0112\u0113\3\2\2\2\u0113\u0114\3\2\2\2\u0114\u0115\7\26\2\2\u0115"+
		"\u015d\b\b\1\2\u0116\u0117\7\25\2\2\u0117\u0118\5\b\5\2\u0118\u0119\7"+
		"\26\2\2\u0119\u011a\b\b\1\2\u011a\u015d\3\2\2\2\u011b\u011c\7\31\2\2\u011c"+
		"\u011d\5\b\5\2\u011d\u011e\7\32\2\2\u011e\u011f\7\27\2\2\u011f\u0120\5"+
		"\b\5\2\u0120\u0121\7\30\2\2\u0121\u0122\7\33\2\2\u0122\u0123\7\27\2\2"+
		"\u0123\u0124\5\b\5\2\u0124\u0125\7\30\2\2\u0125\u0126\b\b\1\2\u0126\u015d"+
		"\3\2\2\2\u0127\u0128\7\r\2\2\u0128\u0129\7\25\2\2\u0129\u012a\5\b\5\2"+
		"\u012a\u012b\7\26\2\2\u012b\u012c\b\b\1\2\u012c\u015d\3\2\2\2\u012d\u012e"+
		"\7\34\2\2\u012e\u012f\7\25\2\2\u012f\u0130\5\b\5\2\u0130\u0131\7\26\2"+
		"\2\u0131\u0132\b\b\1\2\u0132\u015d\3\2\2\2\u0133\u0134\7(\2\2\u0134\u015a"+
		"\b\b\1\2\u0135\u0136\7\25\2\2\u0136\u0142\b\b\1\2\u0137\u0138\5\b\5\2"+
		"\u0138\u013f\b\b\1\2\u0139\u013a\7\4\2\2\u013a\u013b\5\b\5\2\u013b\u013c"+
		"\b\b\1\2\u013c\u013e\3\2\2\2\u013d\u0139\3\2\2\2\u013e\u0141\3\2\2\2\u013f"+
		"\u013d\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0143\3\2\2\2\u0141\u013f\3\2"+
		"\2\2\u0142\u0137\3\2\2\2\u0142\u0143\3\2\2\2\u0143\u0144\3\2\2\2\u0144"+
		"\u0145\7\26\2\2\u0145\u015b\b\b\1\2\u0146\u0147\7\5\2\2\u0147\u0148\7"+
		"(\2\2\u0148\u0149\b\b\1\2\u0149\u014a\7\25\2\2\u014a\u0156\b\b\1\2\u014b"+
		"\u014c\5\b\5\2\u014c\u0153\b\b\1\2\u014d\u014e\7\4\2\2\u014e\u014f\5\b"+
		"\5\2\u014f\u0150\b\b\1\2\u0150\u0152\3\2\2\2\u0151\u014d\3\2\2\2\u0152"+
		"\u0155\3\2\2\2\u0153\u0151\3\2\2\2\u0153\u0154\3\2\2\2\u0154\u0157\3\2"+
		"\2\2\u0155\u0153\3\2\2\2\u0156\u014b\3\2\2\2\u0156\u0157\3\2\2\2\u0157"+
		"\u0158\3\2\2\2\u0158\u0159\7\26\2\2\u0159\u015b\b\b\1\2\u015a\u0135\3"+
		"\2\2\2\u015a\u0146\3\2\2\2\u015a\u015b\3\2\2\2\u015b\u015d\3\2\2\2\u015c"+
		"\u00fa\3\2\2\2\u015c\u00fc\3\2\2\2\u015c\u00fe\3\2\2\2\u015c\u0100\3\2"+
		"\2\2\u015c\u0102\3\2\2\2\u015c\u0116\3\2\2\2\u015c\u011b\3\2\2\2\u015c"+
		"\u0127\3\2\2\2\u015c\u012d\3\2\2\2\u015c\u0133\3\2\2\2\u015d\17\3\2\2"+
		"\2\u015e\u015f\5\22\n\2\u015f\u0160\b\t\1\2\u0160\u0165\3\2\2\2\u0161"+
		"\u0162\5\24\13\2\u0162\u0163\b\t\1\2\u0163\u0165\3\2\2\2\u0164\u015e\3"+
		"\2\2\2\u0164\u0161\3\2\2\2\u0165\21\3\2\2\2\u0166\u0167\7%\2\2\u0167\u016d"+
		"\b\n\1\2\u0168\u0169\7&\2\2\u0169\u016d\b\n\1\2\u016a\u016b\7(\2\2\u016b"+
		"\u016d\b\n\1\2\u016c\u0166\3\2\2\2\u016c\u0168\3\2\2\2\u016c\u016a\3\2"+
		"\2\2\u016d\23\3\2\2\2\u016e\u016f\7\25\2\2\u016f\u017b\b\13\1\2\u0170"+
		"\u0171\5\20\t\2\u0171\u0178\b\13\1\2\u0172\u0173\7\4\2\2\u0173\u0174\5"+
		"\20\t\2\u0174\u0175\b\13\1\2\u0175\u0177\3\2\2\2\u0176\u0172\3\2\2\2\u0177"+
		"\u017a\3\2\2\2\u0178\u0176\3\2\2\2\u0178\u0179\3\2\2\2\u0179\u017c\3\2"+
		"\2\2\u017a\u0178\3\2\2\2\u017b\u0170\3\2\2\2\u017b\u017c\3\2\2\2\u017c"+
		"\u017d\3\2\2\2\u017d\u017e\7\26\2\2\u017e\u017f\7\'\2\2\u017f\u0180\5"+
		"\22\n\2\u0180\u0181\b\13\1\2\u0181\25\3\2\2\2%\36%+\67HKcfw{\u0083\u008a"+
		"\u00a8\u00ab\u00b4\u00b9\u00bf\u00cf\u00d1\u00e2\u00e4\u00f5\u00f7\u010f"+
		"\u0112\u013f\u0142\u0153\u0156\u015a\u015c\u0164\u016c\u0178\u017b";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}