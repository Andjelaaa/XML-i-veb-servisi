package com.xml.projekat.model;

import java.util.ArrayList;

public class ZalbaCutanje {
	private Podnosilac podnosilac;
	private Adresa adresa;
	private String drugiPodaciZaKontakt;
	
	private String nazivPoverenika;
	private Adresa sedistePoverenika;
	
	private String naslov;
	private ArrayList<PZalbaCutanje> paragrafi;
	
	
	private String datumZalbe;
	private String mestoZalbe;
	
	public ZalbaCutanje() {
		super();
	}

	public ZalbaCutanje(Podnosilac podnosilac, Adresa adresa, String drugiPodaciZaKontakt, String nazivPoverenika,
			Adresa sedistePoverenika, String naslov, ArrayList<PZalbaCutanje> paragrafi, String datumZalbe, String mestoZalbe) {
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

	public void setDrugiPodaciZaKontalt(String drugiPodaciZaKontakt) {
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
	}

	public String getNazivPoverenika() {
		return nazivPoverenika;
	}

	public void setNazivPoverenika(String nazivPoverenika) {
		this.nazivPoverenika = nazivPoverenika;
	}

	public Adresa getSedistePoverenika() {
		return sedistePoverenika;
	}

	public void setSedistePoverenika(Adresa sedistePoverenika) {
		this.sedistePoverenika = sedistePoverenika;
	}

	public ArrayList<PZalbaCutanje> getParagrafi() {
		return paragrafi;
	}

	public void setParagrafi(ArrayList<PZalbaCutanje> paragrafi) {
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
	
	@Override
	public String toString() {
		return "Zalba cutanje [podnosilac=" + podnosilac + ", adresa=" + adresa + ", drugiPodaciZaKontakt="
				+ drugiPodaciZaKontakt + ", nazivPoverenika=" + nazivPoverenika + ", sedistePoverenika=" + sedistePoverenika + ", naslov= " + naslov +", paragrafi=" + paragrafi +
				", datumZalbe=" + datumZalbe + ", mestoZalbe=" + mestoZalbe + "]";
	}
	
	
}
