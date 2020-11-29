package com.xml.projekat.model;

public class PZalbaOdluke {
	private String tekst;
	private String datum;
	private String razlog;
	private String broj_zalbe;
	private String godina_odbijanja;
	public PZalbaOdluke(String tekst, String datum, String razlog, String broj_zalbe, String godina_odbijanja) {
		super();
		this.tekst = tekst;
		this.datum = datum;
		this.razlog = razlog;
		this.broj_zalbe = broj_zalbe;
		this.godina_odbijanja = godina_odbijanja;
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
	public String getBroj_zalbe() {
		return broj_zalbe;
	}
	public void setBroj_zalbe(String broj_zalbe) {
		this.broj_zalbe = broj_zalbe;
	}
	public String getGodina_odbijanja() {
		return godina_odbijanja;
	}
	public void setGodina_odbijanja(String godina_odbijanja) {
		this.godina_odbijanja = godina_odbijanja;
	}
	@Override
	public String toString() {
		return "PZalbaOdluke [tekst=" + tekst + ", datum=" + datum + ", razlog=" + razlog + ", broj_zalbe=" + broj_zalbe
				+ ", godina_odbijanja=" + godina_odbijanja + "]";
	}
	
	
}
