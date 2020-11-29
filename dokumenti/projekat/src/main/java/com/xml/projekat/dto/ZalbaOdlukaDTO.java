package com.xml.projekat.dto;

public class ZalbaOdlukaDTO {
	private String text;

	public ZalbaOdlukaDTO(String text) {
		super();
		this.text = text;
	}

	public ZalbaOdlukaDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
