package com.xml.projekat.model;

import java.util.ArrayList;

import com.xml.projekat.dto.PZahtevDTO;
import com.xml.projekat.dto.ZahtevDTO;

public class Zahtev {
	
	private Podnosilac podnosilac;
	private Adresa adresa;
	private String drugiPodaciZaKontakt;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private String naslov;
	private ArrayList<PZahtev> paragrafi;
	private String trazeneInformacije;
	
	private String datum;
	private String mesto;
	private ArrayList<String> fusnote;
	public Zahtev(Podnosilac podnosilac, Adresa adresa, String drugiPodaciZaKontakt, String nazivOrganaVlasti,
			String sedisteOrgana, String naslov, ArrayList<PZahtev> paragrafi, String trazeneInformacije, String datum,
			String mesto, ArrayList<String> fusnote) {
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
	
	public Zahtev(ZahtevDTO dto) {
		this.podnosilac = new Podnosilac(dto.getPodnosilac());
		this.adresa = new Adresa(dto.getAdresa());
		this.drugiPodaciZaKontakt = dto.getDrugiPodaciZaKontakt();
		this.nazivOrganaVlasti = dto.getNazivOrganaVlasti();
		this.sedisteOrgana = dto.getSedisteOrgana();
		this.naslov = dto.getNaslov();
		this.paragrafi = new ArrayList<PZahtev>();
		for (PZahtevDTO pzdto : dto.getParagrafi()) {
			this.paragrafi.add(new PZahtev(pzdto));
		}
		this.trazeneInformacije = dto.getTrazeneInformacije();
		this.datum = dto.getDatum();
		this.mesto = dto.getMesto();
		this.fusnote = dto.getFusnote();
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

	public ArrayList<PZahtev> getParagrafi() {
		return paragrafi;
	}

	public void setParagrafi(ArrayList<PZahtev> paragrafi) {
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

	@Override
	public String toString() {
		return "Zahtev [podnosilac=" + podnosilac + ", adresa=" + adresa + ", drugiPodaciZaKontakt="
				+ drugiPodaciZaKontakt + ", nazivOrganaVlasti=" + nazivOrganaVlasti + ", sedisteOrgana=" + sedisteOrgana
				+ ", naslov=" + naslov + ", paragrafi=" + paragrafi + ", trazeneInformacije=" + trazeneInformacije
				+ ", datum=" + datum + ", mesto=" + mesto + ", fusnote=" + fusnote + "]";
	}
	

}
