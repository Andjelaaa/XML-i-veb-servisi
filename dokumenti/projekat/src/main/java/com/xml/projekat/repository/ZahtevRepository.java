package com.xml.projekat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.database.ExistManager;


@Repository
public class ZahtevRepository {

	@Autowired
	private ExistManager existMenager;

	private String collectionId = "/db/documents/zahtevi";
	
	public Document find(String name) {
		Document document = null;
		if (!name.endsWith(".xml")) {
			name = name + ".xml";
		}
		try {
			XMLResource xmlResource = existMenager.load(collectionId, name.replace(" ", "_"));
			document = (Document) xmlResource.getContentAsDOM();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}
	
	public String save(String xmlEntity, String title)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existMenager.storeXMLFromText(collectionId, title, xmlEntity);

		return "OK";
	}
}
