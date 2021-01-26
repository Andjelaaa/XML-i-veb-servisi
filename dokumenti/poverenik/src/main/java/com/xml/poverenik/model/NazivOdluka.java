package com.xml.poverenik.model;

public class NazivOdluka {
	
	private String nazivResenja;
	private String odluka;
	
	public NazivOdluka() {
		super();
	}

	public NazivOdluka(String naziv, String odluka) {
		super();
		this.nazivResenja = naziv;
		this.odluka = odluka;
	}
	

	public String getNazivResenja() {
		return nazivResenja;
	}

	public void setNazivResenja(String nazivResenja) {
		this.nazivResenja = nazivResenja;
	}

	public String getOdluka() {
		return odluka;
	}

	public void setOdluka(String odluka) {
		this.odluka = odluka;
	}

	@Override
	public String toString() {
		return "NazivOdluka [nazivResenja=" + nazivResenja + ", odluka=" + odluka + "]";
	}
	
}
