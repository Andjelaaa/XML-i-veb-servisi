package com.xml.projekat.controller;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dto.IzvestajDTO;
import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Izvestaj;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.service.IzvestajService;

@RestController
@RequestMapping(value = "api/izvestaj")
public class IzvestajController {
	
	private IzvestajService service;

	public IzvestajController(IzvestajService izvestajService) {
		super();
		this.service = izvestajService;
	}
	
	@PostMapping( produces = MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<IzvestajDTO> parseObavestenje(@RequestBody RetrieveDTO dto) throws Exception{
		Izvestaj response = service.parseIzvestaj(dto);
		return new ResponseEntity<IzvestajDTO>(new IzvestajDTO(response), HttpStatus.OK);
	}
	
	@GetMapping(value = "/create",  produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> makeIzvestaj() throws Exception{
		
		Izvestaj iz = new Izvestaj();
		 try {
			iz = service.makeIzvestaj();
	        }catch(Exception e) {
	         return new ResponseEntity<>(new IzvestajDTO(iz),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new IzvestajDTO(iz),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {
		
		Resource resource = service.getPdf(name);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	
	
	
}
