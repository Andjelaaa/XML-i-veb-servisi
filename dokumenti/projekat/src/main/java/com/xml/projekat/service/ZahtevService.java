package com.xml.projekat.service;


import javax.xml.bind.JAXBContext;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.jaxb.JaxB;

@Service
public class ZahtevService {
	private final DOMParser domParser;
	private final JaxB jaxB;

	public ZahtevService(DOMParser domParser, JaxB jaxB) {
		this.domParser = domParser;
		this.jaxB = jaxB;
	}

	public String playWithXML(ZahtevDTO dto) throws Exception {
		Document document = domParser.buildDocumentFromText(dto.getText());
		NodeList profesori = document.getElementsByTagName("profesor");

		for (int i = 0; i < profesori.getLength(); i++) {
			Element profesor = (Element) profesori.item(i);
			profesor.setAttribute("id", "prof" + i);

			Element titula = document.createElement("Titila");
			titula.appendChild(document.createTextNode("Profesor"));
			profesor.appendChild(titula);
		}

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
