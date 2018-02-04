package ast;

import java.util.ArrayList;

import lib.FOOLlib;

/**
 * class ID1 [extends ID2]...
 */
public class ClassNode implements Node, DecNode {

	private String id;											// nome della classe
	private ArrayList<Node> fields = new ArrayList<Node>();		// dichiarazioni dei campi
	private ArrayList<Node> methods = new ArrayList<Node>();	// dichiarazioni dei metodi
	private ClassTypeNode symType;								// tipo in symbol table
	private STentry superEntry;									// entry della classe super

	public ClassNode(String i) {
		id = i;
	}

	public void addField(Node f) {
		fields.add(f);
	}

	public void addMethod(Node m) {
		methods.add(m);
	}
	
	public void addSymType(ClassTypeNode s) {
		symType = s;
	}
	
	public void setSuperEntry(STentry s) {
		superEntry = s;
	}

	public String toPrint(String s) {
		String fieldstr = "";
		for (Node f : fields) {
			fieldstr += f.toPrint(s + "  ");
		};
		String methodstr = "";
		for (Node m : methods) {
			methodstr += m.toPrint(s + "  ");
		};
		return s + "Class:" + id + "\n" +
			symType.toPrint(s + "  ") + fieldstr + methodstr;
	}

	public Node typeCheck() {
		/*
		 * Mi richiamo sui figli che sono metodi (controllo che il codice del metodo
		 * ritorni quanto dichiarato).
		 */
		for (Node met : methods) {
			met.typeCheck();
		}
		/*
		 * Controllo che eventuali overriding siano corretti:
		 * i tipi dei campi e dei metodi devono essere sottotipi di quelli del genitore.
		 * Per ogni figlio campo o metodo, considero l'offset e calcolo la posizione corrispondente
		 * nell'allFields/allMethods del tipo del genitore. Se la posizione ottenuta è inferiore alla
		 * lunghezza del relativo array, si tratta di overriding ed è dunque necessario un controllo;
		 * altrimenti non occorre alcuna modifica poichè il campo o il metodo è stato aggiunto per
		 * estensione.
		 */
		if (superEntry != null) {
			ArrayList<Node> superFields = ((ClassTypeNode)superEntry.getType()).getAllFields();
			for (int i = 0; i < fields.size(); i++) {
				// controllo il subtyping soltanto se si tratta di overriding (e non di campo aggiunto per estensione)
				if ((-((FieldNode)(fields.get(i))).getOffset()-1) < superFields.size()) {
					if (!FOOLlib.isSubtype(symType.getAllFields().get(i), superFields.get(i))) {
						System.out.println("Invalid overriding type for the " + (i+1) + "-th field inside class " + id);
						System.exit(0);
					}
				}
			}
			ArrayList<Node> superMethods = ((ClassTypeNode)superEntry.getType()).getAllMethods();
			for (int i = 0; i < methods.size(); i++) {
				// controllo il subtyping soltanto se si tratta di overriding (e non di metodo aggiunto per estensione)
				if (((MethodNode)(methods.get(i))).getOffset() < superMethods.size()) {
					if (!FOOLlib.isSubtype(symType.getAllMethods().get(i), superMethods.get(i))) {
						System.out.println("Invalid overriding type for the " + (i+1) + "-th method inside class " + id);
						System.exit(0);
					}
				}
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public String codeGeneration() {
		/*
		 * Creo la dispatch table di partenza per la classe.
		 */
		ArrayList<String> dispatchTable;
		if (superEntry == null) {	// se non eredito
			// creo una nuova dispatch table vuota
			dispatchTable = new ArrayList<String>();
		} else {					// se eredito
			// copio la dispatch table della classe da cui eredito
			dispatchTable = (ArrayList<String>)FOOLlib.getDispatchTable(-superEntry.getOffset()-2).clone();
		}
		/*
		 * Aggiungo la dispatch table alla collezione.
		 */
		FOOLlib.addDispatchTable(dispatchTable);
		/*
		 * Aggiorno la dispatch table, considerando i figli metodi in ordine di apparizione.
		 */
		for (Node m : methods) {
			// creo il codice del metodo (genero etichetta)
			m.codeGeneration();
			/*
			 * Setto la posizione data dall'offset del metodo alla sua etichetta:
			 * - se il metodo è nuovo, viene aggiunto;
			 * - se il metodo è associato a un offset esistente, viene sostituito per
			 *   effetto dell'overriding.
			 */
			if (dispatchTable.size() <= ((MethodNode)m).getOffset()) {
				dispatchTable.add(((MethodNode)m).getLabel());
			} else {
				dispatchTable.set(((MethodNode)m).getOffset(), ((MethodNode)m).getLabel());
			}
		}
		/*
		 * Genero il codice per la costruzione della dispatch table sullo heap.
		 * La scorro e memorizzo ciascuna etichetta nello heap. 
		 */
		String dispCode = "";
		for (String label : dispatchTable) {
			dispCode += "push " + label + "\n" +	// carico l'etichetta sullo stack
						"lhp\n" +					// carico il contenuto di $hp sullo stack
						"sw\n" +					// memorizzo l'etichetta a indirizzo $hp
						"lhp\n" +					// carico $hp sullo stack
						"push 1\n" +				// carico il valore 1 sullo stack
						"add\n" +					// incremento $hp e salvo il risultato sullo stack
						"shp\n";					// aggiorno il valore di $hp
		}
		/*
		 * CODICE RITORNATO DALLA CODE GENERATION
		 */
		return  // metto il valore di $hp sullo stack (dispatch pointer da ritornare alla fine)
				"lhp\n" +
				// creo sullo heap la dispatch table costruita in memoria
				dispCode;
	}

	@Override
	public Node getSymType() {
		return symType;
	}

}