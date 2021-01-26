package com.xml.poverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.repository.ResenjeRepository;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	@Autowired
	private ResenjeRepository resenjeRepository;
	
	public ResenjeService(DOMParser domParser, DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
	}
	
	public Resenje parseResenje(RetrieveDTO dto) throws Exception {
		Document document = resenjeRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../resenje.xml");
		Resenje resenje = domParser.parseResenje(document);
		//return domParser.getDocumentAsString(document);
		return resenje;
	}
	
	public void createResenje(Resenje r) throws Exception {
		String documentContent = domWriter.generateResenje(r);
		String naziv = (resenjeRepository.getSize()+1) + ".xml";
		resenjeRepository.save(documentContent, naziv);
	}

}
