public class Sudoku {

	/**
	 * Genereer een aantal formules voor sudoku's om onze SAT-solver mee te testen
	 */
	public static void genereer() {
		// Lege sudoku
		System.out.println("Lege sudoku");
		System.out.println(formula());

		// Vrij makkelijke sudoku
		System.out.println("Makkelijke sudoku");
		int[][] logica = { 
				{ 0, 8, 1, 0, 4, 3, 7, 2, 9 },
				{ 0, 7, 0, 1, 0, 5, 4, 8, 3 },
				{ 3, 4, 0, 0, 2, 0, 0, 0, 0 },
				{ 0, 0, 0, 9, 8, 0, 3, 5, 2 },
				{ 9, 5, 3, 0, 0, 1, 0, 0, 4 },
				{ 0, 0, 0, 5, 0, 0, 0, 7, 0 },
				{ 1, 6, 8, 4, 0, 0, 0, 3, 0 },
				{ 2, 0, 0, 3, 0, 0, 0, 4, 0 },
				{ 7, 0, 4, 8, 0, 2, 5, 9, 6 } };
		System.out.println(formula(logica));

		// Heel moeilijke sudoku
		System.out.println("Moeilijke sudoku");
		int[][] moeilijk = {
				{ 0, 2, 0, 0, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 6, 0, 0, 0, 0, 3 },
				{ 0, 7, 4, 0, 8, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 3, 0, 0, 2 },
				{ 0, 8, 0, 0, 4, 0, 0, 1, 0 },
				{ 6, 0, 0, 5, 0, 0, 0, 0, 0 },
				{ 0, 0, 0, 0, 1, 0, 7, 8, 0 },
				{ 5, 0, 0, 0, 0, 9, 0, 0, 0 },
				{ 0, 0, 0, 0, 0, 0, 0, 4, 0 } };
		System.out.println(formula(moeilijk));
	}
	
	/**
	 * Print de oplossing voor de sudoku gegeven de valuatie
	 * @param val De valuatie die de sudoku-formule waar maakt
	 * @throws Exception Wanneer dit geen geldige oplossing is
	 */
	static void printOplossing(Valuatie val) throws Exception {
		for (int rij = 0; rij < 9; rij++) {
			for (int kolom = 0; kolom < 9; kolom++) {
				boolean foundOne = false;
				for(int v = 1; v <= 9; v++) {
					String var = p(rij, kolom, v);
					if(!val.bevatVariabele(var)) {
						throw new Exception("Variabele " + var + " niet gevonden");
					}
					else if(val.geefWaarde(var)) {
						if(foundOne) {
							throw new Exception("Geen unieke oplossing");
						} else {
							System.out.print(v);
							foundOne = true;
						}
					}
				}
				if(!foundOne) {
					throw new Exception("Geen oplossing gevonden");
				}
			}
			System.out.println();
		}
	}

	/* Code hieronder is niet zo netjes, maar genereert de correcte
	 * formules voor de regels van een sudoku
	 */
	
	public static String p(int rij, int kolom, int v) {
		return "p" + rij + kolom + v;
	}

	public static String formula() {
		String s = null;
		for (int rij = 0; rij < 9; rij++) {
			for (int kolom = 0; kolom < 9; kolom++) {
				s = addAnd(s, formula(rij, kolom));
			}
		}
		return s;
	}

	public static String formula(int[][] sudoku) {
		String s = null;
		for (int rij = 0; rij < 9; rij++) {
			for (int kolom = 0; kolom < 9; kolom++) {
				if (sudoku[rij][kolom] != 0) {
					s = addAnd(s, p(rij, kolom, sudoku[rij][kolom]));
				}
			}
		}
		s = addAnd(s, formula());
		return s;
	}

	public static String formula(int rij, int kolom) {
		String s = null;
		s = addAnd(s, somethingatposition(rij, kolom));
		for (int v = 1; v <= 9; v++) {
			s = addAnd(s, uniqueinrow(rij, kolom, v));
			s = addAnd(s, uniqueincolumn(rij, kolom, v));
			s = addAnd(s, uniqueinpart(rij, kolom, v));
			s = addAnd(s, uniqueatposition(rij, kolom, v));
		}
		return s;
	}

	public static String somethingatposition(int rij, int kolom) {
		String s = null;
		for (int v = 1; v <= 9; v++) {
			s = addOr(s, p(rij, kolom, v));
		}
		return s;
	}

	public static String uniqueinrow(int rij, int kolom, int v) {
		String s = null;
		for (int kolom1 = 0; kolom1 < 9; kolom1++) {
			if (kolom1 != kolom) {
				s = addOr(s, p(rij, kolom1, v));
			}
		}
		s = "(-" + p(rij, kolom, v) + " \\/ -" + s + ")";
		return s;
	}

	public static String uniqueincolumn(int rij, int kolom, int v) {
		String s = null;
		for (int rij1 = 0; rij1 < 9; rij1++) {
			if (rij1 != rij) {
				s = addOr(s, p(rij1, kolom, v));
			}
		}
		s = "(-" + p(rij, kolom, v) + " \\/ -" + s + ")";
		return s;
	}

	public static String uniqueinpart(int rij, int kolom, int v) {
		String s = null;
		int rij0 = (rij / 3) * 3;
		int kolom0 = (kolom / 3) * 3;
		for (int rij1 = 0; rij1 < 3; rij1++) {
			for (int kolom1 = 0; kolom1 < 3; kolom1++) {
				if ((rij0 + rij1 != rij) | (kolom0 + kolom1 != kolom)) {
					s = addOr(s, p(rij0 + rij1, kolom0 + kolom1, v));
				}
			}
		}
		s = "(-" + p(rij, kolom, v) + " \\/ -" + s + ")";
		return s;
	}

	public static String uniqueatposition(int rij, int kolom, int v) {
		String s = null;
		for (int v1 = 1; v1 <= 9; v1++) {
			if (v1 != v) {
				s = addOr(s, p(rij, kolom, v1));
			}
		}
		s = "(-" + p(rij, kolom, v) + " \\/ -" + s + ")";
		return s;
	}

	public static String addOr(String s, String s2) {
		if (s == null)
			return s2;
		return "(" + s + " \\/ " + s2 + ")";
	}

	public static String addAnd(String s, String s2) {
		if (s == null)
			return s2;
		return "(" + s + " /\\ " + s2 + ")";
	}
}