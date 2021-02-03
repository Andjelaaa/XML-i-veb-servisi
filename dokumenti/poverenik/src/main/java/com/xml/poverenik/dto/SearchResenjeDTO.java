package com.xml.poverenik.dto;

public class SearchResenjeDTO {
	public String URI;
	public String datum;
	public String korisnicko_ime;
	public String zalba_cutanje_uri;
	public String zalba_odluke_uri;
	public String brojResenja;
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
	public String getZalba_cutanje_uri() {
		return zalba_cutanje_uri;
	}
	public void setZalba_cutanje_uri(String zalba_cutanje_uri) {
		this.zalba_cutanje_uri = zalba_cutanje_uri;
	}
	public String getZalba_odluke_uri() {
		return zalba_odluke_uri;
	}
	public void setZalba_odluke_uri(String zalba_odluke_uri) {
		this.zalba_odluke_uri = zalba_odluke_uri;
	}
	public String getBrojResenja() {
		return brojResenja;
	}
	public void setBrojResenja(String brojResenja) {
		this.brojResenja = brojResenja;
	}
	
}
