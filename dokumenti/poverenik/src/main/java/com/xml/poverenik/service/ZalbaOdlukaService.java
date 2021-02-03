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
import com.xml.poverenik.dom.XSLTransformer;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.ZalbaCutanjeDTO;
import com.xml.poverenik.dto.ZalbaOdlukaDTO;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.repository.ZalbaOdlukeRepository;

@Service
public class ZalbaOdlukaService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;

	private static String xslFOPath = "src/main/resources/podaci/xsl/zalba_odluke.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/zalba_odlukeHTML.xsl";


	
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
	
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = zalbaOdlukeRepository.findZalbaOdluke(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}

	public List<ZalbaOdlukaDTO> findAppealsByUser(String username) {
		String xPathExpression = "/";
		ResourceSet result = zalbaOdlukeRepository.findZalbe(xPathExpression);		
		
		ArrayList<ZalbaOdlukaDTO> zalbeList = extractDataFromRequests(result);
		ArrayList<ZalbaOdlukaDTO> filtriranaListaZalbi = new ArrayList<ZalbaOdlukaDTO>();
		for (ZalbaOdlukaDTO zalbaDTO : zalbeList) {
			if(zalbaDTO.getNazivPodnosioca().getKorisnickoIme().equals(username)) {
				filtriranaListaZalbi.add(zalbaDTO);
			}
				
		}
		return filtriranaListaZalbi;	
	}
	private ArrayList<ZalbaOdlukaDTO> extractDataFromRequests(ResourceSet resourceSet) {
		ArrayList<ZalbaOdlukaDTO> zalbeList = new ArrayList<ZalbaOdlukaDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				ZalbaOdluke zalba = domParser.parseZalbaOdluke(document);
				
				zalbeList.add(new ZalbaOdlukaDTO(zalba));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zalbeList;
	}

	public List<ZalbaOdlukaDTO> findAllAppeal() {
		
		String xPathExpression = "/";
		ResourceSet result = zalbaOdlukeRepository.findZalbe(xPathExpression);		
		
		ArrayList<ZalbaOdlukaDTO> zalbeList = extractDataFromRequests(result);	
		return zalbeList;
	}

}
