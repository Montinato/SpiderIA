package dev.spideria.logica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import dev.spideria.ia.Cella;
import dev.spideria.ia.GameAI;

public class Main 
{
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Tavolo tavolo = new Tavolo();
		GameAI ai = new GameAI();
		ArrayList<Posizione> destinazioni = new ArrayList<Posizione>();
		// Riporto su questo file i fatti che genero 
		PrintWriter out = new PrintWriter("input/facts.txt");
		
		
		while(tavolo.fineGioco()) 	//tavolo.fineGioco() -> Metodo di supporto che controlla lo stato del gioco
		{
			for(int i=0;i<tavolo.getPila().size();i++)
			{
				for(int j=0;j<tavolo.getPila().get(i).size();j++)
				{
					Carta c = tavolo.getPila().get(i).get(j);
					
					if(c.isVisibile()) {
						if(j==tavolo.getPila().get(i).size()-1 ) {
							destinazioni.add(new Posizione(i, j));
						}
					int num = tavolo.elementiScala(i, j, c.getValore());
					Cella cella = new Cella(i,j,c.getSeme(),c.getValore(),num); 
					ai.addFacts(cella);
					out.println(cella);		// Aggiungo la cella creata al file facts.txt
					System.out.println(cella);
					}
					
				}
			}
			
			/*
			System.out.println("inserisci riga e colonna da spostare");
			Scanner scanner = new Scanner(System.in);
			int rigaFrom = scanner.nextInt();
			int colonnaFrom= scanner.nextInt();;
			System.out.println("inserisci riga e colonna dove spostare");
			int rigaTo = scanner.nextInt(); 
			int colonnaTo= scanner.nextInt();      
			tavolo.spostaBlocco(rigaFrom, colonnaFrom, rigaTo, colonnaTo);	 */

//			tavolo.initStampaPilaLogica();
			//tavolo.stampaTavolo();
			tavolo.displayPilaGioco();
			tavolo.stampaPilaArrayList();
			//tavolo.stampaTavoloInColonna();
			
			ai.loadFacts();
			
			/*Funzionamento Gioco:

				 1.  Prenderò in considerazione i predicati: scelgo(I,J).	 ( Saranno gli elementi in posizione I,J che proverò a spostare )

				 2.  Dove li andrò a spostare? 
						
						Tra le posizioni disponibili delle celle/carte che sono visibili.
						
						QUI POSSO PROVARE A LAVORARE CON I WEAK CONSTRAINT ...
						
				 3. Quando dovrò distribuire le carte restanti? 
					
				 		Quando avrò maxScala(1) 	*/
				
			
			Scelta answerSets = ai.getAnswerSets();// Da qui devi uscire con la cella che 
			//deve essere spostata
			
			if (answerSets.getI() != null && answerSets.getJ() != null)
			{
				
//				ArrayList<Posizione> destinazioni = ai.getPosizioni();
				//L'oggetto posizione deve indicare le possibili coppie (i,j)
				//che dove posso incollare la pila di carte che parte dalla posizione (answerset)
				
				for (Posizione posizione : destinazioni) {
					boolean spostaBlocco = tavolo.spostaBlocco(answerSets.getI() , answerSets.getJ() , posizione.getI(), posizione.getJ());
					if(spostaBlocco) {
						break;
					}
				}
				
			}
			
			
//			if(ai.distribuisciCarteMazzo() && tavolo.numCarteMazzo() > 0)
//				tavolo.daiCarte();
			out.close();
			destinazioni.clear();
		}
	}


}
