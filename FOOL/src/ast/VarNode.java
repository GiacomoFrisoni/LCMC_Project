package ast;

import lib.FOOLlib;

public class VarNode implements Node {

	private String id;	// nome della variabile
	private Node type;	// tipo della variabile
	private Node exp;	// valore di inzializzazione

	public VarNode(String i, Node t, Node v) {
		id = i;
		type = t;
		exp = v;
	}

	public String toPrint(String s) {
		return s + "Var:" + id + "\n" +
				type.toPrint(s + "  ") +
				exp.toPrint(s + "  ");
	}

	/*
	 * Verifico che il tipo dell'espressione di inizializzazione coincida
	 * con quello di dichiarazione.
	 */
	public Node typeCheck() {
		if (!FOOLlib.isSubtype(exp.typeCheck(), type)) {
			System.out.println("Incompatible value for variable: " + id);
			System.exit(0);
		}
		// nelle dichiarazioni non ha senso parlare di tipo di ritorno
		return null;
	}

	public String codeGeneration() {
		return exp.codeGeneration();
	}

}