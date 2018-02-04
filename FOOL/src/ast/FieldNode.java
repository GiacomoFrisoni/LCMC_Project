package ast;

public class FieldNode implements Node, DecNode {

	private String id;		// nome del campo
	private Node type;		// tipo del campo
	private int offset;		// offset in symbol table

	public FieldNode(String i, Node t) {
		id = i;
		type = t;
	}
	
	@Override
	public Node getSymType() {
		return type;
	}
	
	public void addOffset(int o) {
		offset = o;
	}
	
	public int getOffset() {
		return offset;
	}

	public String toPrint(String s) {
		return s + "Field:" + id + "\n" +
				type.toPrint(s + "  ") +
				s + "  Offset:" + offset + "\n";
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