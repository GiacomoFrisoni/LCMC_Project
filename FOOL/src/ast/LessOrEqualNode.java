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

	/*
	 * Verifico che i nodi siano di tipo intero o suo sottotipo.
	 */
	public Node typeCheck() {
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
				right.codeGeneration() +	// pusha i risultati delle due espressioni
				"beq " + l1 + "\n" +		// se sono uguali salta a l1 e fa push 1
				left.codeGeneration() +
				right.codeGeneration() +
				"bleq " + l1 + "\n" +		// altrimenti guarda se il primo è minore del secondo
											// se sì, salta a l1 e fa push 1
											// se no, pusha 0 ed esce dalla porzione di codice
				"push 0\n" +
				"b " + l2 + "\n" +
				l1 + ": \n" +	
				"push 1\n" +
				l2 + ": \n";
	}

}