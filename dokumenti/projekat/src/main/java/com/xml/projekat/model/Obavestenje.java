package com.xml.projekat.model;

import java.util.ArrayList;

import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.PObavestenjeDTO;

public class Obavestenje {
	
	private String URI;
	private String zahtevURI;
	private Podnosilac podnosilac;
	private Adresa adresa;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private ArrayList<String> dostavljeno;
	
	private String datum;
	private String naslov;
	private String brojPredmeta;
	
	private ArrayList<PObavestenje> paragrafi;
	
	private String mestoPecata;

	
	public Obavestenje(Podnosilac podnosilac, Adresa adresa, String nazivOrganaVlasti, String sedisteOrgana,
			ArrayList<String> dostavljeno, String datum, String naslov, String brojPredmeta,
			ArrayList<PObavestenje> paragrafi, String mestoPecata) {
		super();
		this.podnosilac = podnosilac;
		this.adresa = adresa;
		this.nazivOrganaVlasti = nazivOrganaVlasti;
		this.sedisteOrgana = sedisteOrgana;
		this.dostavljeno = dostavljeno;
		this.datum = datum;
		this.naslov = naslov;
		this.brojPredmeta = brojPredmeta;
		this.paragrafi = paragrafi;
		this.mestoPecata = mestoPecata;
	}
	

	public Obavestenje(ObavestenjeDTO dto) {
		super();
		this.zahtevURI = dto.getZahtevURI();
		this.podnosilac = new Podnosilac(dto.getPodnosilac());
		this.adresa = new Adresa(dto.getAdresa());
		this.nazivOrganaVlasti = dto.getNazivOrganaVlasti();
		this.sedisteOrgana = dto.getSedisteOrgana();;
		this.dostavljeno = dto.getDostavljeno();
		this.datum = dto.getDatum();
		this.brojPredmeta = dto.getBrojPredmeta();
		this.paragrafi = new ArrayList<PObavestenje>();
		
		for(PObavestenjeDTO po: dto.getParagrafi()) {
			PObavestenje p = new PObavestenje(po);
			this.paragrafi.add(p);
		}		
	}


	
	@Override
	public String toString() {
		return "Obavestenje [podnosilac=" + podnosilac + ", adresa=" + adresa + ", nazivOrganaVlasti="
				+ nazivOrganaVlasti + ", sedisteOrgana=" + sedisteOrgana + ", dostavljeno=" + dostavljeno + ", datum="
				+ datum + ", naslov=" + naslov + ", brojPredmeta=" + brojPredmeta + ", paragrafi=" + paragrafi
				+ ", mestoPecata=" + mestoPecata + "]";
	}

	
	
	public String getZahtevURI() {
		return zahtevURI;
	}


	public void setZahtevURI(String zahtevURI) {
		this.zahtevURI = zahtevURI;
	}


	public String getURI() {
		return URI;
	}


	public void setURI(String uRI) {
		URI = uRI;
	}


	public Podnosilac getPodnosilac() {
		return podnosilac;
	}


	public void setPodnosilac(Podnosilac podnosilac) {
		this.podnosilac = podnosilac;
	}


	public Adresa getAdresa() {
		return adresa;
	}


	public void setAdresa(Adresa adresa) {
		this.adresa = adresa;
	}


	public String getNazivOrganaVlasti() {
		return nazivOrganaVlasti;
	}


	public void setNazivOrganaVlasti(String nazivOrganaVlasti) {
		this.nazivOrganaVlasti = nazivOrganaVlasti;
	}


	public String getSedisteOrgana() {
		return sedisteOrgana;
	}


	public void setSedisteOrgana(String sedisteOrgana) {
		this.sedisteOrgana = sedisteOrgana;
	}


	public ArrayList<String> getDostavljeno() {
		return dostavljeno;
	}


	public void setDostavljeno(ArrayList<String> dostavljeno) {
		this.dostavljeno = dostavljeno;
	}


	public String getDatum() {
		return datum;
	}


	public void setDatum(String datum) {
		this.datum = datum;
	}


	public String getNaslov() {
		return naslov;
	}


	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}


	public String getBrojPredmeta() {
		return brojPredmeta;
	}


	public void setBrojPredmeta(String brojPredmeta) {
		this.brojPredmeta = brojPredmeta;
	}


	public ArrayList<PObavestenje> getParagrafi() {
		return paragrafi;
	}


	public void setParagrafi(ArrayList<PObavestenje> paragrafi) {
		this.paragrafi = paragrafi;
	}


	public String getMestoPecata() {
		return mestoPecata;
	}


	public void setMestoPecata(String mestoPecata) {
		this.mestoPecata = mestoPecata;
	}
	
	
	

}
