package com.xml.projekat.model;

import java.util.ArrayList;

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
	@Override
	public String toString() {
		return "Zahtev [podnosilac=" + podnosilac + ", adresa=" + adresa + ", drugiPodaciZaKontakt="
				+ drugiPodaciZaKontakt + ", nazivOrganaVlasti=" + nazivOrganaVlasti + ", sedisteOrgana=" + sedisteOrgana
				+ ", naslov=" + naslov + ", paragrafi=" + paragrafi + ", trazeneInformacije=" + trazeneInformacije
				+ ", datum=" + datum + ", mesto=" + mesto + ", fusnote=" + fusnote + "]";
	}
	

}
