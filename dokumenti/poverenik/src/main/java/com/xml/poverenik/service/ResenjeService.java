package com.xml.poverenik.service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.repository.ResenjeRepository;
import com.xml.poverenik.dom.XSLTransformer;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/resenje.xsl";
	
	@Autowired
	private ResenjeRepository resenjeRepository;
	
	@Autowired
	private com.xml.poverenik.dom.XSLTransformer xslTransformer;
	
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

	public Resource getPdf(String id) throws Exception {
		String document = resenjeRepository.findResenje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
}
