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
	
	private int contElementiPila = 0;
	
	
	
	public Tavolo()
	{
		pile = new ArrayList<ArrayList<Carta>>();
		mazzo = new Mazzo();
		
		costruisciTavoloVuoto();	// OK 
		
		initCarte();	// OK 
		
		initDistribuisciCarte(); // OK 
		
		//debug();
	
	}
	
	public void initDistribuisciCarte()
	{
		//System.out.println("Metodo initDistribuisciCarte() ");
		
		List<Carta> temp = this.mazzo.getCarte();
		int key = temp.size();
		int cont = 0;
		int k = 0;
		
		
		for(int i=0;i<this.pile.size() && k<key;i++)
		{
			for(int j=0;j<this.pile.get(i).size()  && k<key-1;j++)
			{
				if(this.pile.get(i).get(j) == null)
				{
					
					temp = this.mazzo.getCarte();
					key = temp.size();
					/*System.out.println(" ");
					for(int a=0;a<key;a++)
						this.mazzo.stampaDebug(this.mazzo.getCarta(a).getSeme(), this.mazzo.getCarta(a).getValore());	*/
					System.out.println("La size del mazzo e': " + key + "."); 
					
					
					Carta c = temp.get(k);
					/* CONTROLLARE QUI PER L'ERRORE
					System.out.print("Elimino dal mazzo la carta ");
					this.mazzo.stampaDebug(temp.get(k).getSeme(),temp.get(k).getValore()); */
					this.mazzo.getCarte().remove(k);
					aggiornaCarta(c,i,j);
					k++;
					cont++;
					System.out.println("Gli elementi aggiornati della pila sono = " + cont);
					System.out.println(" ");
					
				}
			}
		}
		
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte." );
		//System.out.println("Nella pila ci sono " + contElementiPila + " carte");
		debug();
	} 
				
	public void aggiornaCarta(Carta c,int i,int j)
	{
		this.pile.get(i).add(j, c);
		System.out.print("Ho aggiunto alla pila la carta ");
		this.mazzo.stampaDebug(this.pile.get(i).get(j).getSeme(), this.pile.get(i).get(j).getValore());
		System.out.println(" ");
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
		System.out.println("Metodo costruisciTavoloVuoto()");
		// System.out.println("PRIMA");
		
		int contoNull = 0;
		
		ArrayList<Carta> a = new ArrayList<Carta>();
		
		for(int i = 0; i<10 ; i++)
		{
			a.add(null);
			//contoNull++;
		}
		
		for(int j=0;j<5;j++)
		{
			this.pile.add(a);
			contoNull+=10;
		}
		
		for(int i=0;i<pile.size();i++)
			//System.out.println(pile.get(i));
		
		//System.out.println(" ");
		
		//System.out.println("DOPO");	
		
		a = new ArrayList<Carta>();
		for(int i=0;i<4;i++)
		{
			a.add(null);
			contoNull++;
		}
			
		
		this.pile.add(a);
	
		 
		//for(int i=0;i<pile.size();i++)
			//System.out.println(pile.get(i));
		
		contElementiPila = contoNull;
		System.out.println("LA PILA HA " + contElementiPila + " ELEMENTI NULL");	
		
	}

	public void debug()
	{
		System.out.println(" ");
		System.out.println("Metodo debug() ");
		System.out.println(" ");
		
		System.out.println("STAMPO IL MAZZO DI CARTE");
		for(int i=0;i<this.mazzo.getCarte().size();i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(),this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");
		
		int cont = 0;
		
		System.out.println(" ");
		System.out.println("STAMPO LA PILA ");
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
	
	/*		L'HO INSERITA IN DEBUG 
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
		contElementiPila = cont;
		
	} 	*/
}
