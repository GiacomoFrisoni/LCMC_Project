package ast;

public interface Node {

	/**
	 * Restituisce una stringa rappresentante il contenuto del nodo
	 * per la visualizzazione dell'AST.
	 * 
	 * @param indent
	 * 		indentazione laterale a sinistra
	 * @return rappresentazione del nodo
	 */
	String toPrint(String indent);

	/**
	 * Fa il tupe checking e ritorna:
	 * - per una espressione, il suo tipo
	 * - per una dichiarazione, "null"
	 * 
	 * @return tipo in output del nodo
	 */
	Node typeCheck();

	/**
	 * Restituisce il codice generato per il nodo in esame,
	 * compiendo eventuali azioni correlate.
	 * 
	 * @return codice generato per il nodo
	 */
	String codeGeneration();

}