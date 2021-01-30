package com.xml.projekat.model;

import java.util.ArrayList;

import com.xml.projekat.dto.IzborDTO;
import com.xml.projekat.dto.PZahtevDTO;

public class PZahtev {
	
	private String text;
	private ArrayList<Izbor> izbori;
	public PZahtev(String text, ArrayList<Izbor> izbori) {
		super();
		this.text = text;
		this.izbori = izbori;
	}
	
	public PZahtev(PZahtevDTO pzdto) {
		this.text = pzdto.getText();
		this.izbori = new ArrayList<Izbor>();
		if(pzdto.getIzbori() != null) {
			for (IzborDTO i : pzdto.getIzbori()) {
				this.izbori.add(new Izbor(i));
			}
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<Izbor> getIzbori() {
		return izbori;
	}

	public void setIzbori(ArrayList<Izbor> izbori) {
		this.izbori = izbori;
	}

	@Override
	public String toString() {
		return "PZahtev [text=" + text + ", izbori=" + izbori + "]";
	}
}
