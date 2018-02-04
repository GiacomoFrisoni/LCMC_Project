package ast;

import lib.FOOLlib;

public class VarNode implements Node, DecNode {

	private String id;	// nome della variabile
	private Node type;	// tipo della variabile
	private Node exp;	// valore di inzializzazione

	public VarNode(String i, Node t, Node v) {
		id = i;
		type = t;
		exp = v;
	}
	
	@Override
	public Node getSymType() {
		return type;
	}

	public String toPrint(String s) {
		return s + "Var:" + id + "\n" +
				type.toPrint(s + "  ") +
				exp.toPrint(s + "  ");
	}

	public Node typeCheck() {
		/*
		 * Verifico che il tipo dell'espressione di inizializzazione coincida
		 * con quello di dichiarazione.
		 */
		if (!FOOLlib.isSubtype(exp.typeCheck(), type)) {
			System.out.println("Incompatible value for variable: " + id);
			System.exit(0);
		}
		// nelle dichiarazioni non ha senso parlare di tipo di ritorno
		return null;
	}

	public String codeGeneration() {
		/*
		 * La code generation dell'espressione effettua una push se di tipo non funzionale,
		 * o due push se di tipo funzionale.
		 */
		return exp.codeGeneration();
	}
	
}