package ast;

public interface Node {

	String toPrint(String indent);

	/**
	 * Fa il tupe checking e ritorna:
	 * - per una espressione, il suo tipo
	 * - per una dichiarazione, "null"
	 * 
	 * @return tipo in output del nodo
	 */
	Node typeCheck();

	String codeGeneration();

}