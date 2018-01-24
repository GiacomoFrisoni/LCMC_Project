package ast;

import lib.FOOLlib;

public class OrNode implements Node {

	private Node left;
	private Node right;

	public OrNode(Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "Or\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	/*
	 * Verifico che le due espressioni siano booleane.
	 */
	public Node typeCheck() {
		if (!(left.typeCheck() instanceof BoolTypeNode
				&& right.typeCheck() instanceof BoolTypeNode)) {
			System.out.println("Not boolean types in or");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration() +		// pusho il risultato della prima espressione
				"push 1\n" +
				"beq " + l1 + "\n" +		// se è true salto a l1 e faccio push 1 (senza controllare la seconda)
				right.codeGeneration() +	// se è false, pusho il risultato della seconda espressione
				"push 1\n" +
				"beq " + l1 + "\n" +		// se è true salto a l1 e faccio push 1, altrimento pusho 0...
				"push 0\n" +
				"b " + l2 + "\n" +			// ... Ed esco dal pezzo di codice
				l1 + ": \n" +
				"push 1\n" +
				l2 + ": \n";
	}

}