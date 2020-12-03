package com.xml.projekat.dto;

public class AdresaaDTO {

	private String ulica;
	private String broj;
	private String grad;
	
	public AdresaaDTO() {
		super();
	}

	public AdresaaDTO(String ulica, String broj, String grad) {
		super();
		this.ulica = ulica;
		this.broj = broj;
		this.grad = grad;
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
