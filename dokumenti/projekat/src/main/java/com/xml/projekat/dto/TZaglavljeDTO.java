package com.xml.projekat.dto;

import java.util.Date;

import com.xml.projekat.model.TZaglavlje;

public class TZaglavljeDTO {
	private String brojResenja;
	private Date datum;
	
	public TZaglavljeDTO() {
		super();
	}
	
	public TZaglavljeDTO(String brojResenja, Date datum) {
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

	public Date getDatum() {
		return datum;
	}

	public void setDatum(Date datum) {
		this.datum = datum;
	}

	@Override
	public String toString() {
		return "TZaglavljeDTO [brojResenja=" + brojResenja + ", datum=" + datum + "]";
	}
}
