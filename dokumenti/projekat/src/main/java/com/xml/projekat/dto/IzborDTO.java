package com.xml.projekat.dto;

import java.util.HashMap;

public class IzborDTO {
	
	private int broj;
	private String tekst;
	private HashMap<Integer,String> podizbori;
	private String drugiNacin;
	
	public IzborDTO() {
		super();
	}

	public IzborDTO(int broj, String tekst, HashMap<Integer, String> podizbori, String drugiNacin) {
		super();
		this.broj = broj;
		this.tekst = tekst;
		this.podizbori = podizbori;
		this.drugiNacin = drugiNacin;
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

	public HashMap<Integer, String> getPodizbori() {
		return podizbori;
	}

	public void setPodizbori(HashMap<Integer, String> podizbori) {
		this.podizbori = podizbori;
	}

	public String getDrugiNacin() {
		return drugiNacin;
	}

	public void setDrugiNacin(String drugiNacin) {
		this.drugiNacin = drugiNacin;
	}
	
	
}
