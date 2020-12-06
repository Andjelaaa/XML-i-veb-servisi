package com.xml.projekat.service;


import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.Zahtev;

@Service
public class ZahtevService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	private final JaxB jaxB;

	public ZahtevService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		this.jaxB = jaxB;
	}

	public String parseZahtev() throws Exception {
		Document document = domParser.buildDocumentFromFile("./../zahtev.xml");
		Zahtev zahtev= domParser.parseZahtev(document);

		return domParser.getDocumentAsString(document);
	}
	
	public void createZahtev(Zahtev z) throws Exception {
		domWriter.generateZahtev(z);
	}

}
