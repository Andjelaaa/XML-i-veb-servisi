package com.xml.projekat.model;

public class Adresa {
	

	private String ulica;
	private String broj;
	private String grad;
	
	public Adresa() {
		super();
	}

	public Adresa(String ulica, String broj, String grad) {
		super();
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
	}
	
	@Override
	public String toString() {
		return "Adresa [ulica=" + ulica + ", broj=" + broj + ", grad=" + grad + "]";
	}
}
