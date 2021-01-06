package com.xml.projekat.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.database.ExistManager;


@Repository
public class ZalbaOdlukeRepository {

	@Autowired
	private ExistManager existManager;

	private String collectionId = "/db/dokumenti/zalbeOdluke";
	
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
	
	public String save(String xmlEntity, String title)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.storeXMLFromText(collectionId, title, xmlEntity);

		return "OK";
	}
	
	public Integer getSize() throws XMLDBException {
		return existManager.getCollectionSize(collectionId);
	}
}
