package com.xml.poverenik.dto;

import com.xml.poverenik.model.Resenje;

public class ResenjeDTO {
	private String text;
	private String naziv;
	private String odluka;
	private TZaglavljeDTO zaglavlje;
	private String opisPostupka;
	private TTekstDTO tekstResenja;
	private TTekstDTO tekstObrazlozenja;
	private String potpisPoverenika;
	
	

	public ResenjeDTO() {
		super();
	}

	public ResenjeDTO(String text, String naziv, String odluka, TZaglavljeDTO zaglavlje, String opisPostupka,
			TTekstDTO tekstResenja, TTekstDTO tekstObrazlozenja, String potpisPoverenika) {
		super();
		this.text = text;
		this.naziv = naziv;
		this.odluka = odluka;
		this.zaglavlje = zaglavlje;
		this.opisPostupka = opisPostupka;
		this.tekstResenja = tekstResenja;
		this.tekstObrazlozenja = tekstObrazlozenja;
		this.potpisPoverenika = potpisPoverenika;
	}

	public ResenjeDTO(String text) {
		super();
		this.text = text;
	}

	public ResenjeDTO(Resenje entity) {
		this.naziv = entity.getNazivOdluka().getNazivResenja();
		this.odluka = entity.getNazivOdluka().getOdluka();
		this.zaglavlje = new TZaglavljeDTO(entity.getZaglavlje());
		this.opisPostupka = entity.getOpisPostupka();
		this.tekstResenja = new TTekstDTO(entity.getTekstResenja());
		this.tekstObrazlozenja = new TTekstDTO(entity.getTekstObrazlozenja());
		this.potpisPoverenika = entity.getPotpisPoverenika();
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOdluka() {
		return odluka;
	}

	public void setOdluka(String odluka) {
		this.odluka = odluka;
	}

	public TZaglavljeDTO getZaglavlje() {
		return zaglavlje;
	}

	public void setZaglavlje(TZaglavljeDTO zaglavlje) {
		this.zaglavlje = zaglavlje;
	}

	public String getOpisPostupka() {
		return opisPostupka;
	}

	public void setOpisPostupka(String opisPostupka) {
		this.opisPostupka = opisPostupka;
	}

	public TTekstDTO getTekstResenja() {
		return tekstResenja;
	}

	public void setTekstResenja(TTekstDTO tekstResenja) {
		this.tekstResenja = tekstResenja;
	}

	public TTekstDTO getTekstObrazlozenja() {
		return tekstObrazlozenja;
	}

	public void setTekstObrazlozenja(TTekstDTO tekstObrazlozenja) {
		this.tekstObrazlozenja = tekstObrazlozenja;
	}

	public String getPotpisPoverenika() {
		return potpisPoverenika;
	}

	public void setPotpisPoverenika(String potpisPoverenika) {
		this.potpisPoverenika = potpisPoverenika;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
