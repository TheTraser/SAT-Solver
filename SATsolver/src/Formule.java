import java.util.TreeSet;

public interface Formule {
	
	// Creëer methodes die checken of een valuatie waar/onwaar is 
	boolean waar(Valuatie v);
	boolean onwaar(Valuatie v);
	
	// Creëer methode die alle variabelen verzamelt
	void verzamel(TreeSet<String> collector);

}
