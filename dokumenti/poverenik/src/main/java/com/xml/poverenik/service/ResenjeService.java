package com.xml.poverenik.service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.ResenjeDTO;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.ZalbaCutanjeDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.repository.ResenjeRepository;
import com.xml.poverenik.dom.XSLTransformer;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/resenje.xsl";	
	private static String xslPathHTML = "src/main/resources/podaci/xsl/resenjeHTML.xsl";

	
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
	

	public List<ResenjeDTO> findDecisionByUser(String username) {
		String xPathExpression = "/";
		ResourceSet result = resenjeRepository.findResenja(xPathExpression);		
		
		ArrayList<ResenjeDTO> resenjaList = extractDataFromRequests(result);
		ArrayList<ResenjeDTO> filtriranaListaZalbi = new ArrayList<ResenjeDTO>();
		for (ResenjeDTO resenjeDTO : resenjaList) {
			if(resenjeDTO.getKorisnickoIme().equals(username)) {
				filtriranaListaZalbi.add(resenjeDTO);
			}
				
		}
		return filtriranaListaZalbi;
	}

	public List<ResenjeDTO> findAllDecisions() {
		String xPathExpression = "/";
		ResourceSet result = resenjeRepository.findResenja(xPathExpression);		
		
		ArrayList<ResenjeDTO> resenjeList = extractDataFromRequests(result);	
		return resenjeList;
	}
}
