package com.xml.projekat.service;

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

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dom.XSLTransformer;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.repository.ObavestenjeRepository;

@Service
public class ObavestenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/obavestenje.xsl";
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	@Autowired
	private XSLTransformer xslTransformer;
	
	public ObavestenjeService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public Obavestenje parseObavestenje(RetrieveDTO dto) throws Exception {
		Document document = obavestenjeRepository.find(dto.getXpath());
		Obavestenje obavestenje = domParser.parseObavestenje(document);
		return obavestenje;
	}

	public void makeObavestenje(Obavestenje obavestenje) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, TransformerException, IOException {
		String documentContent = domWriter.generateDOMObavestenje(obavestenje);
		String naziv = (obavestenjeRepository.getSize()+1) + ".xml";
		obavestenjeRepository.save(documentContent, naziv);
	}
	
	public Resource getPdf(String id) throws Exception {
		String document = obavestenjeRepository.findObavestenje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	
}
