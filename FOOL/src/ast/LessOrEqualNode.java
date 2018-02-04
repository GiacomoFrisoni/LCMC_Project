package ast;

import lib.FOOLlib;

public class LessOrEqualNode implements Node {

	private Node left;
	private Node right;

	public LessOrEqualNode(Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "LessOrEqual\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	public Node typeCheck() {
		// verifico che i nodi siano di tipo intero o suo sottotipo
		if (!(FOOLlib.isSubtype(left.typeCheck(), new IntTypeNode())
				&& FOOLlib.isSubtype(right.typeCheck(), new IntTypeNode()))) {
			System.out.println("Not integer types in less than or equal to comparison");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration() +
				right.codeGeneration() +	// pusho i risultati delle due espressioni
				"beq " + l1 + "\n" +		// se sono uguali salto a l1 e faccio push 1
				left.codeGeneration() +
				right.codeGeneration() +
				"bleq " + l1 + "\n" +		// altrimenti guardo se il primo è minore del secondo
											// se sì, salto a l1 e faccio push 1
											// se no, pusho 0 ed esco dalla porzione di codice
				"push 0\n" +
				"b " + l2 + "\n" +
				l1 + ": \n" +	
				"push 1\n" +
				l2 + ": \n";
	}

}