package com.xml.projekat.dto;

public class SearchDTO {
	public String URI;
	public String datum;
	public String korisnicko_ime;
	public String naziv_organa_vlasti;
	
	public String getURI() {
		return URI;
	}
	public void setURI(String uRI) {
		URI = uRI;
	}
	public String getDatum() {
		return datum;
	}
	public void setDatum(String datum) {
		this.datum = datum;
	}
	public String getKorisnicko_ime() {
		return korisnicko_ime;
	}
	public void setKorisnicko_ime(String korisnicko_ime) {
		this.korisnicko_ime = korisnicko_ime;
	}
	public String getNaziv_organa_vlasti() {
		return naziv_organa_vlasti;
	}
	public void setNaziv_organa_vlasti(String naziv_organa_vlasti) {
		this.naziv_organa_vlasti = naziv_organa_vlasti;
	}
	

}
