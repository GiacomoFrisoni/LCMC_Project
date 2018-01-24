package ast;

import lib.FOOLlib;

public class EqualNode implements Node {

	private Node left;
	private Node right;

	public EqualNode(Node l, Node r) {
		left = l;
		right = r;
	}

	public String toPrint(String s) {
		return s + "Equal\n" + left.toPrint(s + "  ") + right.toPrint(s + "  ");
	}

	/*
	 * Verifico che i nodi siano uno sottotipo dell'altro.
	 * L'uso di espressioni con tipi funzionali non è consentito.
	 */
	public Node typeCheck() {
		Node l = left.typeCheck();
		Node r = right.typeCheck();
		if (!(FOOLlib.isSubtype(l, r) || FOOLlib.isSubtype(r, l))) {
			System.out.println("Incompatible types in equal");
			System.exit(0);
		}
		if (l instanceof ArrowTypeNode || r instanceof ArrowTypeNode) {
			System.out.println("The use of expressions with functional types is not allowed");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration() +
				right.codeGeneration() +	// pusha due valori
				"beq " + l1 + "\n" +		// se sono uguali salta a l1 e fa push 1
				"push 0\n" +				// altrimenti fa push 0 e salta fuori dal pezzo di codice
				"b " + l2 + "\n" +
				l1 + ": \n" +
				"push 1\n" +
				l2 + ": \n";
	}

}