package com.xml.projekat.model;



public class Resenje {
	
	private NazivOdluka nazivOdluka;
	private TZaglavlje zaglavlje;
	private String opisPostupka;
	private TResenja tekstResenja;
	private TObrazlozenja tekstObrazlozenja;
	private String potpisPoverenika;
	private String korisnickoIme;
	private String URI;
	private String URIZalbaCutanje;
	private String URIZalbaOdluke;
	
	
	
	public Resenje() {
		super();
	}
	

	public Resenje(NazivOdluka nazivOdluka, TZaglavlje zaglavlje, String opisPostupka, TResenja tekstResenja,
			TObrazlozenja tekstObrazlozenja, String potpisPoverenika, String korisnickoIme, String uRI, String uRIZalbaCutanje,
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

	public TResenja getTekstResenja() {
		return tekstResenja;
	}

	public void setTekstResenja(TResenja tekstResenja) {
		this.tekstResenja = tekstResenja;
	}

	public TObrazlozenja getTekstObrazlozenja() {
		return tekstObrazlozenja;
	}

	public void setTekstObrazlozenja(TObrazlozenja tekstObrazlozenja) {
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
