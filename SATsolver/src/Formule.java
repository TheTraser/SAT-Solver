import java.util.TreeSet;

public interface Formule {
	
	// Cre�er methodes die checken of een valuatie waar/onwaar is 
	boolean waar(Valuatie v);
	boolean onwaar(Valuatie v);
	
	// Cre�er methode die alle variabelen verzamelt
	void verzamel(TreeSet<String> collector);

}
