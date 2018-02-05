package ast;

import java.util.ArrayList;

import lib.FOOLlib;

/**
 * Nodo dell'AST corrispondente alla sintassi:
 * ID1.ID2()
 */
public class ClassCallNode implements Node {

	private String id;											// nome dell'object pointer
	private String methodId;									// nome del metodo
	private STentry entry;										// entry dell'oggetto di tipo riferimento
	private STentry methodEntry;								// entry del metodo
	private ArrayList<Node> parlist = new ArrayList<Node>();	// elenco delle espressioni passate come parametro al metodo
	private int nestingLevel;

	public ClassCallNode(String io, String im, STentry sto, STentry stm, ArrayList<Node> p, int nl) {
		id = io;
		methodId = im;
		entry = sto;
		methodEntry = stm;
		parlist = p;
		nestingLevel = nl;
	}

	public String toPrint(String s) {
		String parlstr = "";
		for (Node par : parlist) {
			parlstr += par.toPrint(s + "  ");
		};
		return s + "ClassCall: method " + methodId + " of object " + id + " at nestinglevel " + nestingLevel + "\n" +
			s + "  Object Reference STentry:\n" + entry.toPrint(s + "    ") +
			s + "  Method STentry:\n" + methodEntry.toPrint(s + "    ") + parlstr;
	}

	public Node typeCheck() {
		/*
		 * Verifico che si stia usando l'identificatore di un metodo e non di una variabile
		 * o di una funzione.
		 */
		ArrowTypeNode t = null;
		if (methodEntry.getType() instanceof ArrowTypeNode && methodEntry.isMethod())
			t = (ArrowTypeNode) methodEntry.getType();
		else {
			System.out.println("Invocation of a non-method " + methodId);
			System.exit(0);
		}
		/*
		 * Verifico che il numero di parametri specificati al momento della dichiarazione (ArrowTypeNode)
		 * coincida effettivamente con quelli indicati nella chiamata.
		 */
		ArrayList<Node> p = t.getParList();
		if (!(p.size() == parlist.size())) {
			System.out.println("Wrong number of parameters in the invocation of " + methodId);
			System.exit(0);
		}
		/*
		 * Verifico che il tipo di ogni parametro attuale sia sottotipo del relativo parametro formale.
		 */
		for (int i = 0; i < parlist.size(); i++)
			if (!(FOOLlib.isSubtype((parlist.get(i)).typeCheck(), p.get(i)))) {
				System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + methodId);
				System.exit(0);
			}
		/*
		 * Il tipo di ritorno è quello dichiarato dal metodo.
		 */
		return t.getRet();
	}

	public String codeGeneration() {
		/*
		 * Genero il codice delle espressioni che inizializzano i parametri in ordine inverso.
		 */
		String parCode = "";
		for (int i = parlist.size() - 1; i >= 0; i--)
			parCode += parlist.get(i).codeGeneration();
		
		/*
		 * Recupero l'AR nello stack in cui è dichiarato ID1, con il meccanismo
		 * di risalita della catena statica.
		 */
		String getAR_ID1 = "";
		for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
			// differenza di nesting level tra dove sono e la dichiarazione di ID1
			getAR_ID1 += "lw\n";
		
		return 	// alloco la mia parte dell'AR del metodo che sto chiamando
				"lfp\n" + 									// metto il CL sullo stack
				parCode + 									// alloco i valori dei parametri
				// setto l'AL col valore di ID1
				"push " + entry.getOffset() + "\n" +		// metto l'offset di ID1 sullo stack
				"lfp\n" + getAR_ID1 + 						// risalgo la catena statica e ottengo l'indirizzo dell'AR di ID1
				"add\n" +									// sommo l'offset
				"lw\n" +									// carico sullo stack il valore di ID1
				// predispongo il salto
				"push " + methodEntry.getOffset() + "\n" +	// metto l'offset di ID2 sullo stack
				"push " + entry.getOffset() + "\n" +		// metto l'offset di ID1 sullo stack
				"lfp\n" + getAR_ID1 + 						// risalgo la catena statica e ottengo l'indirizzo dell'AR di ID1
				"add\n" +									// sommo l'offset
				"lw\n" +									// carico sullo stack il valore di ID1
															// (carico nuovamente sullo stack l'object pointer)
				"lw\n" +									// carico sullo stack il dispatch pointer (con un'ulteriore deferenziazione)
				"add\n" +									// sommo l'offset di ID2
				"lw\n" +									// carico sullo stack l'indirizzo del metodo cui saltare
				// effettuo il salto
				"js\n";
	}

}