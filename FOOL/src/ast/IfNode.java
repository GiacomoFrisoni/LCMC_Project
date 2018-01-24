package ast;

import lib.FOOLlib;

public class IfNode implements Node {

	private Node cond;
	private Node th;
	private Node el;

	public IfNode(Node c, Node t, Node e) {
		cond = c;
		th = t;
		el = e;
	}

	public String toPrint(String s) {
		return s + "If\n" + cond.toPrint(s + "  ") +
				th.toPrint(s + "  ") +
				el.toPrint(s + "  ");
	}

	/*
	 * Verifico che le due espressioni th ed el siano una sottotipo dell'altra
	 * e delle due ritorno il tipo gerarchicamente più grande.
	 */
	public Node typeCheck() {
		if (!(FOOLlib.isSubtype(cond.typeCheck(), new BoolTypeNode()))) {
			System.out.println("non boolean condition in if");
			System.exit(0);
		}
		Node t = th.typeCheck();
		Node e = el.typeCheck();
		if (FOOLlib.isSubtype(t, e))
			return e;
		if (FOOLlib.isSubtype(e, t))
			return t;
		System.out.println("Incompatible types in then-else branches");
		System.exit(0);
		return null;
	}

	public String codeGeneration() {
		String l1 = FOOLlib.freshLabel();
		String l2 = FOOLlib.freshLabel();
		return cond.codeGeneration() +		// metto sullo stack il risultato della condizione (1 o 0)
				"push 1\n" +
				"beq " + l1 + "\n" +		// se il risultato della condizione è 1 salto a l1
				el.codeGeneration() +		// calcolo l'else e lascio il risultato sullo stack
				"b " + l2 + "\n" +
				l1 + ": \n" +
				th.codeGeneration() +		// genero il risultato dello then e lo metto sullo stack
				l2 + ": \n";
	}

}