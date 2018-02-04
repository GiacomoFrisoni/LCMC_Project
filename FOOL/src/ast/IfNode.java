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
	 * Verifico che le due espressioni th ed el abbiano un antenato in comune
	 * e ritorno quello più prossimo.
	 */
	public Node typeCheck() {
		if (!(FOOLlib.isSubtype(cond.typeCheck(), new BoolTypeNode()))) {
			System.out.println("Non boolean condition in if");
			System.exit(0);
		}
		Node lca = FOOLlib.lowestCommonAncestor(th.typeCheck(), el.typeCheck());
		if (lca == null) {
			System.out.println("Incompatible types in then-else branches");
			System.exit(0);
		}
		return lca;
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