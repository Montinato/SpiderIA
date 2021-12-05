package dev.spideria.logica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import dev.spideria.ia.Cella;
import dev.spideria.ia.GameAI;

public class Main 
{
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Tavolo tavolo = new Tavolo();
		GameAI ai = new GameAI();
		
		// Riporto su questo file i fatti che genero 
		PrintWriter out = new PrintWriter("input/facts.txt");
		
		
		while(tavolo.fineGioco()) 	//tavolo.fineGioco() -> Metodo di supporto che controlla lo stato del gioco
		{
			for(int i=0;i<tavolo.getPila().size();i++)
			{
				for(int j=0;j<tavolo.getPila().get(i).size();j++)
				{
					Carta c = tavolo.getPila().get(i).get(j);
					int num = tavolo.elementiScala(i, j, c.getValore());
					Cella cella = new Cella(i,j,c.getValore(),num);
					ai.addFacts(cella);
					
					// Aggiungo la cella creata al file facts.txt
					out.println(cella);
				}
			}
			
			ai.loadFacts();
			
			// Un metodo che mi restituisce gli AnswerSet -> getAnswerSets()
			ai.getAnswerSets();
			
			// Chiudo l'oggetto PrintWriter
			out.close();
			
			// Operazioni sulle celle

		}

	
	}
	
	public void oldMain()
{
	/* Tavolo t = new Tavolo();
	 * 
	 * while(t.isInGame())
	{
		int iterazioni = 0;
		
		int contaDaiCarte = 0;
		
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
			
			t.displayPilaGioco();
			
			iterazioni++;
			
		}
		else if( x == 2 && contaDaiCarte < 5)
		{
			t.daiCarte();
			
			contaDaiCarte++;
			
			iterazioni++;
		}
		else if( x == 3 )
		{
			// METODO DI DLV PER SPOSTARE LE CARTE	
		}
		
		input.close();
		
		t.setInGame(false);
	}	
	
	*/
}

}
