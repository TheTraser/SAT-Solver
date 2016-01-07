import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;

/**
 * Klasse die gebruikt kan worden om propositielogische formules te
 * parsen
 */
public class Parser {
	String s;
	int i;

	/**
	 * Maak parser aan voor de gegeven string
	 * @param s De string die de formule bevat
	 */
	public Parser(String s) {
		// Haal alle whitespace weg, dat maakt parsen makkelijker
		this.s = s.replaceAll("\\s+", "");
		this.i = 0;
	}
	
	/**
	 * Maak parser aan voor de gegeven bestandsnaam
	 * @param bestandsnaam Naam van het bestand
	 * @return Een parser voor de inhoud van het bestand
	 * @throws IOException Als het bestand niet gelezen kon worden
	 */
	public static Parser vanBestand(String bestandsnaam) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(bestandsnaam));
		String result = br.readLine();
		br.close();
		return new Parser(result);
	}
	
	/**
	 * Parse de eerstvolgende formule in de gegeven string.
	 * @return De geparste formule
	 * @throws ParseException Bij een onverwacht formaat
	 */
	public Formule parseFormule() throws ParseException {
		// Bij een haakje openen verwachten we een binair connectief
		if(s.charAt(i) == '(') {
			// Schuif positie op en parse linker argument
			i++;
			Formule l = parseFormule();
			
			// Onthoud binair connectief, onze binaire connectieven bestaan
			// altijd uit 2 karakters
			String connective = s.substring(i, i + 2);
			i += 2; 
			
			// Parse rechter argument
			Formule r = parseFormule();
			
			// Controleer of we nu inderdaad bij een sluithaakje zijn
			if(s.charAt(i) != ')') {
				throw new ParseException(") verwacht", i);
			}
			i++;
			
			// Maak nu het resultaat op basis van het connectief
			if(connective.equals("/\\")) {
				return maakEn(l, r);
			} else if(connective.equals("\\/")) {
				return maakOf(l, r);
			} else {
				throw new ParseException(
						"Connectief niet herkend: " + connective, i);
			}
		} else if(s.charAt(i) == '-') {
			// Een min staat voor de not
			i++;
			Formule inner = parseFormule();
			return maakNiet(inner);
		} else {
			// In dit geval moet het wel een variabele zijn, we nemen aan
			// dat die uit letters en cijfers bestaan
			int to = i;
			while(to < s.length() && Character.isLetterOrDigit(s.charAt(to)))
				to++;
			
			// Als to == i dan staat hier blijkbaar geen letter of cijfer
			if(to == i) {
				throw new ParseException("Variabele verwacht", i);
			}
			
			// Maak resultaat aan
			String variable = s.substring(i, to);
			i = to;
			return maakVariabele(variable);
		}
	}
	
	/**
	 * Meest simpele formule die alleen uit een variabele bestaat
	 * @param variabele De naam van de variabele
	 */
	private static Formule maakVariabele(String variabele) {
		  return new Propositie (variabele);
	}
	
	/**
	 * Maak een formule die de negatie is van een gegeven formule
	 * @param inner De formule waar de not voor staat
	 */
	private static Formule maakNiet(Formule inner) {
		  return new Negatie (inner);
	}
	
	/**
	 * Maak een conjuctie van twee formules
	 * @param l Linker argument van conjuctie
	 * @param r Rechter argument van conjuctie
	 */	
	private static Formule maakEn(Formule l, Formule r) {
		  return new Conjunctie (l,r); 
	}

	/**
	 * Maak een disjuctie van twee formules
	 * @param l Linker argument van disjuctie
	 * @param r Rechter argument van disjuctie
	 */	
	private static Formule maakOf(Formule l, Formule r) { 
		  return new Disjunctie (l,r); 
	}
}