package com.xml.poverenik.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.TransformerException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
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
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/zalba_cutanje.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/zalba_cutanjeHTML.xsl";


	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	
	
	@Autowired
	private com.xml.poverenik.dom.XSLTransformer xslTransformer;
	
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
	
	public Resource getPdf(String id) throws Exception {
		String document = zalbaCutanjeRepository.findZalbaCutanje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = zalbaCutanjeRepository.findZalbaCutanje(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}
}
