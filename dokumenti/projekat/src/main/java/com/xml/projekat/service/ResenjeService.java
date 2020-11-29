package com.xml.projekat.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.Resenje;

@Service
public class ResenjeService {
	private final DOMParser domParser;
	private final DOMWriter domWriter;
	private final JaxB jaxB;
	
	public ResenjeService(DOMParser domParser, JaxB jaxB, DOMWriter domWriter) {
		this.domParser = domParser;
		this.jaxB = jaxB;
		this.domWriter = domWriter;
	}
	
	public String parseResenje() throws Exception {
		Document document = domParser.buildDocumentFromFile("C:\\Users\\Admin\\Desktop\\VII semestar\\XML I VEB SERVISI\\XML-i-veb-servisi\\dokumenti\\resenje.xml");
		Resenje resenje = domParser.parseResenje(document);
		return domParser.getDocumentAsString(document);

	}
	
	public String createResenje() throws Exception {
		Document document = domParser.buildDocumentFromFile("C:\\Users\\Admin\\Desktop\\VII semestar\\XML I VEB SERVISI\\XML-i-veb-servisi\\dokumenti\\resenje.xml");
		Resenje resenje = domParser.parseResenje(document);
		domWriter.generateResenje(resenje);
		return domParser.getDocumentAsString(document);

	}
	/*
	public String jaxBTest(ResenjeDTO dto) throws Exception {
		JAXBContext context = JAXBContext.newInstance(User.class);
		
		
		User user = (User) jaxB.unmarshall(User.class, dto.getText());
		user.name = "novo ime";
		user.email = "novi email";
		System.out.println(user.id);
		
		return jaxB.marshall(User.class, user);
		
		
	}
	*/
}
