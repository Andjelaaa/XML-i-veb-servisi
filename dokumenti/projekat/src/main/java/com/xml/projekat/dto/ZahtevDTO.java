package com.xml.projekat.dto;

public class ZahtevDTO {

	private String text;

	public ZahtevDTO(String text) {
		super();
		this.text = text;
	}

	public ZahtevDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
	
	
}
