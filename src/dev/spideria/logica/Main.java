package dev.spideria.logica;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import dev.spideria.ia.Cella;
import dev.spideria.ia.GameAI;
import dev.spideria.utility.StampeUtils;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, InterruptedException {
		Tavolo tavolo = new Tavolo();
		GameAI ai = new GameAI();

		ArrayList<Posizione> destinazioni = new ArrayList<Posizione>();

		PrintWriter printFatti = new PrintWriter("input/facts.txt");
		PrintWriter printScelte = new PrintWriter("input/scelte.txt");
		PrintWriter printDestinazioni = new PrintWriter("input/destinazioni.txt");

		boolean spostaBlocco = false;

		while (tavolo.fineGioco()) // tavolo.fineGioco() -> Metodo di supporto che controlla lo stato del gioco
		{
			for (int i = 0; i < tavolo.getPila().size(); i++) {
				for (int j = 0; j < tavolo.getPila().get(i).size(); j++) {
					Carta c = tavolo.getPila().get(i).get(j);

					if (c.isVisibile()) {
						if (j == tavolo.getPila().get(i).size() - 1) {
							Posizione p = new Posizione(i, j);
							destinazioni.add(p);
							printDestinazioni.println(p);
						}
						int num = tavolo.elementiScala(i, j, c.getValore());
						Cella cella = new Cella(i, j, c.getSeme(), c.getValore(), num);
						ai.addFacts(cella);
						printFatti.println(cella); // Aggiungo la cella creata al file facts.txt
						System.out.println(cella);
					}

				}

			}

			/*
			 * System.out.println("inserisci riga e colonna da spostare"); Scanner scanner =
			 * new Scanner(System.in); int rigaFrom = scanner.nextInt(); int colonnaFrom=
			 * scanner.nextInt();;
			 * System.out.println("inserisci riga e colonna dove spostare"); int rigaTo =
			 * scanner.nextInt(); int colonnaTo= scanner.nextInt();
			 * tavolo.spostaBlocco(rigaFrom, colonnaFrom, rigaTo, colonnaTo);
			 */

			// util.initStampaPilaLogica();
			// util.stampaTavolo();

			// util.stampaPilaArrayList();
			// util.stampaTavoloInColonna();

			ai.loadFacts();

			ArrayList<Scelta> answerSets = ai.getAnswerSets(); // Da qui devi uscire con la cella che deve essere
																// spostata

			Thread.sleep(1000);

			boolean soloSpostamento = ai.soloSpostamento();

			Thread.sleep(1000);

			for (Scelta scelta : answerSets) {

				printScelte.println(scelta);

				if ((scelta.getI() != null && scelta.getJ() != null) && (soloSpostamento)) {

					// ArrayList<Posizione> destinazioni = ai.getPosizioni();
					// L'oggetto posizione deve indicare le possibili coppie (i,j)
					// che dove posso incollare la pila di carte che parte dalla posizione
					// (answerset)

					for (Posizione posizione : destinazioni) {
						spostaBlocco = tavolo.spostaBlocco(scelta.getI(), scelta.getJ(), posizione.getI(),
								posizione.getJ());
						if (spostaBlocco) {
							try {
								//tavolo.displayPilaGioco();
								StampeUtils.stampaVisualeGiocatore(tavolo.getPila());
								Thread.sleep(1000);
								//tavolo.stampaPilaArrayList();
								break;
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						}
					}

				}
			}

			if (!spostaBlocco && tavolo.numCarteMazzo() > 0)
				tavolo.daiCarte();

			destinazioni.clear();

			printFatti.close();
			printScelte.close();
			printDestinazioni.close();

		}
	}

}
