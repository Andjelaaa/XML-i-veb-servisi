package com.xml.projekat.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.Resenje;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	private final JaxB jaxB;
	
	public ResenjeService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		this.domParser = domParser;
		this.jaxB = jaxB;
		this.domWriter = domWriter;
	}
	
	public String parseResenje() throws Exception {
		Document document = domParser.buildDocumentFromFile("./../resenje.xml");
		Resenje resenje = domParser.parseResenje(document);
		return domParser.getDocumentAsString(document);
	}
	
	public void createResenje(Resenje r) throws Exception {
		domWriter.generateResenje(r);
	}

}
