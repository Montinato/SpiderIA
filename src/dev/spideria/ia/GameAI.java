package dev.spideria.ia;

import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;


public class GameAI 
{
	private static String inputResource = "input/file";
    private static Handler handler;
    InputProgram encoding;
    InputProgram facts;
   
    public GameAI()
    {
    	   handler = new DesktopHandler(new DLV2DesktopService("./dlv2.exe"));
            facts = new ASPInputProgram();
            encoding = new ASPInputProgram();
            encoding.addFilesPath(inputResource);
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

    public void setFacts(InputProgram facts) {
        this.facts = facts;
    }

}
