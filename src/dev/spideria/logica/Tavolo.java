package dev.spideria.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class Tavolo
{
	private ArrayList<ArrayList<Carta>> pile;
	private Mazzo mazzo;
	private int righe = 5;
	private int colonne = 10;

	// Salvo il numero di elementi della pila CORRENTE
	private long elementiPila = 0;

	public Tavolo() 
	{
		pile = new ArrayList<ArrayList<Carta>>();
		mazzo = new Mazzo();

		initCarte(); // OK

		costruisciTavolo();

		initDistribuisciCarte();
		
		//stampaPilaDebug();
		
		fixVisibility();
		
		stampaPilaLogica();	
		
		displayPila();
		
		stampaPilaDebug();

	}
	
	public void fixVisibility()
	{
		this.pile.get(0).get(5).setVisibile(true);
		this.pile.get(1).get(5).setVisibile(true);
		this.pile.get(2).get(5).setVisibile(true);
		this.pile.get(3).get(5).setVisibile(true);
		
		for(int i=4;i<10;i++)
				this.pile.get(i).get(4).setVisibile(true);
	}
	
	public void displayPila()
	{
		System.out.println(" ");
		System.out.println("Metodo displayPila() ");
		
		int num = 0;
		int index = 0;
		int numCarte = 0;
		 
		while(index <= righe )
		{
			//System.out.println("index = " + index + " size di index = " + this.pile.get(index).size());

			for(int j=0;j<this.pile.size();j++)
			{
				if(index == 5 && j>3)
					break;
				
				Carta c = this.pile.get(j).get(index);
				
				/* System.out.println(" ");
				System.out.print("Siamo in posizione " + index + " " + j + " -> ");
				System.out.println(c.toString()); */ 

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
				
				num++;
				
				//System.out.println(" ");
				
				if(num == 10)
				{
					System.out.println(" ");
					num = 0;
				}
				
				numCarte++;

		}
			
			index++;
			
			//System.out.println("index = " + index);
			
			//DEVO CONTROLLARE SE I VALORI DI DISPLAY PILA SONO EQUIVALENTI ALLA PILA REALE
		}
		
		System.out.println(" ");
		System.out.println("numCarte = " + numCarte);

	}

	public void aggiornaCarta(Carta c, int i, int j) {

		Carta x = this.mazzo.getCarta(0);

		this.pile.get(i).get(j).setSeme(x.getSeme());
		this.pile.get(i).get(j).setValore(x.getValore());
		this.mazzo.getCarte().remove(x);
	}

	public int generaRandom(int size) {
		Random rand = new Random();
		return rand.nextInt(size);
	}

	public void initCarte() {
		System.out.println(" ");
		System.out.println("Metodo initCarte() ");

		this.mazzo.mischia();
	}

	public void initDistribuisciCarte() 
	{
		System.out.println(" ");
		System.out.println("Metodo initDistribuisciCarte() ");

		int iterazioni = 0;
		int elementiPila = (int) initNumeroElementiPila();
		int cont = 0;

		Carta card = new Carta();

		for (int i = 0; i < this.pile.size() && cont < elementiPila; i++) {
			elementiPila = (int) initNumeroElementiPila();

			for (int j = 0; j < this.pile.get(i).size() && cont < elementiPila; j++) {
				Carta c = this.pile.get(i).get(j);

				this.aggiornaCarta(c, i, j);

				iterazioni++;
				// System.out.println("Carte Aggiornate = " + iterazioni + ".");

				cont++;
			}
		}

		// System.out.println("Fine metodo initDistribuisciCarte() ");
		// System.out.println("Sono state aggiornate " + iterazioni + " carte.");
		this.elementiPila = this.pile.stream().flatMap(Collection::stream).count();

	}

	public ArrayList<Carta> restituisciLista(int righe) {

		ArrayList<Carta> list = new ArrayList<Carta>();

		for (int i = 1; i <= righe; i++) {

			if (i == righe - 1)
				list.add(new Carta(false));
			else
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

		//initStampaPila();

	}

	public long initNumeroElementiPila() {
		return this.pile.stream().flatMap(Collection::stream).count();
	}

	public int numeroElementiMazzo(List<Carta> list) {
		int num = 0;

		for (int i = 0; i < list.size(); i++)
			if (list.get(i) != null)
				num++;

		return num;
	}

	public void stampaMazzo() {

		for (int i = 0; i < this.mazzo.getCarte().size(); i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(), this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");
	}

	public void stampaPilaLogica() 
	{
		System.out.println(" ");
		System.out.println("Metodo stampaPilaLogica() ");
		System.out.println(" ");

		int index = 0;
		int num = 0;
		
		while(index <= righe )
		{
			//System.out.print("index = " + index + " ");
		

			for(int j=0;j<this.pile.size();j++)
			{
				if(index == 5 && j>3)
					break;
				
				//System.out.print("Siamo in posizione " + index + " " + j + " -> ");
				
				Carta c = this.pile.get(j).get(index);
				
				//System.out.print(c.toString() + " ");
				
				if (c.isVisibile())
					System.out.print("TRUE" + " ");
				else
					System.out.print("FALSE" + " ");

				num++;
				
				//System.out.println(" ");

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}
			}
		
			index++;
		}
		
		System.out.println(" ");
	}

	public void stampaTutto() {
		System.out.println(" ");
		System.out.println("Metodo stampaTutto() ");
		System.out.println(" ");

		System.out.println("STAMPO IL MAZZO DI CARTE");
		for (int i = 0; i < this.mazzo.getCarte().size(); i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(), this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");

		System.out.println(" ");
		System.out.println("STAMPO LA PILA ");

		boolean cond = true;

		for (int i = 0; i < this.pile.size() && cond; i++) {
			int num = 0;

			if (num == 10)
				System.out.println(" ");

			for (int j = 0; j < this.pile.get(i).size() && cond; j++) {
				Carta c = this.pile.get(i).get(j);

				if (c == null || (c.getSeme() == 0 || c.getValore() == 0)) {
					cond = false;
					break;
				}

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

				num++;

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}
			}

			num = 0;
		}

		System.out.println(" ");
		System.out.println("Nella pila ci sono " + this.elementiPila + " carte.");
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
	

	public void stampaPila() 
	{
		System.out.println(" ");
		System.out.println("Metodo stampaPila() ");
		System.out.println(" ");

		boolean cond = true;
		int num = 0;
		
		for (int i = 0; i < this.pile.size() && cond; i++) 
		{
			for (int j = 0; j < this.pile.get(i).size() && cond; j++) 
			{
				Carta c = this.pile.get(i).get(j);

				//System.out.print("Siamo in posizione " + i + " " + j +" -> ");
				//System.out.println(c.toString());

				if ((c == null) || (c.getSeme() == 0 || c.getValore() == 0)) {
					cond = false;
					break;
				}

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

				num++;

				if (num == 10) {
					System.out.println(" ");
					num = 0;
				}
			}
		}

		System.out.println(" ");
		System.out.println("Nella pila ci sono " + this.elementiPila + " carte.");
	}

	public void stampaPilaDebug() 
	{
		System.out.println(" ");
		System.out.println("Metodo stampaPilaDebug() ");
		System.out.println(" ");

		System.out.println("La pila e' composta da " + this.pile.size() + " array list");
		System.out.println(" ");

		boolean cond = true;
		int numCarte = 0;

		for (int i = 0; i < this.pile.size() && cond; i++) {

			int num = 0;

			if (num == 10)
				System.out.println(" ");

			for (int j = 0; j < this.pile.get(i).size() && cond; j++) {
				Carta c = this.pile.get(i).get(j);

				if (c == null || (c.getSeme() == 0 || c.getValore() == 0)) {
					cond = false;
					break;
				}

				System.out.print("Siamo in posizione " + i + " " + j + " -> ");
				
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

				num++;
				numCarte++;
				
				System.out.println(" " + " visible = " + c.isVisibile() + ".");
				System.out.println(" ");
				
				
				if (num == 10) {
					//System.out.println(" ");
					num = 0;
				}
			}

			num = 0;
		}
		
		System.out.println("numCarte = " + numCarte);
	}
	
	

}
