package com.xml.projekat.dto;

import java.util.HashMap;

import com.xml.projekat.model.Izbor;

public class IzborDTO {
	
	private int broj;
	private String tekst;
	private HashMap<String,String> podizbori;
	private String drugiNacin;
	
	public IzborDTO() {
		super();
	}

	public IzborDTO(int broj, String tekst, HashMap<String, String> podizbori, String drugiNacin) {
		super();
		this.broj = broj;
		this.tekst = tekst;
		this.podizbori = podizbori;
		this.drugiNacin = drugiNacin;
	}

	public IzborDTO(Izbor izbor) {
		this.broj = izbor.getBroj();
		this.tekst = izbor.getTekst();
		this.podizbori = izbor.getPodizbori();
		this.drugiNacin = izbor.getDrugiNacin();
	}

	public int getBroj() {
		return broj;
	}

	public void setBroj(int broj) {
		this.broj = broj;
	}

	public String getTekst() {
		return tekst;
	}

	public void setTekst(String tekst) {
		this.tekst = tekst;
	}

	public HashMap<String, String> getPodizbori() {
		return podizbori;
	}

	public void setPodizbori(HashMap<String, String> podizbori) {
		this.podizbori = podizbori;
	}

	public String getDrugiNacin() {
		return drugiNacin;
	}

	public void setDrugiNacin(String drugiNacin) {
		this.drugiNacin = drugiNacin;
	}
	
	
}
