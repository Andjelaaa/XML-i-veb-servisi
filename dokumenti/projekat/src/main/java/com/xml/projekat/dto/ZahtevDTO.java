package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.PZahtev;
import com.xml.projekat.model.Zahtev;


public class ZahtevDTO {
	private String text;
	private String URI;
	private PodnosilacDTO podnosilac;
	private AdresaDTO adresa;
	private String drugiPodaciZaKontakt;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private String naslov;
	private ArrayList<PZahtevDTO> paragrafi;
	private String trazeneInformacije;
	
	private String datum;
	private String mesto;
	private ArrayList<String> fusnote;
	
	public ZahtevDTO() {
		super();
	}

	public ZahtevDTO(String text, PodnosilacDTO podnosilac, AdresaDTO adresa, String drugiPodaciZaKontakt,
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
		this.text = text;
	}

	public ZahtevDTO(String response) {
		this.text = response;
	}

	public ZahtevDTO(Zahtev z) {
		this.URI = z.getURI();
		this.podnosilac = new PodnosilacDTO(z.getPodnosilac());
		this.adresa = new AdresaDTO(z.getAdresa());
		this.drugiPodaciZaKontakt = z.getDrugiPodaciZaKontakt();
		this.nazivOrganaVlasti = z.getNazivOrganaVlasti();
		this.sedisteOrgana = z.getSedisteOrgana();
		this.naslov = z.getNaslov();
		this.paragrafi = new ArrayList<PZahtevDTO>();
		for (PZahtev pz : z.getParagrafi()) {
			this.paragrafi.add(new PZahtevDTO(pz));
		}
		this.trazeneInformacije = z.getTrazeneInformacije();
		this.datum = z.getDatum();
		this.mesto = z.getMesto();
		this.fusnote = z.getFusnote();
	}
	
	

	@Override
	public String toString() {
		return "ZahtevDTO [text=" + text + ", podnosilac=" + podnosilac + ", adresa=" + adresa
				+ ", drugiPodaciZaKontakt=" + drugiPodaciZaKontakt + ", nazivOrganaVlasti=" + nazivOrganaVlasti
				+ ", sedisteOrgana=" + sedisteOrgana + ", naslov=" + naslov + ", paragrafi=" + paragrafi
				+ ", trazeneInformacije=" + trazeneInformacije + ", datum=" + datum + ", mesto=" + mesto + ", fusnote="
				+ fusnote + "]";
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}
	
	
	
}
