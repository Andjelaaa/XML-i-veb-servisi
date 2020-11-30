package com.xml.projekat.dto;

import com.xml.projekat.model.Adresa;

public class AdresaDTO {
	private String ulica;
	private String broj;
	private String grad;
	
	public AdresaDTO() {
		super();
	}

	public AdresaDTO(String ulica, String broj, String grad) {
		super();
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
	}
	
	public AdresaDTO(Adresa adresa) {
		// TODO Auto-generated constructor stub
		this.ulica = adresa.getUlica();
		this.broj = adresa.getBroj();
		this.grad = adresa.getGrad();
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
