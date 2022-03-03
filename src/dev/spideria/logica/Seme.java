package dev.spideria.logica;

public enum Seme {

	FIORI,
	QUADRI,
	CUORI,
	PICCHE;
	
	Seme seme;
	public static Seme parseSeme(int seme) {

		switch(seme) {
		case 1:
			return FIORI;
		case 2:
			return QUADRI;
		case 3:
			return CUORI;
		case 4:
			return PICCHE;
		default:
			return null;
		}
		
	}
	
}
