package com.xml.projekat.dto;

public class ObavestenjeDTO {

	private String text;

	public ObavestenjeDTO(String text) {
		super();
		this.text = text;
	}

	public ObavestenjeDTO() {
		super();
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
}
