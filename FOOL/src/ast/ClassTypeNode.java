package ast;

import java.util.ArrayList;

public class ClassTypeNode implements Node, Cloneable {

	/*
	 * Campi, in ordine di apparizione, tenenti conto:
	 * - dell'ereditarieta'
	 * - dell'overriding
	 */
	private ArrayList<Node> allFields = new ArrayList<Node>();		// tipi dei campi
	private ArrayList<Node> allMethods = new ArrayList<Node>();		// tipi dei metodi

	public ClassTypeNode() {
	}
	
	public void addFieldType(Node ft) {
		allFields.add(ft);
	}
	
	public void setFieldType(int index, Node ft) {
		allFields.set(index, ft);
	}
	
	public ArrayList<Node> getAllFields() {
		return allFields;
	}
	
	public void addMethodType(Node mt) {
		allMethods.add(mt);
	}
	
	public void setMethodType(int index, Node mt) {
		allMethods.set(index, mt);
	}
	
	public ArrayList<Node> getAllMethods() {
		return allMethods;
	}

	public String toPrint(String s) {
		//return s + "ClassType\n";
		return null;
	}

	// non utilizzato
	public Node typeCheck() {
		return null;
	}

	// non utilizzato
	public String codeGeneration() {
		return "";
	}
	
	@SuppressWarnings("unchecked")
	public Object clone() {
		try {
			ClassTypeNode c = (ClassTypeNode) super.clone();
			c.allFields = (ArrayList<Node>) this.allFields.clone();
			c.allMethods = (ArrayList<Node>) this.allMethods.clone();
			return c;
		} catch (Exception e) {
			return null; 
	    }
	}

}