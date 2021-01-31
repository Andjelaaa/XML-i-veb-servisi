package com.xml.poverenik.dto;

import com.xml.poverenik.model.Podnosilac;

public class PodnosilacDTO {
	private String ime;
	private String prezime;
	private String nazivFirme;
	private String korisnickoIme;
	
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
	

	public PodnosilacDTO(String ime, String prezime, String nazivFirme, String korisnickoIme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
		this.korisnickoIme = korisnickoIme;
	}

	public PodnosilacDTO(Podnosilac podnosilac) {
		// TODO Auto-generated constructor stub
		this.ime = podnosilac.getIme();
		this.prezime =  podnosilac.getPrezime();
		this.nazivFirme = podnosilac.getNazivFirme();
	}

	@Override
	public String toString() {
		return "Podnosilac [ime=" + ime + ", prezime=" + prezime + ", nazivFirme=" + nazivFirme + "]";
	}
	
	

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
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
