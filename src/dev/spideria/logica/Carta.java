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
 public String stampaCarta() {
	 String stringa;
	 
	 if (this.valore == 11)
		 stringa= "| J " ;
		else if (this.valore == 12)
			 stringa= "| Q " ;
		else if (this.valore == 13)
			 stringa= "| K " ;
		else
			 stringa= this.valore+ "" ;
	 
	       if (this.seme == 1 )
	    	   stringa =  stringa+ "[Fiori] | ";
		else if (this.seme == 2 )
			stringa = stringa+"[Quadri] |" ;
		else if (this.seme == 3 )
			stringa =stringa+ "[Cuori] |" ;
		else if (this.seme == 4  )
			stringa = stringa+"[Picche] |"  ;
	  
	 
	 return stringa;
 }
	public Carta()
	{
		this.seme = 0;
		this.valore = 0;
		this.visibile = false;
	}
	
	public Carta(Carta c)
	{
		this.seme = c.seme;
		this.valore = c.valore;
		this.visibile = c.visibile;
	}
	
	public Carta(boolean visibile)
	{
		this.seme = 0;
		this.valore = 0;
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
