package com.xml.projekat.model;


import com.xml.projekat.dto.TZaglavljeDTO;

public class TZaglavlje {
	private String brojResenja;
	private String datum;
	
	public TZaglavlje() {
		super();
	}
	
	public TZaglavlje(String brojResenja, String datum) {
		super();
		this.brojResenja = brojResenja;
		this.datum = datum;
	}

	public TZaglavlje(TZaglavljeDTO zaglavlje) {
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
		return "TZaglavlje [brojResenja=" + brojResenja + ", datum=" + datum + "]";
	}
	
}
