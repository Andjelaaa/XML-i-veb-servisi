package com.xml.projekat.dto;

import java.util.ArrayList;
import java.util.List;

public class ObavestenjeeDTO {
	@Override
	public String toString() {
		return "ObavestenjeeDTO [podnosilac=" + podnosilac + ", adresa=" + adresa + ", nazivOrganaVlasti="
				+ nazivOrganaVlasti + ", sedisteOrgana=" + sedisteOrgana + ", dostavljeno=" + dostavljeno + ", datum="
				+ datum + ", naslov=" + naslov + ", brojPredmeta=" + brojPredmeta + ", mestoPecata=" + mestoPecata
				+ ", listaPObavestanja =" + listaPObavestenja + ", getPodnosilac()=" + getPodnosilac() + ", getAdresa()="
				+ getAdresa() + ", getNazivOrganaVlasti()=" + getNazivOrganaVlasti() + ", getSedisteOrgana()="
				+ getSedisteOrgana() + ", getDostavljeno()=" + getDostavljeno() + ", getDatum()=" + getDatum()
				+ ", getNaslov()=" + getNaslov() + ", getBrojPredmeta()=" + getBrojPredmeta() + ", getMestoPecata()="
				+ getMestoPecata() + ", getPObavestenjeDTO()=" + getPObavestenjeDTO() + ", getClass()=" + getClass()
				+ ", hashCode()=" + hashCode() + ", toString()=" + super.toString() + "]";
	}

	private PodnosilacDTO podnosilac;
	private AdresaDTO adresa;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private String dostavljeno;
	
	private String datum;
	private String naslov;
	private String brojPredmeta;

	private String mestoPecata;
	private List<PObavestenjeDTO> listaPObavestenja;

	public ObavestenjeeDTO() {
		super();
	}

	public PodnosilacDTO getPodnosilac() {
		return podnosilac;
	}

	public void setPodnosilac(PodnosilacDTO podnosilac) {
		this.podnosilac = podnosilac;
	}

	public AdresaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaDTO adresa) {
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

	public String getDostavljeno() {
		return dostavljeno;
	}

	public void setDostavljeno(String dostavljeno) {
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

	public String getMestoPecata() {
		return mestoPecata;
	}

	public void setMestoPecata(String mestoPecata) {
		this.mestoPecata = mestoPecata;
	}

	public List<PObavestenjeDTO> getPObavestenjeDTO() {
		return this.listaPObavestenja;
	}

	public void setPObavestenjeDTO(List<PObavestenjeDTO> pObavestenjeDTO) {
		this.listaPObavestenja = pObavestenjeDTO;
	}

}
