package ast;

public class RefTypeNode implements Node {

	private String classId;		// id della classe di riferimento
	
	public RefTypeNode(String i) {
		classId = i;
	}
	
	public String getClassId() {
		return classId;
	}

	public String toPrint(String s) {
		return s + "RefType:" + classId + "\n";
	}

	// non utilizzato
	public Node typeCheck() {
		return null;
	}

	// non utilizzato
	public String codeGeneration() {
		return "";
	}

}