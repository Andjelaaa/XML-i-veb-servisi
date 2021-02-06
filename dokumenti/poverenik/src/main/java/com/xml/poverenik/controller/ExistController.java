package com.xml.poverenik.controller;


import java.io.IOException;
import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
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

import com.xml.poverenik.database.ExistManager;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.StoreDTO;


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
	
	@RequestMapping(value = "/load", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public void load(@RequestBody RetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.load(dto.getCollectionId(), dto.getXpath());
	}

	@RequestMapping(value = "/get", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE)
	public void get(@RequestBody RetrieveDTO dto)
			throws XMLDBException, ClassNotFoundException, InstantiationException, IllegalAccessException {
		existManager.retrieve(dto.getCollectionId(), dto.getXpath());
	}

	@GetMapping(value = "/initiateData")
	public void initiateDate()
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, IOException {
		Resource resource = new ClassPathResource("podaci");
		URI a = resource.getURI();

		existManager.store("/db/poverenik/korisnici", "korisnici.xml", a.getPath() + "/korisnici.xml");

	}
}

