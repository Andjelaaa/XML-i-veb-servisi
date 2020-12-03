package com.xml.projekat.dto;

public class PodnosilaccDTO {
	
	private String ime;
	private String prezime;
	private String nazivFirme;
	
	public PodnosilaccDTO() {
		super();
	}
	
	public PodnosilaccDTO(String ime, String prezime, String nazivFirme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
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
