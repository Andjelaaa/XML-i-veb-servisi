package com.xml.poverenik.dto;

import java.util.Date;

import com.xml.poverenik.model.TZaglavlje;

public class TZaglavljeDTO {
	private String brojResenja;
	private String datum;
	
	public TZaglavljeDTO() {
		super();
	}
	
	public TZaglavljeDTO(String brojResenja, String datum) {
		super();
		this.brojResenja = brojResenja;
		this.datum = datum;
	}

	public TZaglavljeDTO(TZaglavlje zaglavlje) {
		this.brojResenja = zaglavlje.getBrojResenja();
		this.datum = zaglavlje.getDatum();
	}

	public String getBrojResenja() {
		return brojResenja;
	}

	public void setBrojResenja(String brojResenja) {
		this.brojResenja = brojResenja;
	}

	public String getDatum() {
		return datum;
	}

	public void setDatum(String datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "TZaglavljeDTO [brojResenja=" + brojResenja + ", datum=" + datum + "]";
	}
}
