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
	private int righe = 4;
	private int colonne = 10;
	
	private int contElementiPila = 0;
	
	
	
	public Tavolo()
	{
		pile = new ArrayList<ArrayList<Carta>>();
		mazzo = new Mazzo();
		
		initCarte();	// OK 
		
		costruisciTavolo();

		initDistribuisciCarte(); 
		
		System.out.println(" ");
		System.out.println("Metodo tavolo() ");
		
		//stampaPilaLogica();
		stampaTutto();

	}

	

	public Carta aggiornaCarta(Carta c,int i,int j)
	{
		System.out.println(" ");
		System.out.println("Metodo aggiornaCarta() ");
		
		Carta x = this.mazzo.getCarta(0);
		System.out.println("Prelevo la carta " + x.getSeme() + " " + x.getValore() + " dal mazzo.");
		this.pile.get(i).add(j, x);
		System.out.println("Aggiungo la carta " + this.pile.get(i).get(j).getSeme() + " " + this.pile.get(i).get(j).getValore() + " alla pila ");
		this.mazzo.getCarte().remove(x);
		System.out.println("Elimino dal mazzo la carta " + x.getSeme() + " " + x.getValore());
		
		System.out.println(" ");
		System.out.println(" ");
		return x;
	}

	public int generaRandom(int size) 
	{
		Random rand = new Random();
		return rand.nextInt(size);
	}

	public void initCarte() 
	{
		System.out.println(" ");
		System.out.println("Metodo initCarte() ");	
		
		this.mazzo.mischia();
	}
	
	
	public void initDistribuisciCarte()
	{
		System.out.println(" ");
		System.out.println("Metodo initDistribuisciCarte() ");
		
		int iterazioni = 0;
		int elementiPila = numeroElementiPila();
		int cont = 0;
		
		Carta card = new Carta();
		
		for(int i=0;i<this.pile.size() && cont<elementiPila;i++)
		{
			elementiPila = numeroElementiPila();
			
			for(int j=0;j<this.pile.get(i).size() && cont<elementiPila;j++)
			{
				Carta c = this.pile.get(i).get(j);
				
				if( (c.getSeme() == card.getSeme()) && ( (c.getValore() == card.getValore()) ) )
				{
					this.aggiornaCarta(c, i, j);
					
					iterazioni++;
					//System.out.println("Carte Aggiornate = " + iterazioni + ".");
					
					cont++;
				}
			}
		}
		
		System.out.println("Fine metodo initDistribuisciCarte() ");
		System.out.println("Sono state aggiornate  " + iterazioni + " carte.");
	}
	
	
	public void costruisciTavolo()
	{
		System.out.println(" ");
		System.out.println("Metodo costruisciTavolo() ");
		
		// Costruisco la pila inserendo per ogni cella una Carta c = new Carta()
		// Per ogni colonna e per ogni riga 
		
		Carta c = new Carta();
		
		
		List<Carta> list = new ArrayList<Carta>();
		
		for(int i=0;i<colonne;i++)
			list.add(c);
		
		this.pile.add((ArrayList<Carta>) list);
		
		this.pile.add(1,(ArrayList<Carta>) list);
		this.pile.add(2,(ArrayList<Carta>) list);
		this.pile.add(3,(ArrayList<Carta>) list);
		
		list = new ArrayList<Carta>();
		c = new Carta();
		
		for(int i=0;i<colonne;i++)
		{
			if(i > 3)
			{
				c = new Carta();
				c.setVisibile(true);
			}
			list.add(c);
		}
		
		this.pile.add(4,(ArrayList<Carta>) list);
		
		
		list = new ArrayList<Carta>();
		c = new Carta();
		c.setVisibile(true);
		
		for(int i=0;i<righe;i++)
			list.add(c);
		
		this.pile.add(5,(ArrayList<Carta>) list);
		
		
	}

	public int numeroElementiPila()
	{
		int num = 0;
		
		
		for(int i=0;i<this.pile.size();i++)
		{
			for(int j=0;j<this.pile.get(i).size();j++)
			{
				num++;
			}
		}
		
		return num;
	}
	
	public int numeroElementiPilaNULL()
	{
		int num = 0;
		
		
		for(int i=0;i<this.pile.size();i++)
		{
			for(int j=0;j<this.pile.get(i).size();j++)
			{	
				if( (this.pile.get(i).get(j) == null) || ( (this.pile.get(i).get(j).getSeme() == 0) || (this.pile.get(i).get(j).getValore() == 0) ) )
					num++;
			}
		}
		
		return num;
	}
	
	public int numeroElementiMazzo(List<Carta> list)
	{
		int num = 0;
		
		for(int i=0;i<list.size();i++)
			if(list.get(i) != null)
				num++;
		
		return num;
	}
	
	public void stampaMazzo()
	{
		
		for(int i=0;i<this.mazzo.getCarte().size();i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(),this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");
	}
	
	public void stampaPilaLogica()
	{
		System.out.println(" ");
		System.out.println("Metodo stampaPilaLogica() ");
		System.out.println(" ");
		
		boolean cond = true;
		
		for(int i=0;i<this.pile.size() && cond;i++)
		{
			int num = 0;
			
			if(num == 10)
				System.out.println(" ");
			
			for(int j=0;j<this.pile.get(i).size();j++)
			{
				Carta c = this.pile.get(i).get(j);
				
				if(c == null)
				{
					cond = false;
					break;
				}
				
				if(c.isVisibile())
					System.out.print(1 + " ");
				else
					System.out.print(0 + " ");
				
		        num++; 
		        
		        if(num == 10)
		        {
					System.out.println(" ");
					num = 0;
		        }
			}
			
			num = 0;
		}
	}
	
	
	
	public void stampaTutto()
	{
		System.out.println(" ");
		System.out.println("Metodo stampaTutto() ");
		System.out.println(" ");
		
		System.out.println("STAMPO IL MAZZO DI CARTE");
		for(int i=0;i<this.mazzo.getCarte().size();i++)
			this.mazzo.stampaDebug(this.mazzo.getCarta(i).getSeme(),this.mazzo.getCarta(i).getValore());
		System.out.println("Nel mazzo ci sono " + this.mazzo.getCarte().size() + " carte.");
		
		System.out.println(" ");
		System.out.println("STAMPO LA PILA ");
		
		boolean cond = true;
		
		for(int i=0;i<this.pile.size() && cond;i++)
		{
			int num = 0;
			
			if(num == 10)
				System.out.println(" ");
			
			for(int j=0;j<this.pile.get(i).size() && cond;j++)
			{
				Carta c = this.pile.get(i).get(j);
				
				if(c == null || (c.getSeme() == 0 || c.getValore() == 0))
				{
					cond = false;
					break;
				}
				
				if(c.getValore() == 11)
		        	System.out.print("J" + " ");
		        else if(c.getValore() == 12)
		        	System.out.print("Q" + " ");
		        else if(c.getValore() == 13)
		        	System.out.print("K" + " ");
		        else 
		        	System.out.print(c.getValore() + " ");
				
				if(c.getSeme() == 1 && num != 9)
		        	System.out.print("[Fiori] " + " | ");
		        else if(c.getSeme() == 2 && num != 9)
		        	System.out.print("[Quadri]" + " | ");
		        else if(c.getSeme() == 3 && num != 9)
		        	System.out.print("[Cuori]" + " | ");
		        else if(c.getSeme() == 4 && num != 9)
		        	System.out.print("[Picche]" + " | ");
				
				if(c.getSeme() == 1 && num == 9)
		        	System.out.print("[Fiori] ");
		        else if(c.getSeme() == 2 && num == 9)
		        	System.out.print("[Quadri]");
		        else if(c.getSeme() == 3 && num == 9)
		        	System.out.print("[Cuori]");
		        else if(c.getSeme() == 4 && num == 9)
		        	System.out.print("[Picche]");
		        
		        num++; 
		        
		        if(num == 10)
		        {
					System.out.println(" ");
					num = 0;
		        }
			}
			
			num = 0;
		}	

		
		int conteggio = numeroElementiPila();
		System.out.println(" ");
		System.out.println("Nella pila ci sono " + conteggio + " carte.");
	}
	
	public void stampaPila()
	{
		System.out.println(" ");
		System.out.println("Metodo stampaPila() ");
		System.out.println(" ");
		
		boolean cond = true;
		
		for(int i=0;i<this.pile.size() && cond;i++)
		{
			int num = 0;
			
			if(num == 10)
				System.out.println(" ");
			
			for(int j=0;j<this.pile.get(i).size() && cond;j++)
			{
				Carta c = this.pile.get(i).get(j);
				
				if(c == null || (c.getSeme() == 0 || c.getValore() == 0))
				{
					cond = false;
					break;
				}
				
				if(c.getValore() == 11)
		        	System.out.print("J" + " ");
		        else if(c.getValore() == 12)
		        	System.out.print("Q" + " ");
		        else if(c.getValore() == 13)
		        	System.out.print("K" + " ");
		        else 
		        	System.out.print(c.getValore() + " ");
				
				if(c.getSeme() == 1 && num != 9)
		        	System.out.print("[Fiori] " + " | ");
		        else if(c.getSeme() == 2 && num != 9)
		        	System.out.print("[Quadri]" + " | ");
		        else if(c.getSeme() == 3 && num != 9)
		        	System.out.print("[Cuori]" + " | ");
		        else if(c.getSeme() == 4 && num != 9)
		        	System.out.print("[Picche]" + " | ");
				
				if(c.getSeme() == 1 && num == 9)
		        	System.out.print("[Fiori] ");
		        else if(c.getSeme() == 2 && num == 9)
		        	System.out.print("[Quadri]");
		        else if(c.getSeme() == 3 && num == 9)
		        	System.out.print("[Cuori]");
		        else if(c.getSeme() == 4 && num == 9)
		        	System.out.print("[Picche]");
		        
		        num++; 
		        
		        if(num == 10)
		        {
					System.out.println(" ");
					num = 0;
		        }
			}
			
			num = 0;
		}	

		
		int conteggio = numeroElementiPila();
		System.out.println("Nella pila ci sono " + conteggio + " carte.");
	}
	

	
}


	
	

