package com.xml.projekat.model;

import java.util.Date;

public class TZaglavlje {
	private String brojResenja;
	private Date datum;
	
	public TZaglavlje() {
		super();
	}
	
	public TZaglavlje(String brojResenja, Date datum) {
		super();
		this.brojResenja = brojResenja;
		this.datum = datum;
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
		return "TZaglavlje [brojResenja=" + brojResenja + ", datum=" + datum + "]";
	}
	
}
