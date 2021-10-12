package dev.spideria.logica;


public class Carta 
{
	public int seme;
	public int valore;
	private boolean visibile = false;
	
	@Override
	public String toString() {
		return "Carta [seme=" + seme + ", valore=" + valore + ", visibile=" + visibile + "]";
	}

	public Carta()
	{
		this.seme = 0;
		this.seme = 0;
		this.visibile = false;
	}
	public Carta(boolean visibile)
	{
		this.seme = 0;
		this.seme = 0;
		this.visibile = visibile;
	}
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
		return this.visibile;
	}

	public void setVisibile(boolean coperta) {
		this.visibile = coperta;
	}
	
}
