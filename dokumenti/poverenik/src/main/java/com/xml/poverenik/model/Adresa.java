package com.xml.poverenik.model;

import com.xml.poverenik.dto.AdresaDTO;

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
	
	public Adresa(AdresaDTO adresa) {
		this.ulica = adresa.getUlica();
		this.broj = adresa.getBroj();
		this.grad =adresa.getGrad();
	}

	@Override
	public String toString() {
		return "Adresa [ulica=" + ulica + ", broj=" + broj + ", grad=" + grad + "]";
	}

	public String getUlica() {
		return ulica;
	}

	public void setUlica(String ulica) {
		this.ulica = ulica;
	}

	public String getBroj() {
		return broj;
	}

	public void setBroj(String broj) {
		this.broj = broj;
	}

	public String getGrad() {
		return grad;
	}

	public void setGrad(String grad) {
		this.grad = grad;
	}
	
}

