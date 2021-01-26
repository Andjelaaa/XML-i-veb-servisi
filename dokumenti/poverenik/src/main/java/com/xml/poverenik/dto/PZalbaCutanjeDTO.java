package com.xml.poverenik.dto;

import com.xml.poverenik.model.PZalbaCutanje;

public class PZalbaCutanjeDTO {

	private String text;
	private String nazivOrgana;
	private String datum;
	private String podaciOZahtevuIInformacijama;
	
	public PZalbaCutanjeDTO() {
		super();
	}

	public PZalbaCutanjeDTO(String text, String nazivOrgana,String datum, String podaciOZahtevuIInformacijama) {
		super();
		this.text = text;
		this.nazivOrgana = nazivOrgana;
		this.datum = datum;
		this.podaciOZahtevuIInformacijama = podaciOZahtevuIInformacijama;
	}
	
	public PZalbaCutanjeDTO(String text,String datum, String podaciOZahtevuIInformacijama) {
		super();
		this.text = text;
		this.datum = datum;
		this.podaciOZahtevuIInformacijama = podaciOZahtevuIInformacijama;
	}

	public PZalbaCutanjeDTO(String text) {
		super();
		this.text = text;
	}
	
	public PZalbaCutanjeDTO(String text, String nazivOrgana) {
		super();
		this.text = text;
		this.nazivOrgana = nazivOrgana;
	}
	
	
	
	public PZalbaCutanjeDTO(PZalbaCutanje p) {
		this.text = p.getText();
		this.nazivOrgana = p.getNazivOrgana();
		this.datum = p.getDatum();
		this.podaciOZahtevuIInformacijama = p.getPodaciOZahtevuIInformacijama();
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
