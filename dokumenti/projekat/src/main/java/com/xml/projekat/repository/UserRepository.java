package com.xml.projekat.repository;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.parsers.ParserConfigurationException;

import org.exist.xmldb.EXistResource;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xmldb.api.base.ResourceIterator;
import org.xmldb.api.base.ResourceSet;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.xml.projekat.database.ExistManager;
import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.model.TUser;


@Repository
public class UserRepository {

	private ExistManager existManager;
	private DOMParser domParser;

	public UserRepository(ExistManager existMenager, DOMParser domParser) {
		this.existManager = existMenager;
		this.domParser = domParser;
	}

	private String collectionId = "/db/dokumenti/korisnici";
	private String documentId = "korisnici.xml";
	
	public void save(String documentContent, String username)
		throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.appendUser(collectionId, documentId, documentContent, username);
	}
	public TUser findOneByUsername(String username) {

		String xPathExpression = String.format("/Users/user[username='%s']", username);
		TUser foundUser = null;
		try {
			ResourceSet result = existManager.retrieve(collectionId, xPathExpression);
			
			if (result == null) {
				return null;
			}
			ResourceIterator i = result.getIterator();
			XMLResource resource = null;

			while (i.hasMoreResources()) {
				try {
					resource = (XMLResource) i.nextResource();
					foundUser = unmarshal(resource);
				
				} finally {
					try {
						((EXistResource) resource).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return foundUser;
	}

	public List<String> findAllUsernames() throws ClassNotFoundException, InstantiationException,
			IllegalAccessException, XMLDBException, ParserConfigurationException, SAXException, IOException {
		List<String> usernames = new ArrayList<>();

		String xPathExpression = String.format("/Users/user/username");
		ResourceSet result = existManager.retrieve(collectionId, xPathExpression);
		ResourceIterator i = result.getIterator();
		XMLResource resource = null;
		while (i.hasMoreResources()) {
			try {
				resource = (XMLResource) i.nextResource();
				Document document = domParser.buildDocumentFromText(resource.getContent().toString());
				usernames.add(document.getElementsByTagName("ns2:username").item(0).getTextContent());
			} finally {
				try {
					((EXistResource) resource).freeResources();
				} catch (XMLDBException xe) {
					xe.printStackTrace();
				}
			}
		}
		return usernames;
	}


	public String marshal(TUser user) {
		String retVal = null;
		try {
			JAXBContext context = JAXBContext.newInstance(TUser.class);
			Marshaller marshaller = context.createMarshaller();
			marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);

			ByteArrayOutputStream stream = new ByteArrayOutputStream();
			marshaller.marshal(user, stream);
			retVal = new String(stream.toByteArray());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retVal;
	}

	private TUser unmarshal(XMLResource resource) {
		TUser user = null;
		try {
			JAXBContext context = JAXBContext.newInstance(TUser.class);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			user = (TUser) unmarshaller.unmarshal(resource.getContentAsDOM());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return user;
	}
	
	public TUser findOne(String username) {
		
		String xPathExpression = String.format("/Users/user[username='%s']", username);
		TUser foundUser = null;
		try {
			ResourceSet result = existManager.retrieve(collectionId, xPathExpression);
			
			if (result == null) {
				return null;
			}
			ResourceIterator i = result.getIterator();
			XMLResource resource = null;

			while (i.hasMoreResources()) {
				try {
					resource = (XMLResource) i.nextResource();
					foundUser = unmarshal(resource);
				
				} finally {
					try {
						((EXistResource) resource).freeResources();
					} catch (XMLDBException xe) {
						xe.printStackTrace();
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(foundUser.getUsername());
		return foundUser;
	}
	
	

}
