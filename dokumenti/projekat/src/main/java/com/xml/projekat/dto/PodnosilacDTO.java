package com.xml.projekat.dto;

public class PodnosilacDTO {
	private String ime;
	private String prezime;
	private String nazivFirme;
	
	public PodnosilacDTO() {
		super();
	}

	public PodnosilacDTO(String ime, String prezime, String nazivFirme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
	}
	public PodnosilacDTO(String nazivFirme) {
		super();
		this.nazivFirme = nazivFirme;
	}

	@Override
	public String toString() {
		return "Podnosilac [ime=" + ime + ", prezime=" + prezime + ", nazivFirme=" + nazivFirme + "]";
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public String getNazivFirme() {
		return nazivFirme;
	}

	public void setNazivFirme(String nazivFirme) {
		this.nazivFirme = nazivFirme;
	}
	

}
