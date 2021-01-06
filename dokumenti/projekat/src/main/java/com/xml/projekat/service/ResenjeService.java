package com.xml.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.repository.ResenjeRepository;

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
