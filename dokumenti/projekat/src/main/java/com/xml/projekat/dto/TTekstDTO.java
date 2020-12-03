package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.TTekst;

public class TTekstDTO {

	private String tekst;
	private ArrayList<String> paragrafi;
	
	public TTekstDTO() {
		super();
	}

	public TTekstDTO(String tekst, ArrayList<String> paragrafi) {
		super();
		this.tekst = tekst;
		this.paragrafi = paragrafi;
	}

	public TTekstDTO(TTekst tekstResenja) {
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
