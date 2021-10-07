package dev.spideria.logica;


public class Carta 
{
	public int seme;
	public int valore;
	private boolean visibile;
	
	
	public Carta(int seme,int valore)
	{
		this.seme = seme;
		this.valore = valore;
		this.visibile = false;
	}

	public int getSeme() {
		return seme;
	}

	public void setSeme(int seme) {
		this.seme = seme;
	}

	public int getValore() {
		return valore;
	}

	public void setValore(int valore) {
		this.valore = valore;
	}

	public boolean isVisibile() {
		return visibile;
	}

	public void setVisibile(boolean coperta) {
		this.visibile = coperta;
	}
	
}
