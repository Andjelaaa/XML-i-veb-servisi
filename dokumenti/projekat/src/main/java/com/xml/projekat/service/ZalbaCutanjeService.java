package com.xml.projekat.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.ZalbaCutanje;

@Service
public class ZalbaCutanjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;	

	public ZalbaCutanjeService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		super();
		this.domParser = domParser;
		this.domWriter = domWriter;
	}
	
	public String parseZalbaCutanje() throws Exception {
		Document document = domParser.buildDocumentFromFile("./../zalba_cutanje.xml");
		ZalbaCutanje zalbaCutanje= domParser.parseZalbaCutanje(document);

		return domParser.getDocumentAsString(document);
	}
	
	public void makeZalbaCutanje(ZalbaCutanje zc) {
		domWriter.generateZalbaCutanje(zc);
	}
	
}
