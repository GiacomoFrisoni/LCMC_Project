package ast;

import lib.FOOLlib;

public class NotNode implements Node {

	private Node exp;

	public NotNode(Node e) {
		exp = e;
	}

	public String toPrint(String s) {
		return s + "Not\n" + exp.toPrint(s + "  ");
	}

	public Node typeCheck() {
		// verifico che l'espressione sia di tipo booleano
		if (!(exp.typeCheck() instanceof BoolTypeNode)) {
			System.out.println("Not boolean type in not");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return exp.codeGeneration() +		// pusho il risultato dell'espressione
				"push 1\n" +
				"beq " + l1 + "\n" +		// se è true, salto a l1 e faccio push 0
				"push 1\n" +				// se è false, faccio push 1 ed esco dalla porzione di codice
				"b " + l2 + "\n" +
				l1 + ": \n" +
				"push 0\n" +
				l2 + ": \n";
	}

}