package com.xml.projekat.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.ZalbaOdluke;
import com.xml.projekat.repository.ZalbaOdlukeRepository;

@Service
public class ZalbaOdlukaService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;

	@Autowired
	private ZalbaOdlukeRepository zalbaOdlukeRepository;
	
	public ZalbaOdlukaService(DOMParser domParser, DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
	}

	public ZalbaOdluke parseZalbaOdluke(RetrieveDTO dto) throws Exception {
		Document document = zalbaOdlukeRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../zalba_odluke.xml");
		ZalbaOdluke zalba= domParser.parseZalbaOdluke(document);
		//return domParser.getDocumentAsString(document);
		return zalba;
	}
	
	public void createZalbaOdluke(ZalbaOdluke zo) throws Exception {
		String documentContent = domWriter.generateZalbaOdluke(zo);
		String naziv = (zalbaOdlukeRepository.getSize()+1) + ".xml";
		zalbaOdlukeRepository.save(documentContent, naziv);
	}
}
