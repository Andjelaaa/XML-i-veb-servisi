package com.xml.poverenik.controller;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.dto.IzvestajDTO;
import com.xml.poverenik.dto.ResenjeDTO;
import com.xml.poverenik.service.IzvestajService;

@RestController
@RequestMapping(value = "api/izvestaj")
public class IzvestajController {
	
	private IzvestajService service;

	public IzvestajController(IzvestajService service) {
		super();
		this.service = service;
	}
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<IzvestajDTO>> getAll() throws XMLDBException {
		List<IzvestajDTO> resenjaList = service.findAllReports();
		return new ResponseEntity<List<IzvestajDTO>>(resenjaList, HttpStatus.OK);
	}
	

	/*
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
	
	*/

}
