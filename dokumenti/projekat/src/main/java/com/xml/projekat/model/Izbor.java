package com.xml.projekat.model;

import java.util.HashMap;

import com.xml.projekat.dto.IzborDTO;

public class Izbor {
	private int broj;
	private String tekst;
	private HashMap<Integer,String> podizbori;
	private String drugiNacin;
	
	public Izbor() {
		super();
	}

	public Izbor(int broj, String tekst, HashMap<Integer, String> podizbori, String drugiNacin) {
		super();
		this.broj = broj;
		this.tekst = tekst;
		this.podizbori = podizbori;
		this.drugiNacin = drugiNacin;
	}

	
	public Izbor(IzborDTO i) {
		this.broj = i.getBroj();
		this.tekst = i.getTekst();
		this.podizbori = i.getPodizbori();
		this.drugiNacin = i.getDrugiNacin();
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

	@Override
	public String toString() {
		return "Izbor [broj=" + broj + ", tekst=" + tekst + ", podizbori=" + podizbori + ", drugiNacin=" + drugiNacin
				+ "]";
	}
	
	
	
	
}
