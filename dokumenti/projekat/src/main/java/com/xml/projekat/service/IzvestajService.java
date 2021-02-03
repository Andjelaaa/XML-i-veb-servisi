package com.xml.projekat.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.xml.transform.TransformerException;

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
import com.xml.projekat.dto.IzvestajDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.model.Izvestaj;
import com.xml.projekat.repository.IzvestajRepository;
import com.xml.projekat.repository.ZahtevRepository;

@Service
public class IzvestajService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/izvestaj.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/izvestajHTML.xsl";
	
	@Autowired
	private IzvestajRepository izvestajRepository;
	
	@Autowired
	private ZahtevService zahtevService;
	
	@Autowired
	private XSLTransformer xslTransformer;
	
	public IzvestajService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public Izvestaj parseIzvestaj(RetrieveDTO dto) throws Exception {
		Document document = izvestajRepository.find(dto.getXpath());
		Izvestaj izvestaj = domParser.parseIzvestaj(document);
		return izvestaj;
	}

	public Izvestaj makeIzvestaj() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, TransformerException, IOException {
		
		Izvestaj izvestaj = new Izvestaj();
		List<ZahtevDTO> zahteviList = zahtevService.findAllRequests();
		int brojac = 0;
		
		String godina = Calendar.getInstance().get(Calendar.YEAR) + "";
		
		for(ZahtevDTO z: zahteviList) {
			if(z.getDatum().contains(godina))
				brojac++;
		}
		izvestaj.setBrPodnetihZahteva(brojac+"");
		brojac = 0;
		zahteviList = zahtevService.findNewRequests();
		for(ZahtevDTO z: zahteviList) {
			if(z.getDatum().contains(godina))
				brojac++;
		}
		izvestaj.setBrOdbijenihZahteva(brojac+"");
		izvestaj.setGodina(godina);
		izvestaj.setBrZalbi("20");
		
		try {
			String documentContent = domWriter.generateDOMIzvestaj(izvestaj);
			String naziv = (izvestajRepository.getSize()+1) + ".xml";
			izvestajRepository.save(documentContent, naziv);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		
		
		return izvestaj;
	}
	
	public Resource getPdf(String id) throws Exception {
		String document = izvestajRepository.findIzvestaj(id);
		ByteArrayOutputStream outputStream = xslTransformer.generatePDf(document, xslFOPath);

		Path file = Paths.get(id + ".pdf");
		Files.write(file, outputStream.toByteArray());

		return new UrlResource(file.toUri());
	}
	public String convertXMLtoHTML(String id) throws XMLDBException {
		String xml =izvestajRepository.findIzvestaj(id);
		return xslTransformer.convertXMLtoHTML(xslPathHTML, xml);
	}
	
	public List<IzvestajDTO> findAllReports(){
		String xPathExpression = "/";
		ResourceSet result = izvestajRepository.findIzvestaje(xPathExpression);	
		ArrayList<IzvestajDTO> obavestenjaList = extractDataFromInformations(result);
		return obavestenjaList;
		
	}
	

	private ArrayList<IzvestajDTO> extractDataFromInformations(ResourceSet resourceSet) {
		ArrayList<IzvestajDTO> izvestajiList = new ArrayList<IzvestajDTO>();
		ResourceIterator i;
		try {
			i = resourceSet.getIterator();
			while (i.hasMoreResources()) {
				XMLResource resource = (XMLResource) i.nextResource();
				
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				Izvestaj o = domParser.parseIzvestaj(document);
				
				izvestajiList.add(new IzvestajDTO(o));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return izvestajiList;
	}
}
