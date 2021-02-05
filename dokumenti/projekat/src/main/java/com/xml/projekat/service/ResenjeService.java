package com.xml.projekat.service;

import java.io.ByteArrayOutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.xmldb.api.base.XMLDBException;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.repository.ResenjeRepository;
import com.xml.projekat.repository.UserRepository;
import com.xml.projekat.ws.resenje.DokumentResenje;



@Service
public class ResenjeService {

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
	private com.xml.projekat.dom.XSLTransformer xslTransformer;
	
	public ResenjeService(DOMWriter domWriter) {
		this.domWriter = domWriter;
	}
	
	public void createResenje(DokumentResenje r) throws Exception {
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

}
