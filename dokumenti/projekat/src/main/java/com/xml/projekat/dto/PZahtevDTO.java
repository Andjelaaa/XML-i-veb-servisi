package com.xml.projekat.dto;

import java.util.ArrayList;

import com.xml.projekat.model.Izbor;

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
