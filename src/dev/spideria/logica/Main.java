package dev.spideria.logica;

import java.util.Scanner;

public class Main 
{
	
	public static void main(String[] args) 
	{
		
		Tavolo t = new Tavolo();
		
		
		while(t.isInGame())
		{
			int iterazioni = 0;
			
			System.out.println(" ");
			System.out.println(" BENVENUTO IN SPIDER ");
			System.out.println(" ");
			
			System.out.println("Inizio Partita!");
			System.out.println(" ");
			
			System.out.println("Premi 1 per vedere lo stato della pila.");
			System.out.println("Premi 2 per aggiungere 10 carte alle pila.");
			System.out.println("Premi 3 per spostare delle carte.");
			
			Scanner input = new Scanner(System.in);
			System.out.println(" ");
			System.out.println("Inserici un valore : ");
			int x = input.nextInt();
			
			
			if(x == 1 && iterazioni == 0)
			{
				t.initStampaPilaLogica();
				
				t.displayPila();
				
				iterazioni++;
				
			}
			else if( x == 2 && iterazioni == 0)
			{
				t.daiCarte();
				
				iterazioni++;
			}
			
			input.close();
			
			t.setInGame(false);
		}	
		
	}
	
	
}
