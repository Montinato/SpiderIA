package dev.spideria.ia;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cella")
public class Cella 
{
    @Param(0)
    int i;
    @Param(1)
    int j;
    @Param(2)
    int valore;
    @Param(3)
    int numElementiScala;
    

    public Cella(int i, int j, int num ,int valore) {
        this.i = i;
        this.j = j;
        this.valore = valore;
        this.numElementiScala = num;
    }

    public int getI() {
        return this.i;
    }

    public void setI(int i) {
        this.i = i;
    }

    public int getJ() {
        return this.j;
    }

    public void setJ(int j) {
        this.j = j;
    }
    
    public int getNumElementiScala() {
        return this.numElementiScala;
    }
    
    public void setNumElementiScala(int num) {
        this.numElementiScala = num;
    }

    public int getValore() {
        return this.valore;
    }
    
    public void setValore(int v) {
        this.valore = v;
    }

	@Override
	public String toString() {
		return "Cella [i=" + i + ", j=" + j + ", valore=" + valore + ", numElementiScala=" + numElementiScala + "]";
	}
    

}
