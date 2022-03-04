package dev.spideria.utility;

import java.util.ArrayList;
import java.util.Objects;

import dev.spideria.logica.Carta;
import dev.spideria.logica.NumeroCarta;
import dev.spideria.logica.Seme;

public class StampeUtils {

	private static String SPAZIO = " ";
	private static String CARTA_COPERTA = " COPERTA ";
		
	private static Carta [][] convertiListInArray(ArrayList<ArrayList<Carta>> pile) {
		Carta [][] matriceTrasposta = new Carta[pile.size()][]; 
		for(int i=0; i< matriceTrasposta.length;i++) {
			matriceTrasposta[i] = new Carta[ pile.get(i).size()];
    		for(int j=0;j< pile.get(i).size();j++) {
    			matriceTrasposta[i][j]=  pile.get(i).get(j);
    		}
    	}
		return matriceTrasposta;
	}
	
	private static Carta[][] transponiMatrice(ArrayList<ArrayList<Carta>> pile) {
		Carta[][] matrice = convertiListInArray(pile);
	    int maxNumRighe = 0;
	    for (Carta[] riga : matrice)
	        if (riga.length > maxNumRighe)
	            maxNumRighe = riga.length;
	    Carta[][] matriceTansposta = new Carta[maxNumRighe][];
	    for (int i = 0; i < matriceTansposta.length; i++) {
	        for (maxNumRighe = matrice.length; maxNumRighe > 0; maxNumRighe--)
	            if (matrice[maxNumRighe - 1].length > i)
	                break;
	        matriceTansposta[i] = new Carta[maxNumRighe];
	        for (int j = 0; j < maxNumRighe; j++)
	            if (i < matrice[j].length)
	                matriceTansposta[i][j] = matrice[j][i];
	    }
	    return matriceTansposta;
	}
	public static void stampaVisualeGiocatore(ArrayList<ArrayList<Carta>> pile) {
		Carta[][] matriceTransposta = transponiMatrice(pile);
		
		for(int i=0;i<matriceTransposta.length;i++) {
			for(int j=0;j<matriceTransposta[i].length;j++) {
				if(Objects.nonNull(matriceTransposta[i][j])) {
					if(matriceTransposta[i][j].isVisibile()) {
						Seme parseSeme = Seme.parseSeme(matriceTransposta[i][j].getSeme());
						String numeroCarta = String.valueOf( matriceTransposta[i][j].getValore());
						if(matriceTransposta[i][j].getValore()>10) {
							numeroCarta = NumeroCarta.parseNumeroCarta(matriceTransposta[i][j].getValore()).toString(); 
						}
						System.out.print(SPAZIO + numeroCarta + SPAZIO + parseSeme);
					}
					else {
						System.out.print(CARTA_COPERTA);
					}
				}
				else {
					System.out.print(CARTA_COPERTA);
				}
			}
			System.out.println("");
		}
	}
}
