package ast;
import java.util.ArrayList;

import lib.FOOLlib;

public class CallNode implements Node {

  private String id;
  private int nestingLevel;
  private STentry entry;
  private ArrayList<Node> parlist = new ArrayList<Node>(); 
  
  public CallNode (String i, STentry st, ArrayList<Node> p, int nl) {
   id=i;
   entry=st;
   parlist=p;
   nestingLevel=nl;
  }
  
  
  public String toPrint(String s) {
		 String parlstr="";
		 for (Node par:parlist){parlstr+=par.toPrint(s+"  ");};
	     return s+"Call:" + id + " at nestinglevel "+nestingLevel+"\n"  +
			   entry.toPrint(s+"  ") +  
			   parlstr;
  }
  
  public Node typeCheck() {	 
		 ArrowTypeNode t=null;
	     if (entry.getType() instanceof ArrowTypeNode) t=(ArrowTypeNode) entry.getType(); 
	     else {
	       System.out.println("Invocation of a non-function "+id);
	       System.exit(0);
	     }
	     ArrayList<Node> p = t.getParList();
	     if ( !(p.size() == parlist.size()) ) {
	       System.out.println("Wrong number of parameters in the invocation of "+id);
	       System.exit(0);
	     } 
	     for (int i=0; i<parlist.size(); i++) 
	       if ( !(FOOLlib.isSubtype( (parlist.get(i)).typeCheck(), p.get(i)) ) ) {
	         System.out.println("Wrong type for "+(i+1)+"-th parameter in the invocation of "+id);
	         System.exit(0);
	       } 
	     return t.getRet();
  }
  
 public String codeGeneration() {

	  String parCode=""; 
	  for (int i=parlist.size()-1; i>=0; i--)
		  parCode+=parlist.get(i).codeGeneration();
		
	  String getAR=""; //recupero l'AR in cui è dichiarata la funzione che sto usando
	  for (int i=0;i<nestingLevel-entry.getNestinglevel();i++)
		            //differenza di nesting level tra dove sono e la dichiarazione di "id"
	  getAR+="lw\n";
	  
	  return //allocazione della mia parte dell'AR della funzione che sto chiamando
			 "lfp\n"+ //CL
	         parCode + //allocazione valori parametri
	         "lfp\n"+getAR+ //AL
			 //codice per recuperare l'inidirizzo a cui saltare (stesso delle variabili)
			 "push "+entry.getOffset()+"\n"+ //metto l'offset sullo stack
             "lfp\n"+getAR+ //risalgo la catena statica e ottengo l'indirizzo dell'AR della variabile	 
			 "add\n"+
             "lw\n"+ //carico sullo stack l'indirizzo a cui saltare
             //effettuo il salto
	         "js\n";
  }
    
}  