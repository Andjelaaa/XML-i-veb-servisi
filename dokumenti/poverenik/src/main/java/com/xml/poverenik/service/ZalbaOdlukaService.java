package com.xml.poverenik.service;

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

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dom.XSLTransformer;
import com.xml.poverenik.dto.ResenjeDTO;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.SearchDTO;
import com.xml.poverenik.dto.ZalbaOdlukaDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.rdf.FusekiReader;
import com.xml.poverenik.repository.ResenjeRepository;
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
	@Autowired
	private ResenjeRepository resenjeRepository;
	
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
	
	public List<ZalbaOdlukaDTO> findNewAppeal() {
		String xPathExpression = "/";
		ResourceSet result = zalbaOdlukeRepository.findZalbe(xPathExpression);	
		ArrayList<ZalbaOdlukaDTO> zalbeList = extractDataFromRequests(result);	
		
		ResourceSet result2 = resenjeRepository.findResenja(xPathExpression);	
		ArrayList<ResenjeDTO> resenjaList = extractDataFromDecisions(result2);	
		
		ArrayList<ZalbaOdlukaDTO> filtriranaListaZalbi = new ArrayList<ZalbaOdlukaDTO>();
		
		
		
		for (ZalbaOdlukaDTO zalbaDTO : zalbeList) {
			boolean found = false;
			for(ResenjeDTO rDTO: resenjaList) {
				if(rDTO.getZalbaOdlukeURI() != null) {
					if(zalbaDTO.getURI().equals(rDTO.getZalbaOdlukeURI())) {
						found = true;
						break;
					}				
				}				
			}
			if(!found) {
				filtriranaListaZalbi.add(zalbaDTO);
			}
		
		}
		return filtriranaListaZalbi;
	}
	
	private ArrayList<ResenjeDTO> extractDataFromDecisions(ResourceSet resourceSet) {
		ArrayList<ResenjeDTO> resenjaList = new ArrayList<ResenjeDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				Resenje resenje = domParser.parseResenje(document);
				
				resenjaList.add(new ResenjeDTO(resenje));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return resenjaList;
	}
	
	public List<ZalbaOdlukaDTO> findAppealsByContent(String search) throws SAXException, IOException, ParserConfigurationException {
		String xPathExpression = "/";
		ResourceSet result = zalbaOdlukeRepository.findZalbe(xPathExpression);	
		ResourceIterator i;
		ArrayList<String> zalbeList = new ArrayList<String>();
		try {
			i = result.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				zalbeList.add(resource.getContent().toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		ArrayList<ZalbaOdlukaDTO> filtriranaListaZahteva = new ArrayList<ZalbaOdlukaDTO>();
		for (String zalbaDTO : zalbeList) {
			if(zalbaDTO.toLowerCase().contains(search.toLowerCase())) {
				Document document = domParser.buildDocumentFromText(zalbaDTO);
				ZalbaOdluke zalba = domParser.parseZalbaOdluke(document);				
				filtriranaListaZahteva.add(new ZalbaOdlukaDTO(zalba));
			}
				
		}
		return filtriranaListaZahteva;
	}
	
	public ArrayList<ZalbaOdlukaDTO> searhByMetadata(SearchDTO dto) throws IOException {
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
		if(dto.getNaziv_poverenika() == null) {
			params.put("naziv_poverenika", "");
		}else {
			params.put("naziv_poverenika", dto.getNaziv_poverenika());
		}
		if(dto.getZahtev_uri() == null) {
			params.put("zahtev_uri", "");
		}else {
			params.put("zahtev_uri", dto.getZahtev_uri());
		}

		ArrayList<String> URIs = FusekiReader.executeQuery(params, "/zalbeOdluke", "src/main/resources/podaci/rdf/query.rq");
		ArrayList<ZalbaOdlukaDTO> zahteviList = new ArrayList<ZalbaOdlukaDTO>();
		ArrayList<ZalbaOdlukaDTO> filtriranaList = new ArrayList<ZalbaOdlukaDTO>();
		if (URIs.size() != 0) {
			String xPathExpression = "/";
			ResourceSet result = zalbaOdlukeRepository.findZalbe(xPathExpression);
			zahteviList = extractDataFromRequests(result);
			
			for (ZalbaOdlukaDTO zalbaDTO : zahteviList) {
				if(URIs.contains(zalbaDTO.getURI())){
					filtriranaList.add(zalbaDTO);
				}
			}			
		}
		return filtriranaList;
	}

}
