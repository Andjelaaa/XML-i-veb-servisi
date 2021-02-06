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
import javax.xml.transform.TransformerException;

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
import com.xml.poverenik.dto.SearchDTO;
import com.xml.poverenik.dto.ZalbaCutanjeDTO;
import com.xml.poverenik.dto.ZalbaOdlukaDTO;
import com.xml.poverenik.jaxb.JaxB;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.rdf.FusekiReader;
import com.xml.poverenik.repository.ResenjeRepository;
import com.xml.poverenik.repository.ZalbaCutanjeRepository;


@Service
public class ZalbaCutanjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;	
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/zalba_cutanje.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/zalba_cutanjeHTML.xsl";


	@Autowired
	private ZalbaCutanjeRepository zalbaCutanjeRepository;
	@Autowired
	private ResenjeRepository resenjeRepository;
	@Autowired
	private EmailService emailService;
	
	
	@Autowired
	private com.xml.poverenik.dom.XSLTransformer xslTransformer;
	
	public ZalbaCutanjeService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		super();
		this.domParser = domParser;
		this.domWriter = domWriter;
	}
	
	public ZalbaCutanje parseZalbaCutanje(RetrieveDTO dto) throws Exception {
		Document document = zalbaCutanjeRepository.find(dto.getXpath());
		//Document document = domParser.buildDocumentFromFile("./../zalba_cutanje.xml");
		ZalbaCutanje zalbaCutanje= domParser.parseZalbaCutanje(document);
		//return domParser.getDocumentAsString(document);
		return zalbaCutanje;
	}
	public ZalbaCutanje getOneById(String id) throws IOException {
		Document document = zalbaCutanjeRepository.find(id);
		ZalbaCutanje zalbaCutanje= domParser.parseZalbaCutanje(document);
		return zalbaCutanje;
	}
	
	public void makeZalbaCutanje(ZalbaCutanje zc) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, TransformerException, IOException {
		String documentContent = domWriter.generateZalbaCutanje(zc);
		String naziv = (zalbaCutanjeRepository.getSize()+1) + ".xml";
		zalbaCutanjeRepository.save(documentContent,naziv);
	}
	
	public Resource getPdf(String id) throws Exception {
		String document = zalbaCutanjeRepository.findZalbaCutanje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	
	public void sendMail(String uri) throws Exception {
		
		Message message = new Message();
		String email = "organvlasti@gmail.com";
		String naslov = "Zalba na zahtev";
		String sadrzaj = "Pristigla je zalba na zahtev. Za pregled zalbe udjite na link: "+ "http://localhost:4201/appeal_silence_review/" + uri;
		byte[] prilog = getPdfAsByteArray(uri);
		message.setPrimalac(email);
		message.setNaslov(naslov);
		message.setSadrzaj(sadrzaj);
		message.setPrilog(prilog);
		message.setTipPriloga("pdf");
		emailService.posaljiMejl(message);
	}
	
	public byte[] getPdfAsByteArray(String id) throws Exception {
		String document = zalbaCutanjeRepository.findZalbaCutanje(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);
		
		return outputStream.toByteArray();
	}
	
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml = zalbaCutanjeRepository.findZalbaCutanje(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}
	private ArrayList<ZalbaCutanjeDTO> extractDataFromRequests(ResourceSet resourceSet) {
		ArrayList<ZalbaCutanjeDTO> zalbeList = new ArrayList<ZalbaCutanjeDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				ZalbaCutanje zalba = domParser.parseZalbaCutanje(document);
				
				zalbeList.add(new ZalbaCutanjeDTO(zalba));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return zalbeList;
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
	public List<ZalbaCutanjeDTO> findAppealsByUser(String username) {
		String xPathExpression = "/";
		ResourceSet result = zalbaCutanjeRepository.findZalbe(xPathExpression);		
		
		ArrayList<ZalbaCutanjeDTO> zalbeList = extractDataFromRequests(result);
		ArrayList<ZalbaCutanjeDTO> filtriranaListaZalbi = new ArrayList<ZalbaCutanjeDTO>();
		for (ZalbaCutanjeDTO zalbaDTO : zalbeList) {
			if(zalbaDTO.getPodnosilac().getKorisnickoIme().equals(username)) {
				filtriranaListaZalbi.add(zalbaDTO);
			}
				
		}
		return filtriranaListaZalbi;
	}

	public List<ZalbaCutanjeDTO> findAllAppeal() {
		String xPathExpression = "/";
		ResourceSet result = zalbaCutanjeRepository.findZalbe(xPathExpression);		
		
		ArrayList<ZalbaCutanjeDTO> zalbeList = extractDataFromRequests(result);	
		return zalbeList;
	}

	public List<ZalbaCutanjeDTO> findNewAppeal() {
		String xPathExpression = "/";
		ResourceSet result = zalbaCutanjeRepository.findZalbe(xPathExpression);	
		ArrayList<ZalbaCutanjeDTO> zalbeList = extractDataFromRequests(result);	
		
		ResourceSet result2 = resenjeRepository.findResenja(xPathExpression);	
		ArrayList<ResenjeDTO> resenjaList = extractDataFromDecisions(result2);	
		
		ArrayList<ZalbaCutanjeDTO> filtriranaListaZalbi = new ArrayList<ZalbaCutanjeDTO>();
		
		
		
		for (ZalbaCutanjeDTO zalbaDTO : zalbeList) {
			boolean found = false;
			for(ResenjeDTO rDTO: resenjaList) {
				if(rDTO.getZalbaCutanjeURI() != null) {
					if(zalbaDTO.getURI().equals(rDTO.getZalbaCutanjeURI())) {
						System.out.println("tu je zalba"+zalbaDTO.getURI());
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
	
	public List<ZalbaCutanjeDTO> findAppealsByContent(String search) throws SAXException, IOException, ParserConfigurationException {
		String xPathExpression = "/";
		ResourceSet result = zalbaCutanjeRepository.findZalbe(xPathExpression);	
		System.out.println(search +"LALLAL");
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
		
		
		ArrayList<ZalbaCutanjeDTO> filtriranaListaZahteva = new ArrayList<ZalbaCutanjeDTO>();
		for (String zalbaDTO : zalbeList) {
			if(zalbaDTO.toLowerCase().contains(search.toLowerCase())) {
				Document document = domParser.buildDocumentFromText(zalbaDTO);
				ZalbaCutanje zalba = domParser.parseZalbaCutanje(document);				
				filtriranaListaZahteva.add(new ZalbaCutanjeDTO(zalba));
			}
				
		}
		return filtriranaListaZahteva;
	}
	
	public ArrayList<ZalbaCutanjeDTO> searhByMetadata(SearchDTO dto) throws IOException {
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

		ArrayList<String> URIs = FusekiReader.executeQuery(params, "/zalbeCutanje", "src/main/resources/podaci/rdf/queryZalbaCutanje.rq");
		ArrayList<ZalbaCutanjeDTO> zahteviList = new ArrayList<ZalbaCutanjeDTO>();
		ArrayList<ZalbaCutanjeDTO> filtriranaList = new ArrayList<ZalbaCutanjeDTO>();
		if (URIs.size() != 0) {
			String xPathExpression = "/";
			ResourceSet result = zalbaCutanjeRepository.findZalbe(xPathExpression);
			zahteviList = extractDataFromRequests(result);
			
			for (ZalbaCutanjeDTO zalbaDTO : zahteviList) {
				if(URIs.contains(zalbaDTO.getURI())){
					filtriranaList.add(zalbaDTO);
				}
			}			
		}
		return filtriranaList;
	}

	public Resource findRdf(String uri) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("URI", uri);
		
		FusekiReader.findRDF(params, "/zalbeCutanje", "src/main/resources/podaci/rdf/queryOneASilence.rq");
		Path file = Paths.get("src/main/resources/podaci/rdf/metadataRDF.xml");

		return new UrlResource(file.toUri());
	}

	public Resource findJsonMetadata(String uri) throws IOException {
		Map<String, String> params = new HashMap<String, String>();
		params.put("URI", uri);
		
		FusekiReader.findJsonMetadata(params, "/zalbeCutanje", "src/main/resources/podaci/rdf/queryOneASilence.rq");
		Path file = Paths.get("src/main/resources/podaci/rdf/metadataJSON.json");

		return new UrlResource(file.toUri());
	}
}
