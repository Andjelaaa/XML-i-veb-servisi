package com.xml.projekat.service;


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

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dom.XSLTransformer;
import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.repository.ObavestenjeRepository;
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
	private ObavestenjeRepository obavestenjeRepository;
	
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
	
	public ArrayList<ZahtevDTO> findRequestsByUser(String username) throws XMLDBException {
		String xPathExpression = "/";
		ResourceSet result = zahtevRepository.findZahtevi(xPathExpression);		
		
		ArrayList<ZahtevDTO> zahteviList = extractDataFromRequests(result);
		ArrayList<ZahtevDTO> filtriranaListaZahteva = new ArrayList<ZahtevDTO>();
		for (ZahtevDTO zahtevDTO : zahteviList) {
			if(zahtevDTO.getPodnosilac().getKorisnickoIme().equals(username)) {
				filtriranaListaZahteva.add(zahtevDTO);
			}
				
		}
		return filtriranaListaZahteva;
	}

	
	private ArrayList<ZahtevDTO> extractDataFromRequests(ResourceSet resourceSet) {
		ArrayList<ZahtevDTO> zahteviList = new ArrayList<ZahtevDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				Zahtev zahtev = domParser.parseZahtev(document);
				
				zahteviList.add(new ZahtevDTO(zahtev));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zahteviList;
	}
	
	private ArrayList<ObavestenjeDTO> extractDataFromInformations(ResourceSet resourceSet) {
		ArrayList<ObavestenjeDTO> obavestenjeList = new ArrayList<ObavestenjeDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				Obavestenje o = domParser.parseObavestenje(document);
				
				obavestenjeList.add(new ObavestenjeDTO(o));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return obavestenjeList;
	}

	public List<ZahtevDTO> findAllRequests() {
		String xPathExpression = "/";
		ResourceSet result = zahtevRepository.findZahtevi(xPathExpression);		
		
		ArrayList<ZahtevDTO> zahteviList = extractDataFromRequests(result);	
		return zahteviList;
	}
	
	public List<ZahtevDTO> findNewRequests() {
		String xPathExpression = "/";
		ResourceSet result = zahtevRepository.findZahtevi(xPathExpression);	
		ArrayList<ZahtevDTO> zahteviList = extractDataFromRequests(result);	
		
		ResourceSet result2 = obavestenjeRepository.findObavestenja(xPathExpression);	
		ArrayList<ObavestenjeDTO> obavestenjaList = extractDataFromInformations(result2);	
		
		ArrayList<ZahtevDTO> filtriranaListaZahteva = new ArrayList<ZahtevDTO>();
		
		
		
		for (ZahtevDTO zahtevDTO : zahteviList) {
			boolean found = false;
			for(ObavestenjeDTO obDTO: obavestenjaList) {
				if(zahtevDTO.getURI().equals(obDTO.getZahtevURI())) {
					found = true;
					break;
				}
			}
			if(!found) {
				filtriranaListaZahteva.add(zahtevDTO);
			}
		
		}
		return filtriranaListaZahteva;
	}

}
