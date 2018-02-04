package ast;

import java.util.ArrayList;

import lib.FOOLlib;

/**
 * Nodo dell'AST corrispondente alla sintassi:
 * ID()
 */
public class CallNode implements Node {

	private String id;											// nome della funzione
	private int nestingLevel;
	private STentry entry;
	private ArrayList<Node> parlist = new ArrayList<Node>();	// elenco delle espressioni passate come parametro

	public CallNode(String i, STentry st, ArrayList<Node> p, int nl) {
		id = i;
		entry = st;
		parlist = p;
		nestingLevel = nl;
	}

	public String toPrint(String s) {
		String parlstr = "";
		for (Node par : parlist) {
			parlstr += par.toPrint(s + "  ");
		};
		return s + "Call:" + id + " at nestinglevel " + nestingLevel + "\n" +
			entry.toPrint(s + "  ") + parlstr;
	}

	public Node typeCheck() {
		/*
		 * Verifico che si stia usando l'identificatore di una funzione e non di una variabile.
		 */
		ArrowTypeNode t = null;
		if (entry.getType() instanceof ArrowTypeNode)
			t = (ArrowTypeNode) entry.getType();
		else {
			System.out.println("Invocation of a non-function " + id);
			System.exit(0);
		}
		/*
		 * Verifico che il numero di parametri specificati al momento della dichiarazione (ArrowTypeNode)
		 * coincida effettivamente con quelli indicati nella chiamata (CallNode).
		 */
		ArrayList<Node> p = t.getParList();
		if (!(p.size() == parlist.size())) {
			System.out.println("Wrong number of parameters in the invocation of " + id);
			System.exit(0);
		}
		/*
		 * Verifico che il tipo di ogni parametro attuale sia sottotipo del relativo parametro formale.
		 */
		for (int i = 0; i < parlist.size(); i++)
			if (!(FOOLlib.isSubtype((parlist.get(i)).typeCheck(), p.get(i)))) {
				System.out.println("Wrong type for " + (i + 1) + "-th parameter in the invocation of " + id);
				System.exit(0);
			}
		/*
		 * Il tipo di ritorno è quello dichiarato dalla funzione.
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
		
		if (entry.isMethod()) {
			/*
			 * Recupero l'AR, risalendo la catena, in cui è dichiarato il metodo che sto usando.
			 * Calcolo dunque la differenza di nesting level tra dove sono e la dichiarazione di "id".
			 * Inoltre, aggiungo 1 alla differenza di nesting level in modo che, risalendo la
			 * catena statica, si raggiunga la dispatch table (e non ci si fermi all'object pointer).
			 */
			String getAR = "";
			for (int i = 0; i < ((nestingLevel - entry.getNestinglevel())+1); i++)
				getAR += "lw\n";

			return	// alloco la mia parte dell'AR del metodo che sto chiamando
					"lfp\n" +								// CL
					parCode +								// alloco i valori parametri
					"lfp\n" + getAR +						// AL
					// codice per recuperare l'indirizzo a cui saltare (stesso delle variabili)
					"push " + entry.getOffset() + "\n" + 	// metto l'offset sullo stack
					"lfp\n" + getAR +						// risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile
					"add\n" +
					"lw\n" +								// carico sullo stack l'indirizzo a cui saltare
					// effettuo il salto
					"js\n";
		} else {
			/*
			 * Recupero l'AR, risalendo la catena, in cui è dichiarata la funzione che sto usando.
			 * Calcolo dunque la differenza di nesting level tra dove sono e la dichiarazione di "id".
			 */
			String getAR = "";
			for (int i = 0; i < nestingLevel - entry.getNestinglevel(); i++)
				getAR += "lw\n";
			
			return  // alloco la mia parte dell'AR della funzione che sto chiamando
					"lfp\n" + 									// metto il CL sullo stack
					parCode + 									// alloco i valori dei parametri
					// codice per recuperare l'indirizzo a cui saltare (stesso delle variabili)
					// setto l'AL
					"push " + entry.getOffset() + "\n" + 		// metto "offset ID" sullo stack
					"lfp\n" + getAR + 							// risalgo la catena statica e ottengo l'indirizzo dell'AR di dichiarazione di ID
					"add\n" + "lw\n" + 							// sommo l'offset e carico sullo stack l'indirizzo dell'AR di dichiarazione della funzione
					// predispongo il salto
					"push " + (entry.getOffset()-1) + "\n" + 	// metto "offset ID - 1" sullo stack
					"lfp\n" + getAR + 							// risalgo la catena statica e ottengo l'indirizzo dell'AR di dichiarazione di ID
					"add\n" + "lw\n" + 							// sommo l'offset e carico sullo stack l'indirizzo della funzione cui saltare
					// effettuo il salto
					"js\n";
		}
	}

}