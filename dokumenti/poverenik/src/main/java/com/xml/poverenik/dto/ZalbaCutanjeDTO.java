package com.xml.poverenik.dto;

import java.util.ArrayList;

import com.xml.poverenik.model.PZalbaCutanje;
import com.xml.poverenik.model.ZalbaCutanje;


public class ZalbaCutanjeDTO {
	
	private String URI;
	private String zahtevURI;
	private String text;
	private PodnosilacDTO podnosilac;
	private AdresaDTO adresa;
	private String drugiPodaciZaKontakt;
	
	private String nazivPoverenika;
	private AdresaDTO sedistePoverenika;
	
	private String naslov;
	private ArrayList<PZalbaCutanjeDTO> paragrafi;
	
	
	private String datumZalbe;
	private String mestoZalbe;
	
	public ZalbaCutanjeDTO() {
		super();
	}

	public ZalbaCutanjeDTO( String uRI, String zahtevURI, String text, PodnosilacDTO podnosilac,
			AdresaDTO adresa, String drugiPodaciZaKontakt, String nazivPoverenika, AdresaDTO sedistePoverenika,
			String naslov, ArrayList<PZalbaCutanjeDTO> paragrafi, String datumZalbe, String mestoZalbe) {
		super();

		URI = uRI;
		this.zahtevURI = zahtevURI;
		this.text = text;
		this.podnosilac = podnosilac;
		this.adresa = adresa;
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
		this.nazivPoverenika = nazivPoverenika;
		this.sedistePoverenika = sedistePoverenika;
		this.naslov = naslov;
		this.paragrafi = paragrafi;
		this.datumZalbe = datumZalbe;
		this.mestoZalbe = mestoZalbe;
	}

	public ZalbaCutanjeDTO(String text, PodnosilacDTO podnosilac, AdresaDTO adresa, String drugiPodaciZaKontakt, String nazivPoverenika,
			AdresaDTO sedistePoverenika, String naslov, ArrayList<PZalbaCutanjeDTO> paragrafi, String datumZalbe, String mestoZalbe) {
		super();
		this.podnosilac = podnosilac;
		this.adresa = adresa;
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
		this.nazivPoverenika = nazivPoverenika;
		this.sedistePoverenika = sedistePoverenika;
		this.naslov = naslov;
		this.paragrafi = paragrafi;
		this.datumZalbe = datumZalbe;
		this.mestoZalbe = mestoZalbe;
		this.text = text;
	}

	
	public ZalbaCutanjeDTO(String response) {
		this.text = response;
	}

	public ZalbaCutanjeDTO(ZalbaCutanje zc) {
		this.URI = zc.getURI();
		this.zahtevURI = zc.getZahtevURI();
		this.podnosilac = new PodnosilacDTO(zc.getPodnosilac());
		this.adresa = new AdresaDTO(zc.getAdresa());
		this.drugiPodaciZaKontakt = zc.getDrugiPodaciZaKontakt();
		this.nazivPoverenika = zc.getNazivPoverenika();
		this.sedistePoverenika = new AdresaDTO(zc.getSedistePoverenika());
		this.naslov = zc.getNaslov();
		this.paragrafi = new ArrayList<PZalbaCutanjeDTO>();
		for (PZalbaCutanje p : zc.getParagrafi()) {
			this.paragrafi.add(new PZalbaCutanjeDTO(p));
		}
		this.datumZalbe = zc.getDatumZalbe();
		this.mestoZalbe = zc.getMestoZalbe();
	}

	public String getNaslov() {
		return naslov;
	}

	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}

	public void setDrugiPodaciZaKontakt(String drugiPodaciZaKontakt) {
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
	}

	public String getDrugiPodaciZaKontakt() {
		return drugiPodaciZaKontakt;
	}

	public void setDrugiPodaciZaKontalt(String drugiPodaciZaKontakt) {
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
	}

	public String getNazivPoverenika() {
		return nazivPoverenika;
	}

	public void setNazivPoverenika(String nazivPoverenika) {
		this.nazivPoverenika = nazivPoverenika;
	}

	public AdresaDTO getSedistePoverenika() {
		return sedistePoverenika;
	}

	public void setSedistePoverenika(AdresaDTO sedistePoverenika) {
		this.sedistePoverenika = sedistePoverenika;
	}

	public ArrayList<PZalbaCutanjeDTO> getParagrafi() {
		return paragrafi;
	}

	public void setParagrafi(ArrayList<PZalbaCutanjeDTO> paragrafi) {
		this.paragrafi = paragrafi;
	}

	public String getDatumZalbe() {
		return datumZalbe;
	}

	public void setDatumZalbe(String datumZalbe) {
		this.datumZalbe = datumZalbe;
	}

	public String getMestoZalbe() {
		return mestoZalbe;
	}

	public void setMestoZalbe(String mestoZalbe) {
		this.mestoZalbe = mestoZalbe;
	}
	
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
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

	

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getZahtevURI() {
		return zahtevURI;
	}

	public void setZahtevURI(String zahtevURI) {
		this.zahtevURI = zahtevURI;
	}

	@Override
	public String toString() {
		return "ZalbaCutanjeDTO [URI=" + URI + ", zahtevURI=" + zahtevURI + ", text=" + text + ", podnosilac="
				+ podnosilac + ", adresa=" + adresa + ", drugiPodaciZaKontakt=" + drugiPodaciZaKontakt
				+ ", nazivPoverenika=" + nazivPoverenika + ", sedistePoverenika=" + sedistePoverenika + ", naslov="
				+ naslov + ", paragrafi=" + paragrafi + ", datumZalbe=" + datumZalbe + ", mestoZalbe=" + mestoZalbe
				+ "]";
	}

	
}
