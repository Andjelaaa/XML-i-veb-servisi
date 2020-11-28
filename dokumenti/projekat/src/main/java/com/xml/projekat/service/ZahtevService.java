package com.xml.projekat.service;


import org.springframework.stereotype.Service;
import org.w3c.dom.Document;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.jaxb.JaxB;
import com.xml.projekat.model.Zahtev;

@Service
public class ZahtevService {
	private final DOMParser domParser;
	private final JaxB jaxB;

	public ZahtevService(DOMParser domParser, JaxB jaxB) {
		this.domParser = domParser;
		this.jaxB = jaxB;
	}

	public String parseZahtev() throws Exception {
		Document document = domParser.buildDocumentFromFile("C:\\Users\\teodo\\Desktop\\XML-i-veb-servisi\\dokumenti\\zahtev.xml");
		Zahtev zahtev= domParser.parseZahtev(document);

		return domParser.getDocumentAsString(document);

	}
/*
	public String jaxBTest(ZahtevDTO dto) throws Exception {
		JAXBContext context = JAXBContext.newInstance(User.class);
		
		
		User user = (User) jaxB.unmarshall(User.class, dto.getText());
		user.name = "novo ime";
		user.email = "novi email";
		System.out.println(user.id);
		
		return jaxB.marshall(User.class, user);
		
		
	}
	*/
}
