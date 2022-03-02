package dev.spideria.logica;

public class Posizione {
	
	 private Integer i;
	 private Integer j;

	    public Posizione(Integer i, Integer j) {
	        this.i = i;
	        this.j = j;
	    }

	    @Override
	    public String toString() {
	        return "Posizione [i=" + i + ", j=" + j + "]";
	    }

	    public Integer getI() {
	        return this.i;
	    }

	    public void setI(Integer i) {
	        this.i = i;
	    }

	    public Integer getJ() {
	        return this.j;
	    }

	    public void setJ(Integer j) {
	        this.j = j;
	    }

}
