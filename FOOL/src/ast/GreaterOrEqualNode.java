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

	/*
	 * Verifico che i nodi siano di tipo intero o suo sottotipo.
	 */
	public Node typeCheck() {
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
				right.codeGeneration() +	// pusha i risultati delle due espressioni
				"beq " + l1 + "\n" +		// se sono uguali salta a l1 e fa push 1
				left.codeGeneration() +
				right.codeGeneration() +
				"bleq" + l3 + "\n" +		// altrimenti guarda se il primo è minore del secondo
											// se sì, salta a l3, pusha 0 ed esce dalla porzione di codice
											// se no, prosegue e fa push 1
				l1 + ": \n" +	
				"push 1\n" +
				"b " + l2 + "\n" +
				l3 + ": \n" +
				"push 0\n" +
				l2 + ": \n";
	}

}