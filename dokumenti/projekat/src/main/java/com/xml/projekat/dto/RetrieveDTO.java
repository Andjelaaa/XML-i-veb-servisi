package com.xml.projekat.dto;

public class RetrieveDTO {
	private String collectionId;
	private String xpath;

	public RetrieveDTO() {
	}

	public RetrieveDTO(String collectionId, String xpath) {
		this.collectionId = collectionId;
		this.xpath = xpath;
	}

	public String getCollectionId() {
		return collectionId;
	}

	public void setCollectionId(String collectionId) {
		this.collectionId = collectionId;
	}

	public String getXpath() {
		return xpath;
	}

	public void setXpath(String xpath) {
		this.xpath = xpath;
	}

}
