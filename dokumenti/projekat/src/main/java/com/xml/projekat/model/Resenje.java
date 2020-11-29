package com.xml.projekat.model;



public class Resenje {
	
	private NazivOdluka nazivOdluka;
	private TZaglavlje zaglavlje;
	private String opisPostupka;
	private TTekst tekstResenja;
	private TTekst tekstObrazlozenja;
	private String potpisPoverenika;
	
	public Resenje() {
		super();
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

	@Override
	public String toString() {
		return "Resenje [nazivOdluka=" + nazivOdluka + ", zaglavlje=" + zaglavlje + ", opisPostupka=" + opisPostupka
				+ ", tekstResenja=" + tekstResenja + ", tekstObrazlozenja=" + tekstObrazlozenja + ", potpisPoverenika="
				+ potpisPoverenika + "]";
	}
	
	
}
