package dev.spideria.ia;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import dev.spideria.logica.Posizione;
import dev.spideria.logica.Scelta;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;

public class GameAI {
	private static String inputResource = "input/file.txt";
	private static Handler handler;
	InputProgram program;
	InputProgram facts;

	public GameAI() {
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));
		facts = new ASPInputProgram();
		program = new ASPInputProgram();
		program.addFilesPath(inputResource);
	}

	public void addFacts(Cella c) {
		try {
			this.facts.addObjectInput(c);
		} catch (Exception e) {
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

	public void setFacts(InputProgram facts) {
		this.facts = facts;
	}

	public ArrayList<Scelta> getAnswerSets() {
		Scelta scelta = null;

		ArrayList<Scelta> scelte = new ArrayList<Scelta>();

		Output o = GameAI.getHandler().startSync();
		AnswerSets answersets = (AnswerSets) o;
		while (answersets.getAnswersets().get(0).getAnswerSet().isEmpty()) {
			o = GameAI.getHandler().startSync();
			answersets = (AnswerSets) o;
		}
		if (answersets.getAnswersets().size() == 0) {
			System.out.println("No answer set");
		} else {

			// String answerSetsString = answersets.getAnswerSetsString();
			Pattern pattern = Pattern.compile("scelgo\\((\\d+),(\\d+),(\\d+)\\)");

			for (AnswerSet an : answersets.getAnswersets()) {
				Matcher matcher;
				for (String str : an.getAnswerSet()) {
					matcher = pattern.matcher(str);
					if (matcher.find()) {
						Integer i = Integer.valueOf(matcher.group(1));
						Integer j = Integer.valueOf(matcher.group(2));
						scelta = new Scelta(i, j);
						// System.out.println("scelgo("+i+","+j+") ");
						scelte.add(scelta);
					}
				}
			}
		}
		return scelte;
	}

	public boolean soloSpostamento() {
		Output o = GameAI.getHandler().startSync();
		AnswerSets answersets = (AnswerSets) o;
		while (answersets.getAnswersets().get(0).getAnswerSet().isEmpty()) {
			o = GameAI.getHandler().startSync();
			answersets = (AnswerSets) o;
		}
		if (answersets.getAnswersets().size() == 0) {
			System.out.println("No answer set");
		} else {
			// String answerSetsString = answersets.getAnswerSetsString();
			Pattern pattern = Pattern.compile("soloSpostamento\\(1\\)");

			for (AnswerSet an : answersets.getAnswersets()) {
				Matcher matcher;
				for (String str : an.getAnswerSet()) {
					matcher = pattern.matcher(str);
					if (matcher.find()) {
						return true;
					}
				}
			}
		}
		return false;
	}

}
