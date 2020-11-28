package com.xml.projekat.model;

import java.util.ArrayList;
import java.util.HashMap;

public class Izbor {
	private int broj;
	private String tekst;
	private HashMap<Integer,String> podizbori;
	private String drugiNacin;
	
	public Izbor() {
		super();
	}

	public Izbor(int broj, String tekst, HashMap<Integer, String> podizbori, String drugiNacin) {
		super();
		this.broj = broj;
		this.tekst = tekst;
		this.podizbori = podizbori;
		this.drugiNacin = drugiNacin;
	}
	
	
	
	
}
