package com.xml.projekat.model;

import com.xml.projekat.dto.PObavestenjeDTO;

public class PObavestenje {
	
	private String text;
	private String godina;
	private String trazenaInformacija;

	private String dan;
	private String sati;
	private String odSati;
	private String doSati;
	private String mesto;
	private String ulica;
	private String brojZgrade;
	private String brojKancelarije;
	private String novcanaNaknada;
	public PObavestenje(String text, String godina, String trazenaInformacija, String dan, String sati, String odSati, String doSati,
			String mesto, String ulica, String brojZgrade, String brojKancelarije) {
		super();
		this.text = text;
		this.godina = godina;
		this.trazenaInformacija = trazenaInformacija;
		this.dan = dan;
		this.sati = sati;
		this.odSati = odSati;
		this.doSati = doSati;
		this.mesto = mesto;
		this.ulica = ulica;
		this.brojZgrade = brojZgrade;
		this.brojKancelarije = brojKancelarije;
		
	}
	public PObavestenje(String text, String godina, String trazenaInformacija, String dan, String sati, String odSati, String doSati,
			String mesto, String ulica, String brojZgrade, String brojKancelarije, String novcanaNaknada) {
		super();
		this.text = text;
		this.godina = godina;
		this.trazenaInformacija = trazenaInformacija;
		this.dan = dan;
		this.sati = sati;
		this.odSati = odSati;
		this.doSati = doSati;
		this.mesto = mesto;
		this.ulica = ulica;
		this.brojZgrade = brojZgrade;
		this.brojKancelarije = brojKancelarije;
		this.novcanaNaknada = novcanaNaknada;
		
	}
	public PObavestenje(String text,String novcanaNaknada) {
		super();
		this.text = text;
		this.novcanaNaknada = novcanaNaknada;
	}
	public PObavestenje(PObavestenjeDTO po) {

		this.godina = po.getGodina();
		this.trazenaInformacija = po.getTrazenaInformacija();
		this.dan =po.getDan();
		this.sati =po.getSati();
		this.odSati =po.getOdSati();
		this.doSati = po.getDoSati();
		this.mesto = po.getMesto();
		this.ulica = po.getUlica();
		this.brojZgrade = po.getBrojZgrade();
		this.brojKancelarije = po.getBrojKancelarije();
		this.novcanaNaknada = po.getNovcanaNaknada();
	}
	public PObavestenje() {
		super();
	}
	
	public String toString2() {
		return "PObavestenje [text=" + text + ", godina=" + godina + ", trazenaInformacija=" + trazenaInformacija
				+ ", dan=" + dan + ", sati=" + sati + ", odSati=" + odSati + ", doSati=" + doSati + ", mesto=" + mesto
				+ ", ulica=" + ulica + ", brojZgrade=" + brojZgrade + ", brojKancelarije=" + brojKancelarije
				+ "]";
	}
	public String toString1() {
		return "PObavestenje [text=" + text + "novcanaNaknada= " + novcanaNaknada + "]";
	}@Override
	public String toString() {
		return "PObavestenje [text=" + text + ", godina=" + godina + ", trazenaInformacija=" + trazenaInformacija
				+ ", dan=" + dan + ", sati=" + sati + ", odSati=" + odSati + ", doSati=" + doSati + ", mesto=" + mesto
				+ ", ulica=" + ulica + ", brojZgrade=" + brojZgrade + ", brojKancelarije=" + brojKancelarije
				+ "novcanaNaknada= " + novcanaNaknada + "]";
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getGodina() {
		return godina;
	}
	public void setGodina(String godina) {
		this.godina = godina;
	}
	public String getTrazenaInformacija() {
		return trazenaInformacija;
	}
	public void setTrazenaInformacija(String trazenaInformacija) {
		this.trazenaInformacija = trazenaInformacija;
	}
	public String getDan() {
		return dan;
	}
	public void setDan(String dan) {
		this.dan = dan;
	}
	public String getSati() {
		return sati;
	}
	public void setSati(String sati) {
		this.sati = sati;
	}
	public String getOdSati() {
		return odSati;
	}
	public void setOdSati(String odSati) {
		this.odSati = odSati;
	}
	public String getDoSati() {
		return doSati;
	}
	public void setDoSati(String doSati) {
		this.doSati = doSati;
	}
	public String getMesto() {
		return mesto;
	}
	public void setMesto(String mesto) {
		this.mesto = mesto;
	}
	public String getUlica() {
		return ulica;
	}
	public void setUlica(String ulica) {
		this.ulica = ulica;
	}
	public String getBrojZgrade() {
		return brojZgrade;
	}
	public void setBrojZgrade(String brojZgrade) {
		this.brojZgrade = brojZgrade;
	}
	public String getBrojKancelarije() {
		return brojKancelarije;
	}
	public void setBrojKancelarije(String brojKancelarije) {
		this.brojKancelarije = brojKancelarije;
	}
	public String getNovcanaNaknada() {
		return novcanaNaknada;
	}
	public void setNovcanaNaknada(String novcanaNaknada) {
		this.novcanaNaknada = novcanaNaknada;
	}
	
	
	
}
