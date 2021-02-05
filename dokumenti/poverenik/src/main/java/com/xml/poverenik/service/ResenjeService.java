package com.xml.poverenik.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
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

import com.xml.poverenik.data.types.Message;
import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dto.ResenjeDTO;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.SearchResenjeDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.rdf.FusekiReader;
import com.xml.poverenik.repository.ResenjeRepository;
import com.xml.poverenik.repository.UserRepository;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/resenje.xsl";	
	private static String xslPathHTML = "src/main/resources/podaci/xsl/resenjeHTML.xsl";

	
	@Autowired
	private ResenjeRepository resenjeRepository;

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EmailService emailService;

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
		Message message = new Message();
		String username = r.getKorisnickoIme();
		String email = userRepository.findOneByUsername(username).getEmail();
		String naslov = "Odluka o postupku po zalbi";
		String sadrzaj = "Za pregled resenja udjite na link: "+ "http://localhost:4201/decision_review/" + resenjeRepository.getSize();
		byte[] prilog = getPdfAsByteArray(resenjeRepository.getSize()+"");
		message.setPrimalac(email);
		message.setNaslov(naslov);
		message.setSadrzaj(sadrzaj);
		message.setPrilog(prilog);
		message.setTipPriloga("pdf");
		emailService.posaljiMejl(message);
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
	
	public ArrayList<ResenjeDTO> searhByMetadata(SearchResenjeDTO dto) throws IOException {
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
		if(dto.getZalba_cutanje_uri() == null) {
			params.put("zalba_cutanje_uri", "");
		}else {
			params.put("zalba_cutanje_uri", dto.getZalba_cutanje_uri());
		}
		if(dto.getZalba_odluke_uri() == null) {
			params.put("zalba_odluke_uri", "");
		}else {
			params.put("zalba_odluke_uri", dto.getZalba_odluke_uri());
		}
		if(dto.getBrojResenja() == null) {
			params.put("brojResenja", "");
		}else {
			params.put("brojResenja", dto.getBrojResenja());
		}

		ArrayList<String> URIs = FusekiReader.executeQuery(params, "/resenja", "src/main/resources/podaci/rdf/queryResenje.rq");
		ArrayList<ResenjeDTO> zahteviList = new ArrayList<ResenjeDTO>();
		ArrayList<ResenjeDTO> filtriranaList = new ArrayList<ResenjeDTO>();
		if (URIs.size() != 0) {
			String xPathExpression = "/";
			ResourceSet result = resenjeRepository.findResenja(xPathExpression);
			zahteviList = extractDataFromRequests(result);
			
			for (ResenjeDTO rDTO : zahteviList) {
				if(URIs.contains(rDTO.getURI())){
					filtriranaList.add(rDTO);
				}
			}			
		}
		return filtriranaList;
	}
}
