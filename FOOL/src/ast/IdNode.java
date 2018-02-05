package ast;

/**
 * Nodo dell'AST corrispondente alla sintassi:
 * ID
 */
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
		/*
		 * Controllo che ID non sia un metodo.
		 * Infatti, è possibile passare Higher Order funzioni ma non metodi.
		 */
		if (entry.isMethod()) {
			System.out.println("Wrong usage of method identifier");
			System.exit(0);
		}
		// controllo che ID non sia il nome di una classe
		if (entry.getType() instanceof ClassTypeNode) {
			System.out.println("Wrong usage of class identifier");
			System.exit(0);
		}
		return entry.getType();
	}

	public String codeGeneration() {
		/*
		 * Potrebbe trattarsi di una variabile, un campo o un tipo funzionale.
		 * Recupero l'AR nello stack o l'oggetto nello heap in cui è dichiarato l'ID
		 * che sto usando.
		 */
		String getAR = "";
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
			// differenza di nesting level tra dove sono e la dichiarazione di "id"
			getAR += "lw\n";
		
		if (entry.getType() instanceof ArrowTypeNode) {
			/*
			 * Se ID è di tipo funzionale, due cose vengono messe nello stack
			 * (recuperandole come valori dall'AR dove è dichiarato ID e con usuale meccanismo di risalita
			 * della catena statica):
			 * - l'indirizzo all'AR di dichiarazione della funzione (recuperato a offset ID);
			 * - l'indirizzo della funzione (recuperato a offset ID - 1).
			 */
			return loadFromOffset(entry.getOffset(), getAR) + loadFromOffset(entry.getOffset()-1, getAR);
		} else {
			return loadFromOffset(entry.getOffset(), getAR);
		}
	}
	
	private String loadFromOffset(int offset, String getAR) {
		return "push " + offset + "\n" +		// metto l'offset sullo stack
				"lfp\n" + getAR + 				// risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile/funzione
												// o dell'oggetto
				"add\n" +						// sommo l'offset
				"lw\n"; 						// carico sullo stack il valore all'indirizzo ottenuto
	}

}