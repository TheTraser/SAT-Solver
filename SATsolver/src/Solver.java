import java.util.TreeSet;

public class Solver {

	public static void main(String[] args) throws Exception {

		// Roep parser aan om van .txt bestand een String te maken
		long start = System.nanoTime();
		Parser sudokustring = Parser.vanBestand("MakkelijkeSudoku.txt");
		Formule sudoku = sudokustring.parseFormule();

		// Creeër nieuwe valuatie om sudoku mee op te lossen en print oplossing
		// voor sudoku
		Valuatie valuatie = new Valuatie();
		Valuatie vervulbaar = vervulbaar(sudoku, valuatie);
		System.out.println("Opgeloste sudoku:" + "\n");
		Sudoku.printOplossing(vervulbaar);
		long time = System.nanoTime() - start;
		long milliseconds = time/1000000;
		System.out.println(milliseconds);

	}

	// Voeg alle variabelen toe aan TreeSet en roep recursieve methode aan
	public static Valuatie vervulbaar(Formule sudoku, Valuatie val) {
		TreeSet<String> boom = new TreeSet<String>();
		sudoku.verzamel(boom);
		return vervulbaarRecursief(sudoku, boom, val);
	}

	// Recursieve functie om de sudoku mee op te lossen
	public static Valuatie vervulbaarRecursief(Formule sudoku, TreeSet<String> boom, Valuatie val) {
		if (boom.isEmpty()) {
			return val;
		} else {
			String variabele = boom.pollFirst();
			boom.remove(variabele);
			val.voegToe(variabele, true);

			if (sudoku.waar(val)) {
				Valuatie resultaat = vervulbaarRecursief(sudoku, boom, val);
				if (resultaat != null) {
					return resultaat;
				}
			}
			val.verwijder(variabele);
			val.voegToe(variabele, false);
			
			if (sudoku.waar(val)) {
				Valuatie resultaat = vervulbaarRecursief(sudoku, boom, val);
				if (resultaat != null) {
					return resultaat;
				}
			}
			val.verwijder(variabele);
			boom.add(variabele);
			return null;
		}
	}
}
