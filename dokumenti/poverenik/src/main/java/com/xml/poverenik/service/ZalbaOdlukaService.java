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
import com.xml.poverenik.dom.XSLTransformer;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.repository.ZalbaOdlukeRepository;

@Service
public class ZalbaOdlukaService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;

	private static String xslFOPath = "src/main/resources/podaci/xsl/zalba_odluke.xsl";

	
	@Autowired
	private XSLTransformer xslTransformer;
	
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
	
	public Resource getPdf(String id) throws Exception {
		String document = zalbaOdlukeRepository.findZalbaOdluke(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
}
