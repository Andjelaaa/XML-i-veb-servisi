package com.xml.projekat.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dto.ResenjeDTO;
import com.xml.projekat.service.ResenjeService;

@RestController
@RequestMapping(value = "api/resenje")
public class ResenjeController {
	private ResenjeService service;

	public ResenjeController(ResenjeService service) {
		super();
		this.service = service;
	}

	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}

	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {

		Resource resource = service.getPdf(name);

		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ResenjeDTO>> getAll() throws XMLDBException {
		List<ResenjeDTO> resenjaList = service.findAllDecisions();
		return new ResponseEntity<List<ResenjeDTO>>(resenjaList, HttpStatus.OK);
	}

	@GetMapping(value = "/searchRequests/{search}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ResenjeDTO>> searchRequests(@PathVariable("search") String search)
			throws XMLDBException, SAXException, IOException, ParserConfigurationException, ParseException {
		List<ResenjeDTO> resenjeList = service.findDecisionsByContent(search);
		return new ResponseEntity<List<ResenjeDTO>>(resenjeList, HttpStatus.OK);
	}

}
