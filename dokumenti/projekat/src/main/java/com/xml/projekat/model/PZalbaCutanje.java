package com.xml.projekat.model;

public class PZalbaCutanje {
	
	private String text;
	private String nazivOrgana;
	private String datum;
	private String podaciOZahtevuIInformacijama;
	
	public PZalbaCutanje() {
		super();
	}

	public PZalbaCutanje(String text, String nazivOrgana,String datum, String podaciOZahtevuIInformacijama) {
		super();
		this.text = text;
		this.nazivOrgana = nazivOrgana;
		this.datum = datum;
		this.podaciOZahtevuIInformacijama = podaciOZahtevuIInformacijama;
	}
	
	public PZalbaCutanje(String text,String datum, String podaciOZahtevuIInformacijama) {
		super();
		this.text = text;
		this.datum = datum;
		this.podaciOZahtevuIInformacijama = podaciOZahtevuIInformacijama;
	}

	public PZalbaCutanje(String text) {
		super();
		this.text = text;
	}
	
	public PZalbaCutanje(String text, String nazivOrgana) {
		super();
		this.text = text;
		this.nazivOrgana = nazivOrgana;
	}
	
	
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getNazivOrgana() {
		return nazivOrgana;
	}

	public void setNazivOrgana(String nazivOrgana) {
		this.nazivOrgana = nazivOrgana;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getPodaciOZahtevuIInformacijama() {
		return podaciOZahtevuIInformacijama;
	}

	public void setPodaciOZahtevuIInformacijama(String podaciOZahtevuIInformacijama) {
		this.podaciOZahtevuIInformacijama = podaciOZahtevuIInformacijama;
	}

	@Override
	public String toString() {
		return "PZalbaCutanje [text=" + text + ", nazivOrgana="+ nazivOrgana + ", datum=" + datum + ", podaciOZahtevuIInformacijama=" + podaciOZahtevuIInformacijama + "]";
	}
	
}
