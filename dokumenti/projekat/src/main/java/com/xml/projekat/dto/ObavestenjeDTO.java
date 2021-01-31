package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.Adresa;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.PObavestenje;
import com.xml.projekat.model.Podnosilac;

public class ObavestenjeDTO {
	private String URI;
	private String zahtevURI;
	private String text;
	private PodnosilacDTO podnosilac;
	private AdresaDTO adresa;
	
	private String nazivOrganaVlasti;
	private String sedisteOrgana;
	
	private ArrayList<String> dostavljeno;
	
	private String datum;
	private String naslov;
	private String brojPredmeta;

	private String mestoPecata;
	private ArrayList<PObavestenjeDTO> pobavestenjeDTO;

	
	public ObavestenjeDTO() {
		super();
	}
	
	public ObavestenjeDTO(String text) {
		super();
		this.text = text;
	}
  
	public ObavestenjeDTO(Obavestenje o) {
		this(o.getPodnosilac(),o.getAdresa(),o.getNazivOrganaVlasti(),o.getSedisteOrgana(),
				o.getDostavljeno(),o.getDatum(),o.getNaslov(),o.getBrojPredmeta(),o.getMestoPecata(),o.getParagrafi(), o.getZahtevURI());
	}
	
	public ObavestenjeDTO(Podnosilac podnosilac, Adresa adresa, String nazivOrganaVlasti, String sedisteOrgana,
			ArrayList<String> dostavljeno, String datum, String naslov, String brojPredmeta, String mestoPecata,
			ArrayList<PObavestenje> pobavestenje, String zahtevURI) {
		this.zahtevURI = zahtevURI;
		this.podnosilac = new PodnosilacDTO(podnosilac);
		this.adresa = new AdresaDTO(adresa);
		this.nazivOrganaVlasti = nazivOrganaVlasti;
		this.sedisteOrgana = sedisteOrgana;
		this.dostavljeno = dostavljeno;
		this.datum = datum;
		this.naslov = naslov;
		this.brojPredmeta = brojPredmeta;
		this.mestoPecata = mestoPecata;
		this.pobavestenjeDTO = PObavestenjeDTO.konverter(pobavestenje);
		
	}
	
	public ObavestenjeDTO(String text, Podnosilac podnosilac, Adresa adresa, String nazivOrganaVlasti, String sedisteOrgana,
			ArrayList<String> dostavljeno, String datum, String naslov, String brojPredmeta, String mestoPecata,
			ArrayList<PObavestenje> pobavestenje) {
		this.podnosilac = new PodnosilacDTO(podnosilac);
		this.adresa = new AdresaDTO(adresa);
		this.nazivOrganaVlasti = nazivOrganaVlasti;
		this.sedisteOrgana = sedisteOrgana;
		this.dostavljeno = dostavljeno;
		this.datum = datum;
		this.naslov = naslov;
		this.brojPredmeta = brojPredmeta;
		this.mestoPecata = mestoPecata;
		this.pobavestenjeDTO = PObavestenjeDTO.konverter(pobavestenje);
		this.text = text;		
	}
	

	public String getZahtevURI() {
		return zahtevURI;
	}

	public void setZahtevURI(String zahtevURI) {
		this.zahtevURI = zahtevURI;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
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

	public ArrayList<String> getDostavljeno() {
		return dostavljeno;
	}

	public void setDostavljeno(ArrayList<String> dostavljeno) {
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


	public ArrayList<PObavestenjeDTO> getPobavestenjeDTO() {
		return pobavestenjeDTO;
	}

	public void setPobavestenjeDTO(ArrayList<PObavestenjeDTO> pobavestenjeDTO) {
		this.pobavestenjeDTO = pobavestenjeDTO;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String toString() {
		return "ObavestenjeDTO [podnosilac=" + podnosilac + ", adresa=" + adresa + ", nazivOrganaVlasti="
				+ nazivOrganaVlasti + ", sedisteOrgana=" + sedisteOrgana + ", dostavljeno=" + dostavljeno + ", datum="
				+ datum + ", naslov=" + naslov + ", brojPredmeta=" + brojPredmeta + ", mestoPecata=" + mestoPecata
				+ "\n, klasa sa atributima =" + pobavestenjeDTO +  "]";
	}


}
