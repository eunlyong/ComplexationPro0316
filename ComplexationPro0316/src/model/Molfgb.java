package model;

public class Molfgb {
	
	public String[] fg = new String[8];
	public int n_1bits;
	public Molfgb() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Molfgb(String[] fg, int n_1bits) {
		super();
		this.fg = fg;
		this.n_1bits = n_1bits;
	}
	public String[] getFg() {
		return fg;
	}
	public void setFg(String[] fg) {
		this.fg = fg;
	}
	public int getN_1bits() {
		return n_1bits;
	}
	public void setN_1bits(int n_1bits) {
		this.n_1bits = n_1bits;
	}
	
	
}
