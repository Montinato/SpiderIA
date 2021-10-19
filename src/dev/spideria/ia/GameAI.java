package dev.spideria.ia;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;

public class GameAI 
{
	private static String inputResource = "input/file";
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
    
    private static Handler getHandler() {
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

}
