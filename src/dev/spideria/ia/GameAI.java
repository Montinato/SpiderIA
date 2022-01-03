package dev.spideria.ia;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;

public class GameAI 
{
	private static String inputResource = "input/file.txt";
    private static Handler handler;
    InputProgram program;
    InputProgram facts;
   
    public GameAI()
    {
    	    handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
            facts = new ASPInputProgram();
            program = new ASPInputProgram();
            program.addFilesPath(inputResource);
     }
    
    
    public void addFacts(Cella c) {
        try {
            this.facts.addObjectInput(c);
        }
        catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    
    public void clear() {
        this.facts.clearAll();
        handler.removeAll();
    }

    public void loadFacts() {
        handler.addProgram(this.facts);
        handler.addProgram(this.program);
    }
    
    public static Handler getHandler() {
        return GameAI.handler;
    }

    public static void setHandler(Handler handler) {
        GameAI.handler = handler;
    }

    public InputProgram getFacts() {
        return this.facts;
    }

    public void setFacts(InputProgram facts) 
    {
        this.facts = facts;
    }
    
    
    // DEVE ESESERE return ArrayList<Cella>
    public ArrayList<Cella> getAnswerSets() throws FileNotFoundException
    {
    	// L'arrayList in cui ci saranno le celle da confrontare ogni volta 
    	 ArrayList<Cella> max = new  ArrayList<Cella>();
    	 
    	 // Faccio partire il programma logico 
         Output o = GameAI.getHandler().startSync();
         
         PrintWriter out = new PrintWriter("input/answerSet.txt");
         
         
         AnswerSets answersets = (AnswerSets) o;
         
         
         if (answersets.getAnswersets().size() == 0) {
             System.out.println("No answer set");
         }
         else 
         {
             String answerSetsString = answersets.getAnswerSetsString();
             
             System.out.println("\n" + "Stampo l'answerset  \n" + answerSetsString);
             
             Pattern matcher = Pattern.compile("/scelgo\\((\\d{1}),(\\d{1}),(\\d{1}),(\\d{1}\\))");
              //"(cella\\((\\d{1}),(\\d{1}),(\\d{1}),(\\d{1,2})\\))"
             
            Matcher m = matcher.matcher(answerSetsString);
           

             while (m.find()) 
             {
            	 Cella cella = (new Cella(Integer.parseInt(m.group(1)), Integer.parseInt(m.group(2)), Integer.parseInt(m.group(3)), Integer.parseInt(m.group(4)), Integer.parseInt(m.group(5)))); 
                 max.add(cella);
                 out.println(cella);
             }	
         }
         out.close();
       
         return max;	
    }
    
    
    

}
