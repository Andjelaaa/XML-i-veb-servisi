package com.xml.projekat.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.repository.ZahtevRepository;

@Service
public class ZahtevService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	public ZahtevService(DOMParser domParser, DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
	}

	public Zahtev parseZahtev(RetrieveDTO dto) throws Exception {
		Document document = zahtevRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../zahtev.xml");
		Zahtev zahtev= domParser.parseZahtev(document);
		//return domParser.getDocumentAsString(document);
		return zahtev;
	}
	
	public void createZahtev(Zahtev z) throws Exception {
		domWriter.generateZahtev(z);
	}

}
