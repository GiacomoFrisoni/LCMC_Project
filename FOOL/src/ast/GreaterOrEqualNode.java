package ast;

import lib.FOOLlib;

public class GreaterOrEqualNode implements Node {

	private Node left;
	private Node right;

	public GreaterOrEqualNode(Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "GreaterOrEqual\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	public Node typeCheck() {
		// verifico che i nodi siano di tipo intero o suo sottotipo
		if (!(FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode())
				&& FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()))) {
			System.out.println("Not integer types in greater than or equal to comparison");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		String l3 = FOOLlib.freshLabel();
		return left.codeGeneration() +
				right.codeGeneration() +	// pusho i risultati delle due espressioni
				"beq " + l1 + "\n" +		// se sono uguali salto a l1 e faccio push 1
				left.codeGeneration() +
				right.codeGeneration() +
				"bleq " + l3 + "\n" +		// altrimenti guardo se il primo è minore del secondo
											// se sì, salto a l3, pusho 0 ed esco dalla porzione di codice
											// se no, proseguo e faccio push 1
				l1 + ": \n" +	
				"push 1\n" +
				"b " + l2 + "\n" +
				l3 + ": \n" +
				"push 0\n" +
				l2 + ": \n";
	}

}