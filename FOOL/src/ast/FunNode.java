package ast;

import java.util.ArrayList;

import lib.FOOLlib;

public class FunNode implements Node {

	private String id;											// nome della funzione
	private Node type;											// tipo del ritorno
	private ArrayList<Node> parlist = new ArrayList<Node>();	// parametri
	private ArrayList<Node> declist = new ArrayList<Node>();	// dichiarazioni
	private Node exp;											// corpo della funzione

	public FunNode(String i, Node t) {
		id = i;
		type = t;
	}

	public void addDec(ArrayList<Node> d) {
		declist = d;
	}

	public void addBody(Node b) {
		exp = b;
	}

	public void addPar(Node p) {
		parlist.add(p);
	}

	public String toPrint(String s) {
		String parlstr = "";
		for (Node par : parlist) {
			parlstr += par.toPrint(s + "  ");
		};
		String declstr = "";
		for (Node dec : declist) {
			declstr += dec.toPrint(s + "  ");
		};
		return s + "Fun:" + id + "\n" +
			type.toPrint(s + "  ") + parlstr + declstr + exp.toPrint(s + "  ");
	}

	public Node typeCheck() {
		for (Node dec : declist) {
			dec.typeCheck();
		};
		// il tipo del corpo deve essere un sottotipo di quanto dichiarato tornare dalla funzione
		if (!FOOLlib.isSubtype(exp.typeCheck(), type)) {
			System.out.println("Incompatible value for variable");
			System.exit(0);
		}
		// i parametri, a differenza delle variabili, non vengono inizializzati
		return null;
	}

	public String codeGeneration() {
		/*
		 * Genero un'etichetta fresh per la funzione, che serve come indirizzo cui saltare.
		 */
		String funl = FOOLlib.freshFunLabel();
		/*
		 * Creo il codice delle dichiarazioni ricorsivamente.
		 */
		String declCode = "";
		for (Node dec : declist) {
			declCode += dec.codeGeneration();
		};
		/*
		 * Creo una stringa con tanti pop quante le dichiarazioni.
		 */
		String popDecl = "";
		for (@SuppressWarnings("unused") Node dec : declist) {
			popDecl += "pop\n";
		};
		/*
		 * Creo una stringa con tanti pop quanti i parametri.
		 */
		String popParl = "";
		for (@SuppressWarnings("unused") Node par : parlist) {
			popParl += "pop\n";
		};
		/*
		 * Memorizzo il codice della dichiarazione nella collezione di codice di tutte
		 * le dichiarazioni di funzioni.
		 */
		FOOLlib.putCode(
				funl + ":\n" +
				"cfp\n" + 				// setta $fp allo $sp
				"lra\n" + 				// inserimento Return Address
				declCode +				// dichiarazioni locali della funzione
				// AR completato
				// eseguo il corpo della funzione
				exp.codeGeneration() +
				// dealloco l'AR
				"srv\n" + 				// pop del return value e memorizzazione in $rv
				popDecl + 				// una pop per ogni dichiarazione
				"sra\n" + 				// pop del Return Address e memorizzazione in $ra
				"pop\n" + 				// pop di AL
				popParl +				// una pop per ogni parametro
				"sfp\n" + 				// ripristino il $fp al valore del CL
				"lrv\n" + 				// risultato della funzione sullo stack
				"lra\n" + "js\n" 		// salta a $ra
		);
		/*
		 * Eseguo una push dell'indirizzo.
		 */
		return "push " + funl + "\n";
	}

}