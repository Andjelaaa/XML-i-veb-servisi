package com.xml.poverenik.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
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

import com.xml.poverenik.dom.DOMParser;
import com.xml.poverenik.dom.DOMWriter;
import com.xml.poverenik.dom.XSLTransformer;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.repository.IzvestajRepository;
import com.xml.poverenik.ws.izvestaj.Message;

@Service
public class IzvestajService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	
	private static String xslFOPath = "src/main/resources/podaci/xsl/izvestaj.xsl";
	private static String xslPathHTML = "src/main/resources/podaci/xsl/izvestajHTML.xsl";
	
	@Autowired
	private IzvestajRepository izvestajRepository;
		
	@Autowired
	private XSLTransformer xslTransformer;
	
	public IzvestajService(DOMParser domParser,DOMWriter domWriter) {
		this.domParser = domParser;
		this.domWriter = domWriter;
		
	}

	public Message parseIzvestaj(RetrieveDTO dto) throws Exception {
		Document document = izvestajRepository.find(dto.getXpath());
		Message izvestaj = domParser.parseIzvestaj(document);
		return izvestaj;
	}

	public Message makeIzvestaj(Message m) throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, TransformerException, IOException {
		
		try {
			String documentContent = domWriter.generateDOMIzvestaj(m);
			String naziv = (izvestajRepository.getSize()+1) + ".xml";
			izvestajRepository.save(documentContent, naziv);
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return m;
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
	
//	public List<IzvestajDTO> findAllReports(){
//		String xPathExpression = "/";
//		ResourceSet result = izvestajRepository.findIzvestaje(xPathExpression);	
//		ArrayList<IzvestajDTO> obavestenjaList = extractDataFromInformations(result);
//		return obavestenjaList;
//		
//	}
//	
//
//	private ArrayList<IzvestajDTO> extractDataFromInformations(ResourceSet resourceSet) {
//		ArrayList<IzvestajDTO> izvestajiList = new ArrayList<IzvestajDTO>();
//		ResourceIterator i;
//		try {
//			i = resourceSet.getIterator();
//			while (i.hasMoreResources()) {
//				XMLResource resource = (XMLResource) i.nextResource();
//				
//				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
//				Izvestaj o = domParser.parseIzvestaj(document);
//				
//				izvestajiList.add(new IzvestajDTO(o));
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return izvestajiList;
//	}
}
