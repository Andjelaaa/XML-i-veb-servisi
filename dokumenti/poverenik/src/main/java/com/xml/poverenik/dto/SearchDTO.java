package com.xml.poverenik.dto;

public class SearchDTO {
	public String URI;
	public String datum;
	public String korisnicko_ime;
	public String naziv_poverenika;
	public String zahtev_uri;
	
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
	public String getNaziv_poverenika() {
		return naziv_poverenika;
	}
	public void setNaziv_poverenika(String naziv_poverenika) {
		this.naziv_poverenika = naziv_poverenika;
	}
	public String getZahtev_uri() {
		return zahtev_uri;
	}
	public void setZahtev_uri(String zahtev_uri) {
		this.zahtev_uri = zahtev_uri;
	}

}
