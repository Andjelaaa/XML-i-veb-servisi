package com.xml.projekat.model;

import java.util.ArrayList;

public class ZalbaOdluke {
	private Podnosilac nazivPodnosioca;
	private Adresa adresaPodnosioca;
	private String drugiPodaciZaKontakt;
	
	private String nazivPoverenika;
	private Adresa sedistePoverenika;
	
	private String naslov;
	private String nazivOrganaVlasti;
	private ArrayList<PZalbaOdluke> paragrafi;
	private String mesto;
	private String datum;
	private ArrayList<String> tackeNapomene;
	
	public ZalbaOdluke(Podnosilac nazivPodnosioca, Adresa adresaPodnosioca, String drugiPodaciZaKontakt,
			String nazivPoverenika, Adresa sedistePoverenika, String naslov, String nazivOrganaVlasti,
			ArrayList<PZalbaOdluke> paragrafi, String mesto, String datum, ArrayList<String> tackeNapomene) {
		super();
		this.nazivPodnosioca = nazivPodnosioca;
		this.adresaPodnosioca = adresaPodnosioca;
		this.drugiPodaciZaKontakt = drugiPodaciZaKontakt;
		this.nazivPoverenika = nazivPoverenika;
		this.sedistePoverenika = sedistePoverenika;
		this.naslov = naslov;
		this.nazivOrganaVlasti = nazivOrganaVlasti;
		this.paragrafi = paragrafi;
		this.mesto = mesto;
		this.datum = datum;
		this.tackeNapomene = tackeNapomene;
	}
	@Override
	public String toString() {
		return "ZalbaOdluke [nazivPodnosioca=" + nazivPodnosioca + ", adresaPodnosioca=" + adresaPodnosioca
				+ ", drugiPodaciZaKontakt=" + drugiPodaciZaKontakt + ", nazivPoverenika=" + nazivPoverenika
				+ ", sedistePoverenika=" + sedistePoverenika + ", naslov=" + naslov + ", nazivOrganaVlasti="
				+ nazivOrganaVlasti + ", paragrafi=" + paragrafi + ", mesto=" + mesto + ", datum=" + datum
				+ ", tackeNapomene=" + tackeNapomene + "]";
	}
	public Podnosilac getNazivPodnosioca() {
		return nazivPodnosioca;
	}
	public void setNazivPodnosioca(Podnosilac nazivPodnosioca) {
		this.nazivPodnosioca = nazivPodnosioca;
	}
	public Adresa getAdresaPodnosioca() {
		return adresaPodnosioca;
	}
	public void setAdresaPodnosioca(Adresa adresaPodnosioca) {
		this.adresaPodnosioca = adresaPodnosioca;
	}
	public String getDrugiPodaciZaKontakt() {
		return drugiPodaciZaKontakt;
	}
	public void setDrugiPodaciZaKontakt(String drugiPodaciZaKontakt) {
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
	public String getNaslov() {
		return naslov;
	}
	public void setNaslov(String naslov) {
		this.naslov = naslov;
	}
	public String getNazivOrganaVlasti() {
		return nazivOrganaVlasti;
	}
	public void setNazivOrganaVlasti(String nazivOrganaVlasti) {
		this.nazivOrganaVlasti = nazivOrganaVlasti;
	}
	public ArrayList<PZalbaOdluke> getParagrafi() {
		return paragrafi;
	}
	public void setParagrafi(ArrayList<PZalbaOdluke> paragrafi) {
		this.paragrafi = paragrafi;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public ArrayList<String> getTackeNapomene() {
		return tackeNapomene;
	}
	public void setTackeNapomene(ArrayList<String> tackeNapomene) {
		this.tackeNapomene = tackeNapomene;
	}
	

}
