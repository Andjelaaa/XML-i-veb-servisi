package com.xml.projekat.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.ZalbaCutanje;

@Service
public class ZalbaCutanjeService {
	private final DOMParser domParser;
	private final JaxB jaxB;
	

	public ZalbaCutanjeService(DOMParser domParser, JaxB jaxB) {
		super();
		this.domParser = domParser;
		this.jaxB = jaxB;
	}
	
	public String parseZalbaCutanje() throws Exception {
		Document document = domParser.buildDocumentFromFile("C:\\Users\\Tamara\\Desktop\\XML-i-veb-servisi\\dokumenti\\zalba_cutanje.xml");
		ZalbaCutanje zalbaCutanje= domParser.parseZalbaCutanje(document);

		return domParser.getDocumentAsString(document);

	}
	
}
