package ast;

import lib.FOOLlib;

public class AndNode implements Node {

	private Node left;
	private Node right;

	public AndNode(Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "And\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	/*
	 * Verifico che le due espressioni siano booleane.
	 */
	public Node typeCheck() {
		if (!(left.typeCheck() instanceof BoolTypeNode
				&& right.typeCheck() instanceof BoolTypeNode)) {
			System.out.println("Not boolean types in and");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		String l3 = FOOLlib.freshLabel();
		return left.codeGeneration() +		// pusho il risultato della prima espressione
				"push 1\n" +				
				"beq " + l1 + "\n" +		// se è true salto a l1 e verifico la seconda espressione
				"push 0\n" +				// se è false, pusho 0 (senza controllare la seconda espressione)...
				"b " + l2 + "\n" +			// ... Ed esco dal pezzo di codice
				l1 + ": \n" +
				right.codeGeneration() +	// pusho il risultato della seconda espressione
				"push 1\n" +
				"beq " + l3 + "\n" +		// se anch'essa è true salto a l3 e faccio push 1
				"push 0\n" +				// se è false, pusho 0...
				"b " + l2 + "\n" +			// ... Ed esco dal pezzo di codice
				l3 + ": \n" +
				"push 1\n" +
				l2 + ": \n";
	}

}