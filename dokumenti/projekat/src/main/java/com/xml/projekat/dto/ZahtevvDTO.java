package com.xml.projekat.dto;

import java.util.ArrayList;


public class ZahtevvDTO {
	
	private PodnosilaccDTO podnosilac;
	private AdresaaDTO adresa;
	private String drugiPodaciZaKontakt;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private String naslov;
	private ArrayList<PZahtevDTO> paragrafi;
	private String trazeneInformacije;
	
	private String datum;
	private String mesto;
	private ArrayList<String> fusnote;
	
	public ZahtevvDTO() {
		super();
	}

	public ZahtevvDTO(PodnosilaccDTO podnosilac, AdresaaDTO adresa, String drugiPodaciZaKontakt,
			String nazivOrganaVlasti, String sedisteOrgana, String naslov, ArrayList<PZahtevDTO> paragrafi,
			String trazeneInformacije, String datum, String mesto, ArrayList<String> fusnote) {
		super();
		this.podnosilac = podnosilac;
		this.adresa = adresa;
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
		this.nazivOrganaVlasti = nazivOrganaVlasti;
		this.sedisteOrgana = sedisteOrgana;
		this.naslov = naslov;
		this.paragrafi = paragrafi;
		this.trazeneInformacije = trazeneInformacije;
		this.datum = datum;
		this.mesto = mesto;
		this.fusnote = fusnote;
	}

	public PodnosilaccDTO getPodnosilac() {
		return podnosilac;
	}

	public void setPodnosilac(PodnosilaccDTO podnosilac) {
		this.podnosilac = podnosilac;
	}

	public AdresaaDTO getAdresa() {
		return adresa;
	}

	public void setAdresa(AdresaaDTO adresa) {
		this.adresa = adresa;
	}

	public String getDrugiPodaciZaKontakt() {
		return drugiPodaciZaKontakt;
	}

	public void setDrugiPodaciZaKontakt(String drugiPodaciZaKontakt) {
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
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

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public ArrayList<PZahtevDTO> getParagrafi() {
		return paragrafi;
	}

	public void setParagrafi(ArrayList<PZahtevDTO> paragrafi) {
		this.paragrafi = paragrafi;
	}

	public String getTrazeneInformacije() {
		return trazeneInformacije;
	}

	public void setTrazeneInformacije(String trazeneInformacije) {
		this.trazeneInformacije = trazeneInformacije;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	public String getMesto() {
		return mesto;
	}

	public void setMesto(String mesto) {
		this.mesto = mesto;
	}

	public ArrayList<String> getFusnote() {
		return fusnote;
	}

	public void setFusnote(ArrayList<String> fusnote) {
		this.fusnote = fusnote;
	}
	
	
	
}
