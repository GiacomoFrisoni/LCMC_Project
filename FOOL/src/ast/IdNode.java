package ast;

public class IdNode implements Node {

	private String id;
	private int nestingLevel;
	private STentry entry;

	public IdNode(String i, STentry st, int nl) {
		id = i;
		entry = st;
		nestingLevel = nl;
	}

	public String toPrint(String s) {
		return s + "Id:" + id + " at nestinglevel " + nestingLevel + "\n" + entry.toPrint(s + "  ");
	}

	public Node typeCheck() {
		return entry.getType();
	}

	public String codeGeneration() {
		/*
		 * Recupero l'AR in cui è dichiarata la variable che sto usando.
		 */
		String getAR = "";
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
			// differenza di nesting level tra dove sono e la dichiarazione di "id"
			getAR += "lw\n";

		if (entry.getType() instanceof ArrowTypeNode) {
			return loadFromOffset(entry.getOffset(), getAR) + loadFromOffset(entry.getOffset()-1, getAR);
		} else {
			return loadFromOffset(entry.getOffset(), getAR);
		}
	}
	
	private String loadFromOffset(int offset, String getAR) {
		return "push " + offset + "\n" +		// metto l'offset sullo stack
				"lfp\n" + getAR + 				// risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile/funzione
				"add\n" +						// sommo l'offset
				"lw\n"; 						// carico sullo stack il valore all'indirizzo ottenuto
	}

}