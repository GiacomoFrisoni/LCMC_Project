package ast;

/**
 * null
 */
public class EmptyNode implements Node {

	public EmptyNode() {
	}

	public String toPrint(String s) {
		return s + "Empty\n";
	}

	public Node typeCheck() {
		return new EmptyTypeNode();
	}

	public String codeGeneration() {
		/*
		 * Mette sullo stack il valore -1
		 * (sicuramente diverso dall'object pointer di ogni oggetto creato).
		 */
		return "push -1\n";
	}

}