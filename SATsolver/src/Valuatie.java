import java.util.HashMap;

/**
 * Klasse die een valuatie geeft voor een set variabelen, dat wil zeggen
 * dat elke variabele de waarde true (waar) of false (onwaar) krijgt
 */
public class Valuatie {
	HashMap<String, Boolean> map;
	
	/**
	 * Maak een nieuwe lege valuatie aan
	 */
	public Valuatie() {
		this.map = new HashMap<String, Boolean>();
	}
	
	/**
	 * Controleer of een variabele in de valuatie zit
	 * @param variable De op te zoeken variabele
	 * @return true als de variabele in de valuatie zit, false anders
	 */
	public boolean bevatVariabele(String variable) {
		return map.containsKey(variable);
	}
	
	/**
	 * Zoek de waarde van een variabele op in de valuatie. Let op: deze
	 * methode werkt alleen correct als de variabele bestaat!
	 * @param variable De op te zoeken variabele
	 * @return De waarde van de variabele 
	 */
	public boolean geefWaarde(String variable) {
		return map.get(variable);
	}
	
	/**
	 * Voeg een variabele toe aan de valuatie, of overschrijf de waarde
	 * als deze variabele al bestond.
	 * @param variable De variabele
	 * @param value De waarheidswaarde
	 */
	public void voegToe(String variable, boolean value) {
		map.put(variable, value);
	}
	
	/**
	 * Verwijder een variabele uit deze valuatie
	 * @param varariable De variabele
	 */
	public void verwijder(String varariable) {
		map.remove(varariable);
	}
	
	/**
	 * Een String representatie van deze valuatie, de variabelen
	 * zullen op willekeurige volgorde worden geprint
	 */
	public String toString() {
		return map.toString();
	}
}