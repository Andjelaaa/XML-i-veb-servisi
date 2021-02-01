package com.xml.projekat.service;


import java.io.ByteArrayOutputStream;
import java.io.StringWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dom.XSLTransformer;
import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.repository.ZahtevRepository;

@Service
public class ZahtevService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/zahtev.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/zahtevHTML.xsl";

	@Autowired
	private ZahtevRepository zahtevRepository;
	
	@Autowired
	private XSLTransformer xslTransformer;
	
	
	
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

	public Resource getPdf(String id) throws Exception {
		String document = zahtevRepository.findZahtev(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = zahtevRepository.findZahtev(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}

}
