package ast;

public class STentry {

	private int nl;
	private Node type;
	private int offset;
	private boolean methodYN;

	public STentry(int n, int os, boolean m) {
		nl = n;
		offset = os;
		methodYN = m;
	}

	public STentry(int n, Node t, int os, boolean m) {
		nl = n;
		type = t;
		offset = os;
		methodYN = m;
	}

	public void addType(Node t) {
		type = t;
	}

	public Node getType() {
		return type;
	}

	public int getOffset() {
		return offset;
	}

	public int getNestinglevel() {
		return nl;
	}
	
	public boolean isMethod() {
		return methodYN;
	}

	public String toPrint(String s) {
		return s + "STentry: nestlev " + Integer.toString(nl) + "\n" +
				s + "STentry: type\n " + type.toPrint(s + "  ") +
				s + "STentry: offset " + Integer.toString(offset) + "\n" +
				s + "STentry: isMethod=" + Boolean.toString(methodYN) + "\n";
	}

}