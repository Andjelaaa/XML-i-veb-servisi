package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.Izbor;
import com.xml.projekat.model.PZahtev;

public class PZahtevDTO {
	
	private String text;
	private ArrayList<IzborDTO> izbori;
	
	public PZahtevDTO() {
		super();
	}
	
	public PZahtevDTO(String text, ArrayList<IzborDTO> izbori) {
		super();
		this.text = text;
		this.izbori = izbori;
	}

	public PZahtevDTO(PZahtev pz) {
		this.text = pz.getText();
		this.izbori = new ArrayList<IzborDTO>();
		for (Izbor izbor : pz.getIzbori()) {
			this.izbori.add(new IzborDTO(izbor));
		}
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public ArrayList<IzborDTO> getIzbori() {
		return izbori;
	}

	public void setIzbori(ArrayList<IzborDTO> izbori) {
		this.izbori = izbori;
	}
	
	
	
}
