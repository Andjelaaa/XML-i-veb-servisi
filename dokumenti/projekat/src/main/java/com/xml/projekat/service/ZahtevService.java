package com.xml.projekat.service;


import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.xml.projekat.dom.XSLTransformer;
import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.SearchDTO;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.model.TUser;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.rdf.FusekiReader;
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

	public List<ZahtevDTO> findRequestsByContent(String search) throws SAXException, IOException, ParserConfigurationException {
		String xPathExpression = "/";
		ResourceSet result = zahtevRepository.findZahtevi(xPathExpression);		
		ResourceIterator i;
		ArrayList<String> zahteviList = new ArrayList<String>();
		try {
			i = result.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				zahteviList.add(resource.getContent().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ArrayList<ZahtevDTO> filtriranaListaZahteva = new ArrayList<ZahtevDTO>();
		for (String zahtevDTO : zahteviList) {
			if(zahtevDTO.toLowerCase().contains(search.toLowerCase())) {
				Document document = domParser.buildDocumentFromText(zahtevDTO);
				Zahtev zahtev = domParser.parseZahtev(document);				
				filtriranaListaZahteva.add(new ZahtevDTO(zahtev));
			}
				
		}
		return filtriranaListaZahteva;
	}
	
	public ArrayList<ZahtevDTO> searhByMetadata(SearchDTO dto) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		if(dto.getURI() == null) {
			params.put("URI", "");
		}else {
			params.put("URI", dto.getURI());
		}
		if(dto.getDatum() == null) {
			params.put("datum", "");
		}else {
			params.put("datum", dto.getDatum());
		}
		if(dto.getKorisnicko_ime() == null) {
			params.put("korisnicko_ime", "");
		}else {
			params.put("korisnicko_ime", dto.getKorisnicko_ime());
		}
		if(dto.getNaziv_organa_vlasti() == null) {
			params.put("naziv_organa_vlasti", "");
		}else {
			params.put("naziv_organa_vlasti", dto.getNaziv_organa_vlasti());
		}

		ArrayList<String> URIs = FusekiReader.executeQuery(params, "/zahtevi");
		ArrayList<ZahtevDTO> zahteviList = new ArrayList<ZahtevDTO>();
		ArrayList<ZahtevDTO> filtriranaList = new ArrayList<ZahtevDTO>();
		if (URIs.size() != 0) {
			String xPathExpression = "/";
			ResourceSet result = zahtevRepository.findZahtevi(xPathExpression);
			zahteviList = extractDataFromRequests(result);
			
			for (ZahtevDTO zahtevDTO : zahteviList) {
				if(URIs.contains(zahtevDTO.getURI())){
					filtriranaList.add(zahtevDTO);
				}
			}			
		}
		return filtriranaList;
	}

}
