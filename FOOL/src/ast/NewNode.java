package ast;

import java.util.ArrayList;

import lib.FOOLlib;

/**
 * new ID()
 */
public class NewNode implements Node {

	private String id;											// id della classe
	private STentry entry;										// entry della classe
	private int nestingLevel;									// nesting level in cui sono
	private ArrayList<Node> parlist = new ArrayList<Node>();	// elenco delle espressioni passate come parametro

	public NewNode(String i, STentry e, ArrayList<Node> p, int nl) {
		id = i;
		entry = e;
		parlist = p;
		nestingLevel = nl;
	}

	public String toPrint(String s) {
		String parlstr = "";
		for (Node par : parlist) {
			parlstr += par.toPrint(s + "  ");
		};
		return s + "New:" + id + " at nestinglevel " + nestingLevel + "\n" + entry.toPrint(s + "  ") + parlstr;
	}

	public Node typeCheck() {
		/*
		 * Verifico che il numero di parametri specificati al momento della dichiarazione (ClassTypeNode)
		 * coincida effettivamente con quelli indicati nella chiamata (NewNode).
		 */
		ArrayList<Node> parTypes = ((ClassTypeNode)entry.getType()).getAllFields();
		if (!(parTypes.size() == parlist.size())) {
			System.out.println("Wrong number of parameters in the object creation for class " + id);
			System.exit(0);
		}
		/*
		 * Verifico che il tipo di ogni parametro attuale sia sottotipo del relativo parametro formale.
		 */
		for (int i = 0; i < parlist.size(); i++)
			if (!(FOOLlib.isSubtype((parlist.get(i)).typeCheck(), parTypes.get(i)))) {
				System.out.println("Wrong type for " + (i + 1) + "-th parameter in the object creation for class " + id);
				System.exit(0);
			}
		/*
		 * Il tipo di ritorno è un tipo riferimento.
		 */
		return new RefTypeNode(id);
	}

	public String codeGeneration() {
		/*
		 * Chiamo la code generation su tutti i parametri in ordine di apparizione
		 * (esattamente come per una chiamata a funzione). Ciascuna chiamata metterà
		 * sullo stack il proprio valore calcolato.
		 */
		String parCode = "";
		for (int i = 0; i < parlist.size(); i++)
			parCode += parlist.get(i).codeGeneration();
		/*
		 * Copio i valori dei parametri, uno alla volta, dallo stack allo heap
		 * (incrementando $hp dopo ogni singola copia).
		 */
		String copyParCode = "";
		for (int i = 0; i < parlist.size(); i++)
			copyParCode += 	"lhp\n" +							// carico il contenuto di $hp sullo stack
							"sw\n" +							// memorizzo il valore del parametro a indirizzo $hp
							"lhp\n" +							// carico $hp sullo stack
							"push 1\n" +				// carico il valore 1 sullo stack
							"add\n" +					// incremento $hp e salvo il risultato sullo stack
							"shp\n";					// aggiorno il valore di $hp
		
		return 	parCode +
				copyParCode +
				"push " + (FOOLlib.MEMSIZE + entry.getOffset()) + "\n" +	// carico l'offset cui trovare il
																			// dispatch pointer sullo stack
				"lw\n" +													// carico sullo stack il dispatch pointer
				"lhp\n" +													// carico il contenuto di $hp sullo stack
				"sw\n" +													// memorizzo il dp a indirizzo $hp
				"lhp\n" +													// carico il valore di $hp sullo stack
																			// (indirzzo object pointer da ritornare)
				"lhp\n" +													// incremento $hp
				"push 1\n" +
				"add\n" +
				"shp\n";
	}

}