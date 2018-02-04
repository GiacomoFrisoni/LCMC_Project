package ast;

import java.util.ArrayList;

import lib.FOOLlib;

public class FunNode implements Node, DecNode {

	private String id;											// nome della funzione
	private Node type;											// tipo del ritorno
	private ArrayList<Node> parlist = new ArrayList<Node>();	// parametri
	private ArrayList<Node> declist = new ArrayList<Node>();	// dichiarazioni
	private Node exp;											// corpo della funzione
	private ArrowTypeNode symType;								// tipo in symbol table

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
	
	public void addSymType(ArrowTypeNode s) {
		symType = s;
	}

	@Override
	public Node getSymType() {
		return symType;
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
			type.toPrint(s + "  ") + parlstr + declstr + exp.toPrint(s + "  ") +
			symType.toPrint(s + "  ");
	}

	public Node typeCheck() {
		for (Node dec : declist) {
			dec.typeCheck();
		};
		// il tipo del corpo deve essere un sottotipo di quanto dichiarato tornare dalla funzione
		if (!FOOLlib.isSubtype(exp.typeCheck(), type)) {
			System.out.println("Invalid return type for function: " + id);
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
		 * Se una dichiarazione ha tipo funzionale, vengono effettuate due pop.
		 */
		String popDecl = "";
		for (Node dec : declist) {
			popDecl += (((DecNode)dec).getSymType() instanceof ArrowTypeNode) ? "pop\npop\n" : "pop\n";
		};
		/*
		 * Creo una stringa con tanti pop quanti i parametri.
		 * Se un parametro ha tipo funzionale, vengono effettuate due pop.
		 */
		String popParl = "";
		for (Node par : parlist) {
			popParl += (((DecNode)par).getSymType() instanceof ArrowTypeNode) ? "pop\npop\n" : "pop\n";
		};
		/*
		 * CODICE DELLA FUNZIONE
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
		 * CODICE RITORNATO DALLA CODE GENERATION
		 * Pusho una coppia di valori:
		 * - l'indirizzo dell'AR in cui è dichiarata la funzione;
		 * - l'indirizzo della funzione.
		 */
		return	"lfp\n" +
		 		"push " + funl + "\n";
	}

}