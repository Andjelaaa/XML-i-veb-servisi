package com.xml.poverenik.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.model.Zahtev;
import com.xml.poverenik.repository.ZahtevRepository;

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
		String documentContent = domWriter.generateZahtev(z);
		String naziv = (zahtevRepository.getSize()+1) + ".xml";
		zahtevRepository.save(documentContent, naziv);
	}

}
