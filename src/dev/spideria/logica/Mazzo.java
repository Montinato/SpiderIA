package dev.spideria.logica;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Mazzo 
{
	private int dimensione = 104; 
	private int intervallo = 14;
	private List<Carta> carte = null;
	
	public Mazzo()
	{
		
		carte = new ArrayList<Carta>();
		
		for(int j=1;j<=4;j++)
		{
			for(int k=1;k<intervallo;k++)
			{
				Carta c = new Carta(j,k);
				this.carte.add(c);
				
			}
			
		}
		
		for(int j=1;j<=4;j++)
		{
			for(int k=1;k<intervallo;k++)
			{
				Carta c = new Carta(j,k);
				this.carte.add(c);
				
			}
			
		}
		
		/* print();
		
		System.out.println("Nel mazzo sono presenti " + this.carte.size() + " carte.");
		System.out.println(" ");	*/
	}

	public void print()
	{
		int seme,valore = 0;
		
		for(int i=0;i<carte.size();i++)
		{
			if(i == 52)
			{
				System.out.println(" ");
				System.out.println("SECONDO MAZZO ");
				System.out.println(" ");
			}
			else if(i == 0)
			{
				System.out.println("PRIMO MAZZO ");
				System.out.println(" ");
			}
			
			//System.out.println("DIO");
			seme =  carte.get(i).getSeme();
			valore = carte.get(i).getValore();
			
			if(seme == 1)
	        	System.out.print("Fiori : ");
	        else if(seme == 2)
	        	System.out.print("Quadri : ");
	        else if(seme == 3)
	        	System.out.print("Cuori : ");
	        else if(seme == 4)
	        	System.out.print("Picche : ");
	        
	        
	        
	        if(valore == 11)
	        	System.out.println("J");
	        else if(valore == 12)
	        	System.out.println("Q");
	        else if(valore == 13)
	        	System.out.println("K");
	        else 
	        	System.out.println(valore);
		}
		
		
	}
	
	public List<Carta> getCarte()
	{
		return this.carte;
	}
	
	public boolean rimuoviCarta(Carta c)
	{
		/*System.out.print("Metodo rimuoviCarta(): ");
		stampaDebug(c.getSeme(), c.getValore());*/
		
		return this.carte.remove(c);
	}

	public void mischia()
	{
		Collections.shuffle(this.carte);
	}

	public Carta getCarta(int i) {
		/*System.out.print("Metodo getCarta(): ");
		stampaDebug(this.carte.get(i).getSeme(),this.carte.get(i).getValore());	*/
		return this.carte.get(i);
	}
	
	public void stampaDebug(int suit,int rank)
    {
    	if(suit == 1)
        	System.out.print("Fiori : ");
        else if(suit == 2)
        	System.out.print("Quadri : ");
        else if(suit == 3)
        	System.out.print("Cuori : ");
        else if(suit == 4)
        	System.out.print("Picche : ");
        
        
        
        if(rank == 11)
        	System.out.println("J");
        else if(rank == 12)
        	System.out.println("Q");
        else if(rank == 13)
        	System.out.println("K");
        else 
        	System.out.println(rank);
    }


}


