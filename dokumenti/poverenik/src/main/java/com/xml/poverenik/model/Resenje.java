package com.xml.poverenik.model;

import com.xml.poverenik.dto.ResenjeDTO;

public class Resenje {
	
	private NazivOdluka nazivOdluka;
	private TZaglavlje zaglavlje;
	private String opisPostupka;
	private TTekst tekstResenja;
	private TTekst tekstObrazlozenja;
	private String potpisPoverenika;
	private String korisnickoIme;
	private String URI;
	private String URIZalbaCutanje;
	private String URIZalbaOdluke;
	
	
	
	public Resenje() {
		super();
	}
	

	public Resenje(NazivOdluka nazivOdluka, TZaglavlje zaglavlje, String opisPostupka, TTekst tekstResenja,
			TTekst tekstObrazlozenja, String potpisPoverenika, String korisnickoIme, String uRI, String uRIZalbaCutanje,
			String uRIZalbaOdluke) {
		super();
		this.nazivOdluka = nazivOdluka;
		this.zaglavlje = zaglavlje;
		this.opisPostupka = opisPostupka;
		this.tekstResenja = tekstResenja;
		this.tekstObrazlozenja = tekstObrazlozenja;
		this.potpisPoverenika = potpisPoverenika;
		this.korisnickoIme = korisnickoIme;
		URI = uRI;
		URIZalbaCutanje = uRIZalbaCutanje;
		URIZalbaOdluke = uRIZalbaOdluke;
	}


	public Resenje(NazivOdluka nazivOdluka, TZaglavlje zaglavlje, String opisPostupka, TTekst tekstResenja,
			TTekst tekstObrazlozenja, String potpisPoverenika) {
		super();
		this.nazivOdluka = nazivOdluka;
		this.zaglavlje = zaglavlje;
		this.opisPostupka = opisPostupka;
		this.tekstResenja = tekstResenja;
		this.tekstObrazlozenja = tekstObrazlozenja;
		this.potpisPoverenika = potpisPoverenika;
	}

	public Resenje(ResenjeDTO dto) {
		this.nazivOdluka = new NazivOdluka(dto.getNaziv(), dto.getOdluka());
		this.zaglavlje = new TZaglavlje(dto.getZaglavlje());
		this.opisPostupka = dto.getOpisPostupka();
		this.tekstResenja = new TTekst(dto.getTekstResenja());
		this.tekstObrazlozenja = new TTekst(dto.getTekstObrazlozenja());
		this.potpisPoverenika = dto.getPotpisPoverenika();
		this.korisnickoIme = dto.getKorisnickoIme();
		this.URI = dto.getURI();
		this.URIZalbaCutanje = dto.getZalbaCutanjeURI();
		this.URIZalbaOdluke = dto.getZalbaOdlukeURI();
	}

	public NazivOdluka getNazivOdluka() {
		return nazivOdluka;
	}

	public void setNazivOdluka(NazivOdluka nazivOdluka) {
		this.nazivOdluka = nazivOdluka;
	}

	public TZaglavlje getZaglavlje() {
		return zaglavlje;
	}

	public void setZaglavlje(TZaglavlje zaglavlje) {
		this.zaglavlje = zaglavlje;
	}

	public String getOpisPostupka() {
		return opisPostupka;
	}

	public void setOpisPostupka(String opisPostupka) {
		this.opisPostupka = opisPostupka;
	}

	public TTekst getTekstResenja() {
		return tekstResenja;
	}

	public void setTekstResenja(TTekst tekstResenja) {
		this.tekstResenja = tekstResenja;
	}

	public TTekst getTekstObrazlozenja() {
		return tekstObrazlozenja;
	}

	public void setTekstObrazlozenja(TTekst tekstObrazlozenja) {
		this.tekstObrazlozenja = tekstObrazlozenja;
	}

	public String getPotpisPoverenika() {
		return potpisPoverenika;
	}

	public void setPotpisPoverenika(String potpisPoverenika) {
		this.potpisPoverenika = potpisPoverenika;
	}

	public String getKorisnickoIme() {
		return korisnickoIme;
	}

	public void setKorisnickoIme(String korisnickoIme) {
		this.korisnickoIme = korisnickoIme;
	}

	public String getURI() {
		return URI;
	}

	public void setURI(String uRI) {
		URI = uRI;
	}

	public String getURIZalbaCutanje() {
		return URIZalbaCutanje;
	}

	public void setURIZalbaCutanje(String uRIZalbaCutanje) {
		URIZalbaCutanje = uRIZalbaCutanje;
	}

	public String getURIZalbaOdluke() {
		return URIZalbaOdluke;
	}

	public void setURIZalbaOdluke(String uRIZalbaOdluke) {
		URIZalbaOdluke = uRIZalbaOdluke;
	}

	@Override
	public String toString() {
		return "Resenje [nazivOdluka=" + nazivOdluka + ", zaglavlje=" + zaglavlje + ", opisPostupka=" + opisPostupka
				+ ", tekstResenja=" + tekstResenja + ", tekstObrazlozenja=" + tekstObrazlozenja + ", potpisPoverenika="
				+ potpisPoverenika + "]";
	}
	
	
}
