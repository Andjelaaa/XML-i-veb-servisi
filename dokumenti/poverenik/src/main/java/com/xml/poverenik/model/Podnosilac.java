package com.xml.poverenik.model;

import com.xml.poverenik.dto.PodnosilacDTO;

public class Podnosilac {
	private String ime;
	private String prezime;
	private String nazivFirme;
	private String korisnickoIme;
	
	public Podnosilac() {
		super();
	}

	public Podnosilac(String ime, String prezime, String nazivFirme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
	}

	public Podnosilac(PodnosilacDTO podnosilac) {
		this.ime = podnosilac.getIme();
		this.prezime = podnosilac.getPrezime();
		this.nazivFirme = podnosilac.getNazivFirme();
		this.korisnickoIme = podnosilac.getKorisnickoIme();
	}

	
	public Podnosilac(String ime, String prezime, String nazivFirme, String korisnickoIme) {
		super();
		this.ime = ime;
		this.prezime = prezime;
		this.nazivFirme = nazivFirme;
		this.korisnickoIme = korisnickoIme;
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
