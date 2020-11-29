package com.xml.projekat.dto;

public class ResenjeDTO {
	private String text;

	public ResenjeDTO(String text) {
		super();
		this.text = text;
	}

	public ResenjeDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
