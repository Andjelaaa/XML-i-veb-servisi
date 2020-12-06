package com.xml.projekat.service;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.ZalbaOdluke;

@Service
public class ZalbaOdlukaService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	private final JaxB jaxB;

	public ZalbaOdlukaService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		this.domParser = domParser;
		this.jaxB = jaxB;
		this.domWriter = domWriter;
	}

	public String parseZalbaOdluke() throws Exception {
		Document document = domParser.buildDocumentFromFile("./../zalba_odluke.xml");
		ZalbaOdluke zalba= domParser.parseZalbaOdluke(document);

		return domParser.getDocumentAsString(document);
	}
	
	public void createZalbaOdluke(ZalbaOdluke zo) throws Exception {
		Document doc = domWriter.generateZalbaOdluke(zo);
	}
}
