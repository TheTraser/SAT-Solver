import java.util.TreeSet;

public class Conjunctie implements Formule {
	Formule links;
	Formule rechts; 
	
	// Maak constructor voor conjunctie
	public Conjunctie (Formule links, Formule rechts) {
		this.links = links; 
		this.rechts = rechts;
	}
	
	// Maak een ToString methode om conjunctie te representeren
	public String toString(){
		  return "(" + links.toString() + "/\\" + rechts.toString() +  ")";
	}
		
	// Creëer een methode voor wanneer conjunctie waar is
	// De variabelen zijn waar als links en rechts waar zijn
	public boolean waar(Valuatie v){
		return (links.waar(v) && rechts.waar(v));
	}

	// Creëer een methode voor wanneer conjunctie onwaar is
	// De variabelen zijn onwaar als links of rechts onwaar zijn
	public boolean onwaar(Valuatie v){
		return (links.onwaar(v) || rechts.onwaar(v));
	}
	
	// Voeg variabelen uit conjunctie aan TreeSet toe
	public void verzamel(TreeSet<String> collector) {
		links.verzamel(collector); 
		rechts.verzamel(collector);
	}
}