package com.xml.projekat.model;

import java.util.ArrayList;



public class TResenja {
	private String tekst;
	private ArrayList<String> paragrafi;
	
	public TResenja() {
		super();
	}

	public TResenja(String tekst, ArrayList<String> paragrafi) {
		super();
		this.tekst = tekst;
		this.paragrafi = paragrafi;
	}

	
	public ArrayList<String> getParagrafi() {
		return paragrafi;
	}

	public void setParagrafi(ArrayList<String> paragrafi) {
		this.paragrafi = paragrafi;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	@Override
	public String toString() {
		return "TTekst [tekst=" + tekst + ", paragrafi=" + paragrafi + "]";
	}
	

}
