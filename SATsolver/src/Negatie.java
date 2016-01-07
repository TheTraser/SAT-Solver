import java.util.TreeSet;

public class Negatie implements Formule {
	Formule negatieProp;  		
	
	//Maak constructor voor Negatie
	public Negatie (Formule negatieProp){ 
	this.negatieProp = negatieProp; 
	} 
	
	//Maak een ToString methode om negatie te representeren
	public String toString(){
		return "(" + "-" + negatieProp.toString() + ")";
	}
	 
	// Creëer een methode voor wanneer negatie waar is
	// De propositie is waar als negatieprop onwaar is
	public boolean waar(Valuatie v){
		return (negatieProp.onwaar(v));
	}
	
	// Creëer een methode voor wanneer negatie onwaar is
	// De propositie is waar als negatieprop waar is
	public boolean onwaar(Valuatie v){
		return (negatieProp.waar(v));
	}

	// Voeg variabelen uit negatie aan TreeSet toe
	public void verzamel(TreeSet<String> collector) {
		negatieProp.verzamel(collector); 
	}

}
