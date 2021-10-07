package dev.spideria.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class Tavolo 
{
	private ArrayList<ArrayList<Carta>> pile;
	private Mazzo mazzo;
	private int righe;
	private int colonne;
	
	public Tavolo()
	{
		pile = new ArrayList<ArrayList<Carta>>();
		mazzo = new Mazzo();
		
		costruisciTavoloVuoto();	// OK 
		
		initCarte();	// OK 
		
		distribuisciCarteCoperte(); // OK 
		
		System.out.println(" ");
		displayPile();	// OK
		
		distribuisciCarteScoperte();
		
		//debug();
	
	}
	
	public void distribuisciCarteScoperte()
	{
	
	}

	public void distribuisciCarteCoperte()
	{
		System.out.println("Metodo distribuisciCarteCoperte() ");
		
		List<Carta> temp = this.mazzo.getCarte();
		int cont = 0;
		int k = 0;
		int key = temp.size();
		
		for(int i=0;i<this.pile.size() && cont<44 && k<key;i++)
		{
			for(int j=0;j<this.pile.get(i).size() && cont<44 && k<key;j++)
			{
				if(this.pile.get(i).get(j) == null)
				{
					Carta c = temp.get(k);
					this.mazzo.rimuoviCarta(c);
					aggiornaCarta(c,i,j);
					k++;
					cont++;
				}
			}
		}
	
	} 
		
		
	public void aggiornaCarta(Carta c,int i,int j)
	{
		System.out.println("Metodo aggiornaCarta() ");
		
		
		this.pile.get(i).add(j, c);
		
		System.out.println("Ho aggiunto la Carta " + c.getSeme() + " " + c.getValore() + " alla pila");
	}

	public int generaRandom(int size) 
	{
		Random rand = new Random();
		return rand.nextInt(size);
	}

	public void initCarte() 
	{
		/*System.out.println(" ");
		System.out.println("Metodo initCarte() ");	*/
		
		this.mazzo.mischia();
		
		/*for(int i=0;i<this.mazzo.getCarte().size();i++)
			this.mazzo.stampaDebug(this.mazzo.getCarte().get(i).getSeme(), this.mazzo.getCarte().get(i).getValore());	*/
		
		
		//System.out.println("Nel mazzo sono presenti " + this.mazzo.getCarte().size() + " carte.");
		 
	}
	
	
	// OK
	public void costruisciTavoloVuoto()
	{
		
		// System.out.println("PRIMA");
		
		int contoNull = 0;
		
		ArrayList<Carta> a = new ArrayList<Carta>();
		
		for(int i = 0; i<10 ; i++)
		{
			a.add(null);
			//contoNull++;
		}
		
		for(int j=0;j<4;j++)
		{
			this.pile.add(a);
			contoNull+=10;
		}
		
		/*for(int i=0;i<pile.size();i++)
			System.out.println(pile.get(i));
		
		System.out.println(" ");
		
		System.out.println("DOPO");	*/
		
		a = new ArrayList<Carta>();
		for(int i=0;i<4;i++)
		{
			a.add(null);
			contoNull++;
		}
			
		
		this.pile.add(a);
	
		/* 
		for(int i=0;i<pile.size();i++)
			System.out.println(pile.get(i));
		
		System.out.println("La dimensione della pila e': " + contoNull);	*/
		
		}

	public void displayPile()
	{
		System.out.println("Metodo displayPile() ");
		int cont = 0;
		boolean stop = false;
		
		for(int i=0;i<this.pile.size() && !stop;i++)
		{
			for(int j=0;j<this.pile.get(i).size();j++)
			{
				if(this.pile.get(i).get(j) == null)
				{
					stop = true;
					break;
				}
				this.mazzo.stampaDebug(this.pile.get(i).get(j).getSeme(), this.pile.get(i).get(j).getValore());
				//System.out.println(this.pile.get(i).get(j).getSeme() + " " + this.pile.get(i).get(j).getValore() + " ");
				cont++;
			}
				
		}
		
		System.out.println("Nella pila ci sono " + cont + " carte.");
	}
	
	public void debug()
	{
		System.out.println("Metodo debug() ");
		
		System.out.println("STAMPO IL MAZZO DI CARTE");
		for(int i=0;i<this.mazzo.getCarte().size();i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(),this.mazzo.getCarta(i).getValore());
		
		
		System.out.println("STAMPO LA PILA ");
		for(int j=0;j<this.pile.size();j++)
		{
			for(int k=0;k<this.pile.get(j).size();k++)
			{
				int seme = this.pile.get(j).get(k).getSeme();
				System.out.println("Seme : " + seme);
				int valore = this.pile.get(j).get(k).getValore();
				System.out.println("Valore : " + valore);
				Carta c = new Carta(seme,valore);
				this.mazzo.stampaDebug(c.getSeme(),c.getValore());
			}
		}

		System.out.println("FINE DEL METODO debug()");
	}	
}
