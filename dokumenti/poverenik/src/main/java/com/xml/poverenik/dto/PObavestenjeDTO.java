package com.xml.poverenik.dto;

import java.util.ArrayList;

import com.xml.poverenik.model.PObavestenje;

public class PObavestenjeDTO {
	
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
	public PObavestenjeDTO() {
		super();
	}
	public PObavestenjeDTO(String godina, String trazenaInformacija, String dan, String sati, String odSati,
			String doSati, String mesto, String ulica, String brojZgrade, String brojKancelarije,
			String novcanaNaknada) {
		super();
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
	
	public static ArrayList<PObavestenjeDTO> konverter(ArrayList<PObavestenje> pobavestenjeDTO) {
		ArrayList<PObavestenjeDTO> dto = new ArrayList<PObavestenjeDTO>();
		for(PObavestenje p: pobavestenjeDTO) {
			PObavestenjeDTO po = new PObavestenjeDTO();
			po.setGodina(p.getGodina());
			po.setTrazenaInformacija(p.getTrazenaInformacija());
			po.setDan(p.getDan());
			po.setOdSati(p.getOdSati());
			po.setDoSati(p.getDoSati());
			po.setMesto(p.getMesto());
			po.setUlica(p.getUlica());
			po.setBrojZgrade(p.getBrojZgrade());
			po.setBrojKancelarije(p.getBrojKancelarije());
			po.setNovcanaNaknada(p.getNovcanaNaknada());
			dto.add(po);
			
		}
		return dto;	
		
	}
	public String getDoSati() {
		return doSati;
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
	public String novcanaNaknada() {
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
	@Override
	public String toString() {
		return "PObavestenjeDTO [godina=" + godina + ", trazenaInformacija=" + trazenaInformacija + ", dan=" + dan
				+ ", sati=" + sati + ", odSati=" + odSati + ", doSati=" + doSati + ", mesto=" + mesto + ", ulica="
				+ ulica + ", brojZgrade=" + brojZgrade + ", brojKancelarije=" + brojKancelarije + ", novcanaNaknada="
				+ novcanaNaknada + "]";
	}
	
	

}
