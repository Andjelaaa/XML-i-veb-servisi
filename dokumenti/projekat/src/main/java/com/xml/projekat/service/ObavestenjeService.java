package com.xml.projekat.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.model.Obavestenje;

@Service
public class ObavestenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	public ObavestenjeService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public String parseObavestenje() throws Exception {
		Document document = domParser.buildDocumentFromFile("C:\\Users\\teodo\\Desktop\\XML-i-veb-servisi\\dokumenti\\obavestenje.xml");
		Obavestenje obavestenje = domParser.parseObavestenje(document);
		return domParser.getDocumentAsString(document);
	}

	public void makeObavestenje(Obavestenje obavestenje) {
		domWriter.generateDOMObavestenje(obavestenje);
	}
	
}
