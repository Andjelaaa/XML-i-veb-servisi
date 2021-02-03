package com.xml.projekat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.database.ExistManager;

@Repository
public class IzvestajRepository {
	
	@Autowired
	private ExistManager existManager;

	private String collectionId = "/db/dokumenti/izvestaji";

	public Document find(String name) {
		Document document = null;
		if (!name.endsWith(".xml")) {
			name = name + ".xml";
		}
		try {
			XMLResource xmlResource = existManager.load(collectionId, name.replace(" ", "_"));
			document = (Document) xmlResource.getContentAsDOM();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	public int getSize() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		return existManager.getCollectionSize(collectionId);
		
	}

	public String save(String xmlEntity, String title) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		existManager.storeXMLFromText(collectionId, title, xmlEntity);

		return "OK";
		
	}

	public String findIzvestaj(String name) throws XMLDBException {
		XMLResource xmlResource = null;
		if (!name.endsWith(".xml")) {
			name = name + ".xml";
		}
		try {
			xmlResource = existManager.load(collectionId, name.replace(" ", "_"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (String) xmlResource.getContent();
	}
	


	public ResourceSet findIzvestaje(String xPathExpression) {
		ResourceSet result = null;
		try {
			result = existManager.retrieve(collectionId, xPathExpression);
			return result;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

}
