package com.xml.projekat.model;

public class Podnosilac {
	private String ime;
	private String prezime;
	private String nazivFirme;
	
	public Podnosilac() {
		super();
	}

	public Podnosilac(String ime, String prezime, String nazivFirme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
	}

	@Override
	public String toString() {
		return "Podnosilac [ime=" + ime + ", prezime=" + prezime + ", nazivFirme=" + nazivFirme + "]";
	}
	
	
}
