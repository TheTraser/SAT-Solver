import java.util.TreeSet;

public class Disjunctie implements Formule {
	Formule links;
	Formule rechts; 
	
	// Maak constructor voor disjunctie
	public Disjunctie (Formule links, Formule rechts) {
		this.links = links; 
		this.rechts = rechts;
	}
	
	
	// Creëer een ToString methode voor disjunctie
	public String toString(){
		  return "(" + links.toString() + "\\/" +  rechts.toString() + ")";
		 }
	
	// Creëer een methode voor wanneer disjunctie waar is
	// De variabelen zijn waar als links of rechts waar is
	public boolean waar(Valuatie v){
		return (links.waar(v) || rechts.waar(v));
	}

	// Creëer een methode voor wanneer disjunctie onwaar is
	// De variabelen zijn onwaar als links en rechts onwaar zijn
	public boolean onwaar(Valuatie v){
		return (links.onwaar(v) && rechts.onwaar(v));
	}
	
	// Voeg variabelen uit disjunctie aan TreeSet toe
	public void verzamel(TreeSet<String> collector) {
		links.verzamel(collector); 
		rechts.verzamel(collector);
	}
}
