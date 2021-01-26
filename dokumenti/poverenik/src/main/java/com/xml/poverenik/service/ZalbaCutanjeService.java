package com.xml.poverenik.service;

import java.io.IOException;

import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.jaxb.JaxB;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.repository.ZalbaCutanjeRepository;

@Service
public class ZalbaCutanjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;	

	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	public ZalbaCutanjeService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		super();
		this.domParser = domParser;
		this.domWriter = domWriter;
	}
	
	public ZalbaCutanje parseZalbaCutanje(RetrieveDTO dto) throws Exception {
		Document document = zalbaCutanjeRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../zalba_cutanje.xml");
		ZalbaCutanje zalbaCutanje= domParser.parseZalbaCutanje(document);
		//return domParser.getDocumentAsString(document);
		return zalbaCutanje;
	}
	
	public void makeZalbaCutanje(ZalbaCutanje zc) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, TransformerException, IOException {
		String documentContent = domWriter.generateZalbaCutanje(zc);
		String naziv = (zalbaCutanjeRepository.getSize()+1) + ".xml";
		zalbaCutanjeRepository.save(documentContent,naziv);
	}
	
}
