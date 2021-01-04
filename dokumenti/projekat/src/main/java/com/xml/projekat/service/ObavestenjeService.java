package com.xml.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.repository.ObavestenjeRepository;

@Service
public class ObavestenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	public ObavestenjeService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public Obavestenje parseObavestenje(RetrieveDTO dto) throws Exception {
		Document document = obavestenjeRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../obavestenje.xml");
		Obavestenje obavestenje = domParser.parseObavestenje(document);
		//return domParser.getDocumentAsString(document);
		return obavestenje;
	}

	public void makeObavestenje(Obavestenje obavestenje) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		String documentContent = domWriter.generateDOMObavestenje(obavestenje);
		obavestenjeRepository.save(documentContent, obavestenje.getBrojPredmeta()+".xml");
	}
	
}
