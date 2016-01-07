import java.util.TreeSet;

public class Propositie implements Formule {

	private String prop;
	
	// Maak constructor voor propositie
	public Propositie(String prop) {
		this.prop =  prop; 
	}
	
	// Maak een ToString methode om propositie te representeren
	public String toString(){
		return prop;
	}
	
	// Creëer een methode voor wanneer propositie waar is
	public boolean waar(Valuatie v){
		if (v.bevatVariabele(prop)){return v.geefWaarde(prop);}
		return true;
	}
	

	// Creëer een methode voor wanneer propositie onwaar is
	public boolean onwaar(Valuatie v){
		if (v.bevatVariabele(prop)){ return !v.geefWaarde(prop); }
		return true;
	}
	
	// Voeg variabelen uit propositie aan TreeSet toe
	public void verzamel(TreeSet<String> collector) {
		collector.add(prop);
	}
}
