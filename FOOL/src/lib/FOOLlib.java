package lib;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.stream.IntStream;

import ast.*;

public class FOOLlib {
	
	// costante indicante la dimensione della memoria
	public static final int MEMSIZE = 10000;

	private static int labCount = 0;
	private static int funLabCount = 0;

	// campo per la memorizzazione del codice delle funzioni
	private static String funCode = "";
	
	/*
	 * Struttura dati mappante l'ID di una classe nell'ID della sua classe super.
	 * Definisce la gerarchia dei tipi riferimento.
	 */
	private static HashMap<String,String> superType = new HashMap<String,String>();
	
	/*
	 * Struttura dati memorizzante le dispatch table di tutte le classi,
	 * in ordine di dichiarazione nell'ambiente globale.
	 * Una dispatch table è una tabella tenente traccia dell'indirizzo associato
	 * a ogni metodo di classe.
	 */
	private static ArrayList<ArrayList<String>> dispatchTables = new ArrayList<ArrayList<String>>();

	
	/**
	 * Valuta se il tipo "a" è <= al tipo "b" (sotto-tipaggio).
	 * Bool è sottotipo di Int.
	 * Un tipo funzionale è sottotipo di un altro tipo funzionale se:
	 * - hanno lo stesso numero di parametri
	 * - vale la co-varianza sul tipo di ritorno
	 * - vale la contro-varianza sul tipo dei parametri
	 * Un tipo RefTypeNode è sottotipo di un altro in base alla relazione superType
	 * (considerando la raggiungibilità applicata a catena molteplici volte).
	 * Un tipo EmptyTypeNode è sottotipo di un qualsiasi tipo riferimento RefTypeNode
	 * (null è accettato ovunque ci si aspetti un riferimento).
	 * 
	 * @param a
	 * 		Primo tipo
	 * @param b
	 * 		Secondo tipo
	 * @return true se a è sottotipo di b, false altrimenti
	 */
	public static boolean isSubtype(Node a, Node b) {
		return a.getClass().equals(b.getClass()) || ((a instanceof BoolTypeNode) && (b instanceof IntTypeNode))
				|| (((a instanceof ArrowTypeNode) && (b instanceof ArrowTypeNode))
						&& (((ArrowTypeNode)a).getParList().size() == ((ArrowTypeNode)b).getParList().size())
						&& (isSubtype(((ArrowTypeNode)a).getRet(), ((ArrowTypeNode)b).getRet()))
						&& (IntStream.range(0, ((ArrowTypeNode)a).getParList().size()-1)
								.allMatch(i -> isSubtype(((ArrowTypeNode)b).getParList().get(i), ((ArrowTypeNode)a).getParList().get(i)))))
				|| (((a instanceof RefTypeNode) && (b instanceof RefTypeNode))
						&& checkReachability(((RefTypeNode)a).getClassId(), ((RefTypeNode)b).getClassId()))
				|| ((a instanceof EmptyTypeNode) && (b instanceof RefTypeNode));
	}
	
	/*
	 * Verifica se un valore è raggiungibile da una chiave (considerando anche passaggi multipli),
	 * a partire dalle relazioni chiave-valore della HashMap superType.
	 */
	private static boolean checkReachability(String x, String y) {
		if (!superType.containsKey(x)) {
			return false;
		} else if (superType.get(x).equals(y)) {
			return true;
		}
		return checkReachability(superType.get(x), y);
	}
	
	/**
	 * Calcola l'antenato comune più prossimo tra due tipi.
	 * 
	 * @param a
	 * 		Primo tipo
	 * @param b
	 * 		Secondo tipo
	 * @return l'antenato comune più prossimo
	 */
	public static Node lowestCommonAncestor(Node a, Node b) {
		// per a e b tipi riferimento (o EmptyTypeNode)
		if (((a instanceof RefTypeNode) || (a instanceof EmptyTypeNode))
			&& (b instanceof RefTypeNode) || (b instanceof EmptyTypeNode)) {
			// se uno tra a e b è EmptyTypeNode, ritorno l'altro
			if (a instanceof EmptyTypeNode) {
				return b;
			} else if (b instanceof EmptyTypeNode) {
				return a;
			}
			// se sono entrambi tipi riferimento, cerco e ritorno il loro antenato comune
			return searchLowestCommonAncientRefType((RefTypeNode)a, (RefTypeNode)b);
		}
		// per a e b tipi bool/int
		else if (((a instanceof IntTypeNode) || (a instanceof BoolTypeNode))
				&& (b instanceof IntTypeNode) || (b instanceof BoolTypeNode)) {
			// torno int se almeno uno è int, bool altrimenti
			if ((a instanceof IntTypeNode) || (b instanceof IntTypeNode)) {
				return new IntTypeNode();
			} else {
				return new BoolTypeNode();
			}
		}
		// per a e b tipi funzionali con stesso numero di parametri
		else if (((a instanceof ArrowTypeNode) && (b instanceof ArrowTypeNode))
				&& (((ArrowTypeNode)a).getParList().size() == ((ArrowTypeNode)b).getParList().size())) {
			Node lcaRet = lowestCommonAncestor(((ArrowTypeNode)a).getRet(), ((ArrowTypeNode)b).getRet());
			// se esiste il lowest common ancestor dei tipi di ritorno di a e b
			if (lcaRet != null) {
				ArrayList<Node> parList = new ArrayList<Node>();
				for (int i = 0; i < ((ArrowTypeNode)a).getParList().size(); i++) {
					// se i parametri sono uno sottotipo dell'altro
					if (isSubtype(((ArrowTypeNode)a).getParList().get(i), ((ArrowTypeNode)b).getParList().get(i))) {
						parList.add(((ArrowTypeNode)a).getParList().get(i));
					} else if (isSubtype(((ArrowTypeNode)b).getParList().get(i), ((ArrowTypeNode)a).getParList().get(i))) {
						parList.add(((ArrowTypeNode)b).getParList().get(i));
					} else {
						return null;
					}
				}
				/*
				 * Torno un tipo funzionale che ha come tipo di ritorno il risultato della chiamata
				 * ricorsiva (covarianza) e come tipo di parametro i-esimo il tipo che è sottotipo dell'altro
				 * (controvarianza).
				 */
				return new ArrowTypeNode(parList, lcaRet);
			} else {
				return null;
			}
		} else {
			// in ogni altro caso torno null
			return null;
		}
	}
	
	/*
	 * A partire da due RefTypeNode, verifica se questi hanno un antenato in comune
	 * nella gerarchia dell'ereditarietà. Ritorna un tipo riferimento a tale classe qualora
	 * esista, null in caso contrario.
	 */
	private static Node searchLowestCommonAncientRefType(RefTypeNode x, RefTypeNode y) {
		if (x == null || y == null) {
			return null;
		} else if (isSubtype(y, x)) {
			return x;
		} else if (!superType.containsKey(x.getClassId())) {
			return null;
		}
		return searchLowestCommonAncientRefType(new RefTypeNode(superType.get(x.getClassId())), y);
	}

	/**
	 * Restituisce un'etichetta diversa ogni volta che viene chiamata.
	 * 
	 * @return etichetta univoca
	 */
	public static String freshLabel() {
		return "label" + (labCount++);
	}
	
	/**
	 * Restituisce un'etichetta per funzioni diversa ogni volta che viene chiamata.
	 * 
	 * @return etichetta per funzioni univoca
	 */
	public static String freshFunLabel() {
		return "function" + (funLabCount++);
	}

	/**
	 * Aggiunge il codice della dichiarazione di funzione specificata alla collezione.
	 * 
	 * @param c
	 * 		codice di dichiarazione di funzione
	 */
	public static void putCode(String c) {
		funCode += "\n" + c; // aggiunge una linea vuota di separazione prima della funzione
	}

	/**
	 * Ritorna il codice delle funzioni dichiarate.
	 * 
	 * @return l'intero codice di dichiarazione delle funzioni
	 */
	public static String getCode() {
		return funCode;
	}

	/**
	 * Aggiunge una nuova relazione di sottotipaggio tra classi.
	 * 
	 * @param c
	 * 		classe
	 * @param sc
	 * 		super classe
	 */
	public static void addSuperTypeRelation(String c, String sc) {
		superType.put(c, sc);
	}
	
	/**
	 * Aggiunge una nuova dispatch table di classe alla collezione.
	 * 
	 * @param a
	 * 		dispatch table
	 */
	public static void addDispatchTable(ArrayList<String> a) {
		dispatchTables.add(a);
	}
	
	/**
	 * Restituisce la dispatch table associata all'indice specificato.
	 * 
	 * @param index
	 * 		indice della dispatch table
	 * @return dispatch table richiesta
	 */
	public static ArrayList<String> getDispatchTable(int index) {
		return dispatchTables.get(index);
	}
}
