package com.xml.poverenik.dto;

import com.xml.poverenik.model.PZalbaOdluke;

public class PZalbaOdlukeDTO {
	private String tekst;
	private String datum;
	private String razlog;
	private String brojZalbe;
	private String godinaOdbijanja;
	
	public PZalbaOdlukeDTO() {}
	
	public PZalbaOdlukeDTO(String tekst, String datum, String razlog, String brojZalbe, String godinaOdbijanja) {
		super();
		this.tekst = tekst;
		this.datum = datum;
		this.razlog = razlog;
		this.brojZalbe = brojZalbe;
		this.godinaOdbijanja = godinaOdbijanja;
	}
	public PZalbaOdlukeDTO(PZalbaOdluke pzo) {
		this.tekst = pzo.getTekst();
		this.datum = pzo.getDatum();
		this.razlog = pzo.getRazlog();
		this.brojZalbe = pzo.getBrojZalbe();
		this.godinaOdbijanja = pzo.getGodinaOdbijanja();
	}
	public String getTekst() {
		return tekst;
	}
	public void setTekst(String tekst) {
		this.tekst = tekst;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getRazlog() {
		return razlog;
	}
	public void setRazlog(String razlog) {
		this.razlog = razlog;
	}
	public String getBrojZalbe() {
		return brojZalbe;
	}
	public void setBrojZalbe(String brojZalbe) {
		this.brojZalbe = brojZalbe;
	}
	public String getGodinaOdbijanja() {
		return godinaOdbijanja;
	}
	public void setGodinaOdbijanja(String godinaOdbijanja) {
		this.godinaOdbijanja = godinaOdbijanja;
	}
	@Override
	public String toString() {
		return "PZalbaOdluke [tekst=" + tekst + ", datum=" + datum + ", razlog=" + razlog + ", brojZalbe=" + brojZalbe
				+ ", godinaOdbijanja=" + godinaOdbijanja + "]";
	}
	
	
}
