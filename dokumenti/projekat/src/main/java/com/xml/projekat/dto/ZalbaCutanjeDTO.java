package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.PZalbaCutanje;
import com.xml.projekat.model.ZalbaCutanje;


public class ZalbaCutanjeDTO {
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

	@Override
	public String toString() {
		return "Zalba cutanje [podnosilac=" + podnosilac + ", adresa=" + adresa + ", drugiPodaciZaKontakt="
				+ drugiPodaciZaKontakt + ", nazivPoverenika=" + nazivPoverenika + ", sedistePoverenika=" + sedistePoverenika + ", naslov= " + naslov +", paragrafi=" + paragrafi +
				", datumZalbe=" + datumZalbe + ", mestoZalbe=" + mestoZalbe + "]";
	}
	
}
