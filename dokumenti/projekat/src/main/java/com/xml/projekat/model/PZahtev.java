package com.xml.projekat.model;

import java.util.ArrayList;

public class PZahtev {
	private String text;
	private ArrayList<Izbor> izbori;
	public PZahtev(String text, ArrayList<Izbor> izbori) {
		super();
		this.text = text;
		this.izbori = izbori;
	}
	@Override
	public String toString() {
		return "PZahtev [text=" + text + ", izbori=" + izbori + "]";
	}
}
