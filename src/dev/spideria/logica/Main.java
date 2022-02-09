package dev.spideria.logica;

import java.io.FileNotFoundException;
import java.util.Scanner;

import dev.spideria.ia.Cella;

public class Main 
{
	
	public static void main(String[] args) throws FileNotFoundException 
	{
		Tavolo tavolo = new Tavolo();
		//GameAI ai = new GameAI();
		
		// Riporto su questo file i fatti che genero 
		//PrintWriter out = new PrintWriter("input/facts.txt");
		
		
		while(tavolo.fineGioco()) 	//tavolo.fineGioco() -> Metodo di supporto che controlla lo stato del gioco
		{
			for(int i=0;i<tavolo.getPila().size();i++)
			{
				for(int j=0;j<tavolo.getPila().get(i).size();j++)
				{
					Carta c = tavolo.getPila().get(i).get(j);
					int num = tavolo.elementiScala(i, j, c.getValore());
					Cella cella = new Cella(i,j,c.getSeme(),c.getValore(),num);
					//ai.addFacts(cella);
					
					// Aggiungo la cella creata al file facts.txt
					//out.println(cella);
				}
			}
			
			System.out.println("inserisci riga e colonna da spostare");
			Scanner scanner = new Scanner(System.in);
			int rigaFrom = scanner.nextInt();
			int colonnaFrom= scanner.nextInt();;
			System.out.println("inserisci riga e colonna dove spostare");
			int rigaTo = scanner.nextInt(); 
			int colonnaTo= scanner.nextInt();      
			tavolo.spostaBlocco(rigaFrom, colonnaFrom, rigaTo, colonnaTo);
//			tavolo.initStampaPilaLogica();//
			 tavolo.stampaTavolo();
//			tavolo.displayPilaGioco();
//
//			tavolo.stampaPilaArrayList();
			//ai.loadFacts();
			
			// Un metodo che mi restituisce gli AnswerSet -> getAnswerSets()
			//List<Cella> answerSets = ai.getAnswerSets();
			
		
			
			// Chiudo l'oggetto PrintWriter
			//out.close();
			
			// Operazioni sulle celle
//			if(answerSets.size() > 0)
//			{
//				// Quando trovo un answer set cosa devo fare ? ? ?
//				
//				
//				// TROVERO' COME ANSWER SET LA MAXSCALA CORRENTE TRA 
//				// GLI ELEMENTI DELLA PILA ( ATTRAVERSO LA POSIZIONE 
//				// DEGLI INDICI )
//				
//				// Dovro' rimuoverlo dalla pila e continuare ad iterare fino a quando
//				// non ci sono o elementi nella pila o non ci sono piu' combinazioni
//				// di scala possibili. 
//				
//				// HO FATTO UN PO' DI DEBUG SUL CODICE DI MELO, GUARDARE LI 
//				//( Non so perchè non mi stampa più sul file answerSet.txt ( sempre dal codice di Melo ) . )
//				
//				
//			}

		}
	
	}


}
