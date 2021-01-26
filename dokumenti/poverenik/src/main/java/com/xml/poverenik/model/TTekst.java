package com.xml.poverenik.model;

import java.util.ArrayList;

import com.xml.poverenik.dto.TTekstDTO;

public class TTekst {
	
	private String tekst;
	private ArrayList<String> paragrafi;
	
	public TTekst() {
		super();
	}

	public TTekst(String tekst, ArrayList<String> paragrafi) {
		super();
		this.tekst = tekst;
		this.paragrafi = paragrafi;
	}

	public TTekst(TTekstDTO tekstResenja) {
		this.tekst = tekstResenja.getTekst();
		this.paragrafi = tekstResenja.getParagrafi();
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
