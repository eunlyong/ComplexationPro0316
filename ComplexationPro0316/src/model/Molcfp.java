package model;

public class Molcfp {
	
	public String dfp01;
	String[] hfp16 = new String[16];
//	public String hfp02;
//	public String hfp03;
//	public String hfp04;
//	public String hfp05;
//	public String hfp06;
//	public String hfp07;
//	public String hfp08;
//	public String hfp09;
//	public String hfp10;
//	public String hfp11;
//	public String hfp12;
//	public String hfp13;
//	public String hfp14;
//	public String hfp15;
//	public String hfp16;
	public String n_h1bits;
	public Molcfp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Molcfp(String dfp01, String[] hfp16, String n_h1bits) {
		super();
		this.dfp01 = dfp01;
		this.hfp16 = hfp16;
		this.n_h1bits = n_h1bits;
	}
	public String getDfp01() {
		return dfp01;
	}
	public void setDfp01(String dfp01) {
		this.dfp01 = dfp01;
	}
	public String[] getHfp16() {
		return hfp16;
	}
	public void setHfp16(String[] hfp16) {
		this.hfp16 = hfp16;
	}
	public String getN_h1bits() {
		return n_h1bits;
	}
	public void setN_h1bits(String n_h1bits) {
		this.n_h1bits = n_h1bits;
	}
	
	
	
	
}
