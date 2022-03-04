package dev.spideria.logica;

public enum NumeroCarta {

	J,
	Q,
	K;
	
	public static NumeroCarta parseNumeroCarta(int numeroCarta) {
		switch(numeroCarta) {
		case 11:
			return J;
		case 12:
			return Q;
		case 13:
			return K;
		default:
			return null;
		}
	}
}
