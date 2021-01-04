package com.xml.projekat.controller;


import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.database.ExistManager;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.StoreDTO;


@RestController
@RequestMapping("/api/exist")
@CrossOrigin
public class ExistController {
	@Autowired
	public ExistManager existManager;

//	@Value("${abs.path}")
//	private String absolutePath;

	@RequestMapping(value = "/store", method = RequestMethod.POST)
	public void store(@RequestBody StoreDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.store(dto.getCollectionId(), dto.getName(), dto.getPath());
	}
	
	@RequestMapping(value = "/load", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void load(@RequestBody RetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.load(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	public void get(@RequestBody RetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.retrieve(dto.getCollectionId(), dto.getXpath());
	}

//	@RequestMapping(value = "/remove", method = RequestMethod.DELETE, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void remove(@RequestBody XPathRetrieveDTO dto)
//			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
//		existManager.remove(dto.getCollectionId(), dto.getXpath());
//	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
//	public void update(@RequestBody UpdateDTO dto)
//			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
//		existManager.update(0, dto.getCollectionId(), dto.getDocumentId(), dto.getContextXPath(), dto.getPatch());
//	}

	@GetMapping(value = "/initiateData")
	public void initiateDate()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, IOException {
		Resource resource = new ClassPathResource("podaci");
		URI a = resource.getURI();

		existManager.store("/db/dokumenti/korisnici", "korisnici.xml", a.getPath() + "/korisnici.xml");
//		existManager.store("/db/paperShare/CoverLetters", "CoverLetters.xml", a.getPath() + "/CoverLetters.xml");
//		existManager.store("/db/paperShare/reviews", "rev1.xml", a.getPath() + "/rev1.xml");
//		existManager.store("/db/paperShare/reviews", "rev2.xml", a.getPath() + "/rev2.xml");
//		existManager.store("/db/paperShare/reviews", "rev3.xml", a.getPath() + "/rev3.xml");
//		existManager.store("/db/paperShare/ScientificPapers", "etika.xml", a.getPath() + "/etika.xml");
//		existManager.store("/db/paperShare/ScientificPapers", "auroraBorealis.xml", a.getPath() + "/auroraBorealis.xml");
	}
}

