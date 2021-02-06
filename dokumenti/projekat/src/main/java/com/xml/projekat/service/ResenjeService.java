package com.xml.projekat.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.ResenjeDTO;
import com.xml.projekat.model.Resenje;
import com.xml.projekat.repository.ResenjeRepository;
import com.xml.projekat.ws.resenje.DokumentResenje;



@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/resenje.xsl";	
	private static String xslPathHTML = "src/main/resources/podaci/xsl/resenjeHTML.xsl";

	
	@Autowired
	private ResenjeRepository resenjeRepository;


	@Autowired
	private com.xml.projekat.dom.XSLTransformer xslTransformer;
	
	public ResenjeService(DOMParser domParser, DOMWriter domWriter) {
		this.domWriter = domWriter;
		this.domParser = domParser;
	}
	
	public void createResenje(DokumentResenje r) throws Exception {
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
	
	public byte[] getPdfAsByteArray(String id) throws Exception {
		String document = resenjeRepository.findResenje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);
		
		return outputStream.toByteArray();
	}
	
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = resenjeRepository.findResenje(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}
	
	private ArrayList<ResenjeDTO> extractDataFromRequests(ResourceSet resourceSet) {
		ArrayList<ResenjeDTO> resenjeList = new ArrayList<ResenjeDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				Resenje resenje = domParser.parseResenje(document);
				
				resenjeList.add(new ResenjeDTO(resenje));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resenjeList;
	}
	public List<ResenjeDTO> findAllDecisions() {
		String xPathExpression = "/";
		ResourceSet result = resenjeRepository.findResenja(xPathExpression);		
		
		ArrayList<ResenjeDTO> resenjeList = extractDataFromRequests(result);	
		return resenjeList;
	}
	
	
	public List<ResenjeDTO> findDecisionsByContent(String search) throws SAXException, IOException, ParserConfigurationException, ParseException {
		String xPathExpression = "/";
		ResourceSet result = resenjeRepository.findResenja(xPathExpression);	
		ResourceIterator i;
		ArrayList<String> resenjaList = new ArrayList<String>();
		try {
			i = result.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				resenjaList.add(resource.getContent().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ArrayList<ResenjeDTO> filtriranaListaZahteva = new ArrayList<ResenjeDTO>();
		for (String rDTO : resenjaList) {
			if(rDTO.toLowerCase().contains(search.toLowerCase())) {
				Document document = domParser.buildDocumentFromText(rDTO);
				Resenje resenje = domParser.parseResenje(document);				
				filtriranaListaZahteva.add(new ResenjeDTO(resenje));
			}
				
		}
		return filtriranaListaZahteva;
	}

}
