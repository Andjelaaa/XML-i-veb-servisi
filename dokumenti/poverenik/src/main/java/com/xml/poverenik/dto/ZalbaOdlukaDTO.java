package com.xml.poverenik.dto;

import java.util.ArrayList;

import com.xml.poverenik.model.PZalbaOdluke;
import com.xml.poverenik.model.ZalbaOdluke;


public class ZalbaOdlukaDTO {
	

	private String URI;
	private String zahtevURI;
	
	private String text;
	private PodnosilacDTO nazivPodnosioca;
	private AdresaDTO adresaPodnosioca;
	private String drugiPodaciZaKontakt;
	
	private String nazivPoverenika;
	private AdresaDTO sedistePoverenika;
	
	private String naslov;
	private String nazivOrganaVlasti;
	private ArrayList<PZalbaOdlukeDTO> paragrafi;
	private String mesto;
	private String datum;
	private ArrayList<String> tackeNapomene;
	
	public ZalbaOdlukaDTO() {}
	
	public ZalbaOdlukaDTO( String uRI, String zahtevURI, String text,
			PodnosilacDTO nazivPodnosioca, AdresaDTO adresaPodnosioca, String drugiPodaciZaKontakt,
			String nazivPoverenika, AdresaDTO sedistePoverenika, String naslov, String nazivOrganaVlasti,
			ArrayList<PZalbaOdlukeDTO> paragrafi, String mesto, String datum, ArrayList<String> tackeNapomene) {
		super();
	
		URI = uRI;
		this.zahtevURI = zahtevURI;
		this.text = text;
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

	public ZalbaOdlukaDTO(String text, PodnosilacDTO nazivPodnosioca, AdresaDTO adresaPodnosioca, String drugiPodaciZaKontakt,
			String nazivPoverenika, AdresaDTO sedistePoverenika, String naslov, String nazivOrganaVlasti,
			ArrayList<PZalbaOdlukeDTO> paragrafi, String mesto, String datum, ArrayList<String> tackeNapomene) {
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
		this.text = text;
	}
	public ZalbaOdlukaDTO(String response) {
		this.text = response;
	}
	public ZalbaOdlukaDTO(ZalbaOdluke zo) {
		this.URI = zo.getURI();
		this.nazivPodnosioca = new PodnosilacDTO(zo.getNazivPodnosioca());
		this.adresaPodnosioca = new AdresaDTO(zo.getAdresaPodnosioca());
		this.drugiPodaciZaKontakt = zo.getDrugiPodaciZaKontakt();
		this.nazivPoverenika = zo.getNazivPoverenika();
		this.sedistePoverenika = new AdresaDTO(zo.getSedistePoverenika());
		this.naslov = zo.getNaslov();
		this.nazivOrganaVlasti = zo.getNazivOrganaVlasti();
		this.paragrafi = new ArrayList<PZalbaOdlukeDTO>();
		for (PZalbaOdluke pzo : zo.getParagrafi()) {
			this.paragrafi.add(new PZalbaOdlukeDTO(pzo));
		}
		this.mesto = zo.getMesto();
		this.datum = zo.getDatum();
		this.tackeNapomene = zo.getTackeNapomene();
	}
	@Override
	public String toString() {
		return "ZalbaOdluke [nazivPodnosioca=" + nazivPodnosioca + ", adresaPodnosioca=" + adresaPodnosioca
				+ ", drugiPodaciZaKontakt=" + drugiPodaciZaKontakt + ", nazivPoverenika=" + nazivPoverenika
				+ ", sedistePoverenika=" + sedistePoverenika + ", naslov=" + naslov + ", nazivOrganaVlasti="
				+ nazivOrganaVlasti + ", paragrafi=" + paragrafi + ", mesto=" + mesto + ", datum=" + datum
				+ ", tackeNapomene=" + tackeNapomene + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public PodnosilacDTO getNazivPodnosioca() {
		return nazivPodnosioca;
	}
	public void setNazivPodnosioca(PodnosilacDTO nazivPodnosioca) {
		this.nazivPodnosioca = nazivPodnosioca;
	}
	public AdresaDTO getAdresaPodnosioca() {
		return adresaPodnosioca;
	}
	public void setAdresaPodnosioca(AdresaDTO adresaPodnosioca) {
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
	public AdresaDTO getSedistePoverenika() {
		return sedistePoverenika;
	}
	public void setSedistePoverenika(AdresaDTO sedistePoverenika) {
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
	public ArrayList<PZalbaOdlukeDTO> getParagrafi() {
		return paragrafi;
	}
	public void setParagrafi(ArrayList<PZalbaOdlukeDTO> paragrafi) {
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


}
