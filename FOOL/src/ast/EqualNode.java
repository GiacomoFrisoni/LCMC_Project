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

	public Node typeCheck() {
		Node l = left.typeCheck();
		Node r = right.typeCheck();
		/*
		 * L'uso di espressioni con tipi funzionali non è consentito
		 * (dovrei infatti controllare coppie di valori <indirizzo, riferimento all'AR della dichiarazione>).
		 * È invece possibile, ad esempio, confrontare variabili riferimento nell'OO:
		 * queste infatti non presentano coppie di valori, ma contengono soltanto l'indirizzo dell'oggetto.
		 */
		if (l instanceof ArrowTypeNode || r instanceof ArrowTypeNode) {
			System.out.println("The use of expressions with functional types is not allowed");
			System.exit(0);
		}
		// verifico che i nodi siano uno sottotipo dell'altro
		if (!(FOOLlib.isSubtype(l, r) || FOOLlib.isSubtype(r, l))) {
			System.out.println("Incompatible types in equal");
			System.exit(0);
		}
		return new BoolTypeNode();
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return left.codeGeneration() +
				right.codeGeneration() +	// pusho due valori
				"beq " + l1 + "\n" +		// se sono uguali salto a l1 e faccio push 1
				"push 0\n" +				// altrimenti faccio push 0 e salto fuori dal pezzo di codice
				"b " + l2 + "\n" +
				l1 + ": \n" +
				"push 1\n" +
				l2 + ": \n";
	}

}