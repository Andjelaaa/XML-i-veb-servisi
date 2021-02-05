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

import javax.xml.transform.TransformerException;

import org.quartz.plugins.history.LoggingTriggerHistoryPlugin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.data.types.Message;
import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dom.XSLTransformer;
import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.rdf.FusekiReader;
import com.xml.projekat.repository.ObavestenjeRepository;
import com.xml.projekat.repository.UserRepository;

@Service
public class ObavestenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/obavestenje.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/obavestenjeHTML.xsl";
	
	@Autowired
	private ObavestenjeRepository obavestenjeRepository;
	
	@Autowired 
	private EmailService emailService;
//	@Autowired User
	@Autowired
	private XSLTransformer xslTransformer;
	
	@Autowired
	private UserRepository userRepository;
	
	
	public ObavestenjeService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public Obavestenje parseObavestenje(RetrieveDTO dto) throws Exception {
		Document document = obavestenjeRepository.find(dto.getXpath());
		Obavestenje obavestenje = domParser.parseObavestenje(document);
		return obavestenje;
	}

	public void makeObavestenje(Obavestenje obavestenje) throws Exception {
		String documentContent = domWriter.generateDOMObavestenje(obavestenje);
		String naziv = (obavestenjeRepository.getSize()+1) + ".xml";
		obavestenjeRepository.save(documentContent, naziv);
		Message message = new Message();
		String username = obavestenje.getPodnosilac().getKorisnickoIme();
		String email = userRepository.findOneByUsername(username).getEmail();
		String naslov = "Obavestenje za poslati zahtev za informacijama od javnog znacaja";
		String sadrzaj = "Za pregled obavestenja udjite na link: "+ "http://localhost:4200/obavestenje/" + obavestenjeRepository.getSize();
		byte[] prilog = getPdfAsByteArray(obavestenjeRepository.getSize()+"");
		message.setPrimalac(email);
		message.setNaslov(naslov);
		message.setSadrzaj(sadrzaj);
		message.setPrilog(prilog);
		message.setTipPriloga("pdf");
		emailService.posaljiMejl(message);
	}
	
	public Resource getPdf(String id) throws Exception {
		String document = obavestenjeRepository.findObavestenje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	
	public byte[] getPdfAsByteArray(String id) throws Exception {
		String document = obavestenjeRepository.findObavestenje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);
		
		return outputStream.toByteArray();
	}
	
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = obavestenjeRepository.findObavestenje(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}
	
	public List<ObavestenjeDTO> findAllInformations(){
		String xPathExpression = "/";
		ResourceSet result = obavestenjeRepository.findObavestenja(xPathExpression);	
		ArrayList<ObavestenjeDTO> obavestenjaList = extractDataFromInformations(result);
		return obavestenjaList;
		
	}
	
	public List<ObavestenjeDTO> findInformationsByUser(String username){
		String xPathExpression = "/";
		ResourceSet result = obavestenjeRepository.findObavestenja(xPathExpression);	
		ArrayList<ObavestenjeDTO> obavestenjaList = extractDataFromInformations(result);
		ArrayList<ObavestenjeDTO> filtriranaListaZahteva = new ArrayList<ObavestenjeDTO>();
		for (ObavestenjeDTO obavestenjeDTO : obavestenjaList) {
			if(obavestenjeDTO.getPodnosilac().getKorisnickoIme().equals(username)) {
				filtriranaListaZahteva.add(obavestenjeDTO);
			}
				
		}
		return filtriranaListaZahteva;
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

	public Resource findRdf(String uri) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("URI", uri);
		
		FusekiReader.findRDF(params, "/obavestenja", "src/main/resources/podaci/rdf/queryOneInfo.rq");
		Path file = Paths.get("src/main/resources/podaci/rdf/metadataRDF.xml");

		return new UrlResource(file.toUri());
	}

	public Resource findJsonMetadata(String uri) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("URI", uri);
		
		FusekiReader.findJsonMetadata(params, "/obavestenja", "src/main/resources/podaci/rdf/queryOneInfo.rq");
		Path file = Paths.get("src/main/resources/podaci/rdf/metadataJSON.json");

		return new UrlResource(file.toUri());
	}
	
}
