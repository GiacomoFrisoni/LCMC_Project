package lib;

import ast.*;

public class FOOLlib {

	private static int labCount = 0;
	private static int funLabCount = 0;

	// campo per la memorizzazione del codice delle funzioni
	private static String funCode = "";

	/**
	 * Valuta se il tipo "a" è <= al tipo "b" (sotto-tipaggio).
	 * 
	 * @param a
	 * 		Primo tipo
	 * @param b
	 * 		Secondo tipo
	 * @return true se a è sottotipo di b, false altrimenti
	 */
	public static boolean isSubtype(Node a, Node b) {
		return a.getClass().equals(b.getClass()) || ((a instanceof BoolTypeNode) && (b instanceof IntTypeNode));
	}

	/**
	 * Restituisce un'etichetta diversa ogni volta che viene chiamata
	 * 
	 * @return etichetta univoca
	 */
	public static String freshLabel() {
		return "label" + (labCount++);
	}
	
	/**
	 * Restituisce un'etichetta per funzioni diversa ogni volta che viene chiamata
	 * 
	 * @return etichetta per funzioni univoca
	 */
	public static String freshFunLabel() {
		return "function" + (funLabCount++);
	}

	/**
	 * aggiunge il codice della dichiarazione di funzione specificata alla collezione
	 * 
	 * @param c
	 * 		codice di dichiarazione di funzione
	 */
	public static void putCode(String c) {
		funCode += "\n" + c; // aggiunge una linea vuota di separazione prima della funzione
	}

	/**
	 * Ritorna il codice delle funzioni dichiarate
	 * 
	 * @return l'intero codice di dichiarazione delle funzioni
	 */
	public static String getCode() {
		return funCode;
	}

}
