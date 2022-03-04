package dev.spideria.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import dev.spideria.utility.StampeUtils;

public class Tavolo {
	private ArrayList<ArrayList<Carta>> pile;
	private Mazzo mazzo;
	private int indexRiga = 0;
	private int indexColonna = 0;
	private boolean inGame = true;

	private int righe = 5;
	private int colonne = 10;
	public long elementiPila = 0;

	public Tavolo() {
		pile = new ArrayList<ArrayList<Carta>>();
		mazzo = new Mazzo();

		initCarte(); // Mischio le carte

		costruisciTavolo();

		initDistribuisciCarte();

		fixVisibility(); // NON COMMENTARE QUESTO

		// daiCarte();

		//initStampaPilaLogica();

		//displayPilaGioco();

		StampeUtils.stampaVisualeGiocatore(pile);
		
		//stampaPilaArrayList();
	}

	public ArrayList<ArrayList<Carta>> getPila() {
		return this.pile;
	}

	public void daiCarte() {
		this.pile.stream().forEach(list -> list.add(getCartaDaMazzo()));
	}

	public Carta getCartaDaMazzo() {
		/*
		 * System.out.print("Metodo getCartaDaMazzo() " + " ");
		 * System.out.println(this.mazzo.getCarta(0));
		 */

		this.mazzo.getCarta(0).setVisibile(true);

		return this.mazzo.getCarte().remove(0);

	}

	public void initCarte() {
		System.out.println(" ");
		System.out.println("Metodo initCarte() ");

		this.mazzo.mischia();
	}

	public long initNumeroElementiPila() {
		return this.pile.stream().flatMap(Collection::stream).count();
	}

	public void aggiornaCarta(int i, int j) {

		Carta x = this.mazzo.getCarta(0);

		this.pile.get(i).get(j).setSeme(x.getSeme());
		this.pile.get(i).get(j).setValore(x.getValore());
		this.mazzo.getCarte().remove(x);
	}

	public void initDistribuisciCarte() {
		System.out.println(" ");
		System.out.println("Metodo initDistribuisciCarte() ");

		int elementiPila = (int) initNumeroElementiPila();
		int cont = 0;

		for (int i = 0; i < this.pile.size() && cont < elementiPila + 1; i++) {
			elementiPila = (int) initNumeroElementiPila();

			for (int j = 0; j < this.pile.get(i).size() && cont < elementiPila + 1; j++) {
				this.aggiornaCarta(i, j);
				cont++;
			}
		}

		// System.out.println("Fine metodo initDistribuisciCarte() ");
		// System.out.println("Sono state aggiornate " + iterazioni + " carte.");
		this.elementiPila = this.pile.stream().flatMap(Collection::stream).count();

	}

	public boolean possoSpostareBlocco(int riga, int colonna) {

		ArrayList<Carta> colonnaDelBloccoDaSpostare = this.pile.get(riga);

		int valorePartenza = colonnaDelBloccoDaSpostare.get(colonna).getValore();
		int semePartenza = colonnaDelBloccoDaSpostare.get(colonna).getSeme();

		for (int i = colonna + 1; i < colonnaDelBloccoDaSpostare.size(); i++) {
			// if(colonnaDelBloccoDaSpostare.get(i).getSeme() != semePartenza) {
			// return false;
			// }
			if (colonnaDelBloccoDaSpostare.get(i).getValore() != valorePartenza + 1) {
				return false;
			}
			valorePartenza++;
		}
		return true;
	}

	public boolean possoIncollareBlocco(int riga, int colonna, ArrayList<Carta> carteDaIncollare) {

		ArrayList<Carta> colonnaDoveIncollare = this.pile.get(riga);

		int valoreUltimaCarta = colonnaDoveIncollare.get(colonnaDoveIncollare.size() - 1).getValore();
		int valorePrimaCartaDaIncollare = carteDaIncollare.get(0).getValore();

		if (valoreUltimaCarta == 0 || valorePrimaCartaDaIncollare == 0) {
			return false;
		}

		return valorePrimaCartaDaIncollare == valoreUltimaCarta - 1;
	}

	public boolean spostaBlocco(int rigaFrom, int colonnaFrom, int rigaTo, int colonnaTo) {

		if (!possoSpostareBlocco(rigaFrom, colonnaFrom)) {
			return false;
		}
		System.out.println("Ho controllato se posso spostare il blocco che parte dalla carta " + "I= " + rigaFrom
				+ " J= " + colonnaFrom + ".");
		ArrayList<Carta> carteDaIncollare = this.getElementiConsecutiviPila(rigaFrom, colonnaFrom);
		if (!possoIncollareBlocco(rigaTo, colonnaTo, carteDaIncollare)) {
			System.out.println("Non posso incollare il blocco che parte dalla carta " + "I= " + rigaFrom + " J= "
					+ colonnaFrom + " nella posizione I = " + rigaTo + " J = " + colonnaTo + ".");
			return false;
		}

		System.out.println("Posso incollare il blocco" + ".");
		for (int i = this.pile.get(rigaFrom).size() - 1; i >= colonnaFrom; i--) {
			
			if(i == colonnaFrom) {
				this.pile.get(rigaFrom).get(i-1).setVisibile(true);
			}
			this.pile.get(rigaFrom).remove(i);
		}
		System.out.println("Sto spostando il blocco " + carteDaIncollare.toString() + ".");
		System.out.println("Ho rimosso il blocco.");
		this.pile.get(rigaTo).addAll(carteDaIncollare);
		System.out.println("Ho incollato il blocco.");
		return true;

	}

	public ArrayList<Carta> getElementiConsecutiviPila(int riga, int colonna) {
		// si suppone che gli elementi della pila rigaFrom siano consecutivi , è una
		// semplice get
		ArrayList<Carta> colonnaDelBloccoDaSpostare = this.pile.get(riga);
		ArrayList<Carta> carteDaRestituire = new ArrayList<Carta>();
		int valorePartenza = colonnaDelBloccoDaSpostare.get(colonna).getValore();
		for (int i = colonna; i < colonnaDelBloccoDaSpostare.size(); i++) {
			carteDaRestituire.add(colonnaDelBloccoDaSpostare.get(i));
		}
		return carteDaRestituire;
	}

	public boolean chiusoScalaCompleta(int riga, int colonna) {

		ArrayList<Carta> pilaDaControllare = this.pile.get(riga);
		int valoreCorrente = 1;

		for (int i = 0; i < pilaDaControllare.size(); i++) {
			if (pilaDaControllare.get(i).getValore() != valoreCorrente) {
				return false;
			}
			valoreCorrente++;
		}
		return true;
	}

	public ArrayList<Carta> restituisciLista(int righe) {

		ArrayList<Carta> list = new ArrayList<Carta>();

		for (int i = 1; i <= righe; i++) {

			list.add(new Carta(false));
		}
		return list;

	}

	public void costruisciTavolo() {
		System.out.println(" ");
		System.out.println("Metodo costruisciTavolo() ");

		// Costruisco la pila inserendo per ogni cella una Carta c = new Carta()
		// Per ogni colonna e per ogni riga

		for (int i = 0; i < 4; i++)
			this.pile.add(i, restituisciLista(6));

		int size = this.pile.size();
		for (int i = size; i < size + 6; i++)
			this.pile.add(i, restituisciLista(5));

		// MI SERVE PER DISTRIBUIRE LE CARTI RESTANTI

		indexRiga = this.pile.size() - 1;
		indexColonna = this.pile.get(indexRiga).size(); // Posizione corrente in cui inserire ( NO -1 )

		// System.out.println("L'ultimo elemento della pila e' stato inserito in
		// posizione : " + indexRiga + " " + indexColonna + " ");
	}

	public boolean fineGioco() {
		boolean ans = false;

		if (this.pile.size() == 0)
			return true;

		for (int i = 0; i < this.getPila().size(); i++) {
			for (int j = 0; j < this.getPila().get(i).size(); j++) {
				if (this.elementiScala(i, j, this.getPila().get(i).get(j).getValore()) > 0)
					ans = true;
			}
		}

		return ans;
	}

	public int elementiScala(int riga, int colonna, int valore) {

		int cont = 1;
		int v = valore;

		if (v == 13) {
			// System.out.println("In posizione " + riga + " " + colonna + " gli elementi
			// della scala sono : " + cont + " . ");
			return cont;
		}

		int righePila = this.pile.size() - 1;
		int ultimo = this.pile.get(righePila).size();

		if (riga == righePila && colonna == ultimo - 1)
			inGame = false;

		for (int i = 0; i < this.pile.get(riga).size(); i++) {
			if (i > colonna) {
				if (this.pile.get(riga).get(i).getValore() == v + 1) {
					cont++;
					v++;
				} else
					break;
			}
		}

		// System.out.println("In posizione " + riga + " " + colonna + " gli elementi
		// della scala sono : " + cont + " . ");
		return cont;

	}

	public void fixVisibility() {
		System.out.println(" ");
		System.out.println("Metodo fixVisibility() ");
		System.out.println(" ");

		this.pile.get(0).get(5).setVisibile(true);
		this.pile.get(1).get(5).setVisibile(true);
		this.pile.get(2).get(5).setVisibile(true);
		this.pile.get(3).get(5).setVisibile(true);

		for (int i = 4; i < 10; i++) {
			this.pile.get(i).get(4).setVisibile(true);
		}
	}

	public int numCarteMazzo() {
		return this.mazzo.getCarte().size();
	}

//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	
///////////////////////////////////////////////    FUNZIONI DI STAMPA    ////////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////	

	public void displayPilaGioco() {
		System.out.println(" ");
		System.out.println("Metodo displayPilaGioco() ");
		System.out.println(" ");

		int num = 0;
		int index = 0;
		int numCarte = 0;

		while (index <= righe) {
			// System.out.println("index = " + index + " size di index = " +
			// this.pile.get(index).size());

			for (int j = 0; j < this.pile.size(); j++) {
				if (index == righe && j > 3)
					break;

				Carta c = this.pile.get(j).get(index);

				/*
				 * System.out.println(" "); System.out.print("Siamo in posizione " + index + " "
				 * + j + " -> "); System.out.println(c.toString());
				 */

				if (c.isVisibile()) {
					if (c.getValore() == 11)
						System.out.print("J" + " ");
					else if (c.getValore() == 12)
						System.out.print("Q" + " ");
					else if (c.getValore() == 13)
						System.out.print("K" + " ");
					else
						System.out.print(c.getValore() + " ");

					if (c.getSeme() == 1 && num != 9)
						System.out.print("[Fiori] " + " | ");
					else if (c.getSeme() == 2 && num != 9)
						System.out.print("[Quadri]" + " | ");
					else if (c.getSeme() == 3 && num != 9)
						System.out.print("[Cuori]" + " | ");
					else if (c.getSeme() == 4 && num != 9)
						System.out.print("[Picche]" + " | ");

					if (c.getSeme() == 1 && num == 9)
						System.out.print("[Fiori] ");
					else if (c.getSeme() == 2 && num == 9)
						System.out.print("[Quadri]");
					else if (c.getSeme() == 3 && num == 9)
						System.out.print("[Cuori]");
					else if (c.getSeme() == 4 && num == 9)
						System.out.print("[Picche]");
				} else
					System.out.print("-" + "            ");

				num++;

				// System.out.println(" ");

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}

				numCarte++;

			}

			index++;

			// System.out.println("index = " + index);

			// DEVO CONTROLLARE SE I VALORI DI DISPLAY PILA SONO EQUIVALENTI ALLA PILA REALE
		}

		System.out.println(" ");
		this.elementiPila = this.pile.stream().flatMap(Collection::stream).count();
		System.out.println("numCarte = " + this.elementiPila);
		System.out.println(" ");

	}

	public void stampaMazzo() {

		for (int i = 0; i < this.mazzo.getCarte().size(); i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(), this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");
	}

	public void stampaPilaLogica() {
		System.out.println(" ");
		System.out.println("Metodo stampaPilaLogica() ");
		System.out.println(" ");

		int index = 0;
		int num = 0;

		while (index <= righe) {
			// System.out.print("index = " + index + " ");

			for (int j = 0; j < this.pile.size(); j++) {
				if (index == 6 && j > 3)
					break;

				// System.out.print("Siamo in posizione " + index + " " + j + " -> ");

				Carta c = this.pile.get(j).get(index);

				// System.out.print(c.toString() + " ");

				if (c.isVisibile())
					System.out.print("TRUE" + " ");
				else
					System.out.print("FALSE" + " ");

				num++;

				// System.out.println(" ");

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}
			}

			index++;
		}

		System.out.println(" ");
	}

	public void initStampaPilaLogica() {
		// DA USARE PER MOSTRARE LE PRIME 54 CARTE
		System.out.println(" ");
		System.out.println("Metodo initStampaPilaLogica() ");
		System.out.println(" ");

		int index = 0;
		int num = 0;

		while (index <= righe) {
			// System.out.print("index = " + index + " ");

			for (int j = 0; j < this.pile.size(); j++) {
				if (index == 5 && j > 3)
					break;

				// System.out.print("Siamo in posizione " + index + " " + j + " -> ");

				Carta c = this.pile.get(j).get(index);

				// System.out.print(c.toString() + " ");

				if (c.isVisibile())
					System.out.print("TRUE" + " ");
				else
					System.out.print("FALSE" + " ");

				num++;

				// System.out.println(" ");

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}
			}

			index++;
		}

		System.out.println(" ");
	}

	public void initStampaPila() {

		System.out.println(" ");

		System.out.println("Metodo initStampaPila() ");
		System.out.println(" ");

		boolean cond = true;

		for (int i = 0; i < this.pile.size() && cond; i++) {
			int num = 0;

			if (num == 10)
				System.out.println(" ");

			for (int j = 0; j < this.pile.get(i).size() && cond; j++) {
				Carta c = this.pile.get(i).get(j);

				if (c == null) {
					cond = false;
					break;
				} else {

					if (c.getValore() == 11)
						System.out.print("J" + " ");
					else if (c.getValore() == 12)
						System.out.print("Q" + " ");
					else if (c.getValore() == 13)
						System.out.print("K" + " ");
					else
						System.out.print(c.getValore() + " ");

					if (c.getSeme() == 1 && num != 9)
						System.out.print("[Fiori] " + " | ");
					else if (c.getSeme() == 2 && num != 9)
						System.out.print("[Quadri]" + " | ");
					else if (c.getSeme() == 3 && num != 9)
						System.out.print("[Cuori]" + " | ");
					else if (c.getSeme() == 4 && num != 9)
						System.out.print("[Picche]" + " | ");

					if (c.getSeme() == 1 && num == 9)
						System.out.print("[Fiori] ");
					else if (c.getSeme() == 2 && num == 9)
						System.out.print("[Quadri]");
					else if (c.getSeme() == 3 && num == 9)
						System.out.print("[Cuori]");
					else if (c.getSeme() == 4 && num == 9)
						System.out.print("[Picche]");
				}

				num++;

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}

			}

			num = 0;
		}

		System.out.println("Nella pila ci sono " + this.elementiPila + " carte.");

	}

	public void stampaPilaArrayList() {
		System.out.println(" ");
		System.out.println("Metodo stampaPilaArrayList() ");
		System.out.println(" ");

		int num = 0;

		for (int i = 0; i < this.pile.size(); i++) {
			for (int j = 0; j < this.pile.get(i).size(); j++) {
				Carta c = this.pile.get(i).get(j);

				if (c.isVisibile()) {
					if (c.getValore() == 11)
						System.out.print(" J" + " ");
					else if (c.getValore() == 12)
						System.out.print(" Q" + " ");
					else if (c.getValore() == 13)
						System.out.print(" K" + " ");
					else if (c.getValore() != 0)
						System.out.print(" " + c.getValore() + " ");

					if (c.getSeme() == 1 && num != 9)
						System.out.print("[Fiori] " + " | ");
					else if (c.getSeme() == 2 && num != 9)
						System.out.print("[Quadri]" + " | ");
					else if (c.getSeme() == 3 && num != 9)
						System.out.print("[Cuori]" + " | ");
					else if (c.getSeme() == 4 && num != 9)
						System.out.print("[Picche]" + " | ");

					if (c.getSeme() == 1 && num == 9)
						System.out.print("[Fiori] ");
					else if (c.getSeme() == 2 && num == 9)
						System.out.print("[Quadri]");
					else if (c.getSeme() == 3 && num == 9)
						System.out.print("[Cuori]");
					else if (c.getSeme() == 4 && num == 9)
						System.out.print("[Picche]");
				} else
					System.out.print("-" + "            ");

			}

			System.out.println(" ");
		}

		System.out.println(" ");
		this.elementiPila = this.pile.stream().flatMap(Collection::stream).count();
		System.out.println("Nella pila ci sono " + this.elementiPila + " carte.");
	}

	public void stampaTavoloInColonna() {
		System.out.println("Metodo stampaTavoloInColonna() ");

		for (int i = 0; i < this.pile.size(); i++) {

			for (int j = 0; j < this.pile.get(i).size(); j++) {
				System.out.println(this.pile.get(i).get(j).stampaCarta());
			}
			System.out.println("\n");
		}
	}

	public void stampaTavolo() {

		for (int k = 0; k < this.pile.size(); k++) {

			for (int i = 0; i < this.pile.size(); i++) {
				if (k < this.pile.get(i).size()) {
					System.out.print(this.pile.get(i).get(k).stampaCarta());
				} else {
					System.out.print("|           |  ");
				}
			}
			System.out.println("\n");
		}
	}

}
