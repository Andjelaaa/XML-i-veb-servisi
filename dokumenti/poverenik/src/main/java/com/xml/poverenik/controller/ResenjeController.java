package com.xml.poverenik.controller;

import java.util.regex.Pattern;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.dto.ResenjeDTO;
import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.model.Resenje;
import com.xml.poverenik.service.ResenjeService;


@RestController
@RequestMapping(value = "api/resenje")
public class ResenjeController {
	private ResenjeService service;

	public ResenjeController(ResenjeService service) {
		super();
		this.service = service;
	}

	@PostMapping( produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ResenjeDTO> parseResenje(@RequestBody RetrieveDTO dto) throws Exception{
		Resenje response = service.parseResenje(dto);
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(response), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping(value ="/create", produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> createResenje(@RequestBody ResenjeDTO dto) throws Exception{
		if(!validate(dto)) {
			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
		     
		}
		Resenje entity = new Resenje(dto);
		 try {
			 service.createResenje(entity);
	     }catch(Exception e) {
	         return new ResponseEntity<>(new ResenjeDTO(entity),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ResenjeDTO(entity),HttpStatus.CREATED);
	}
	
	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {
		
		Resource resource = service.getPdf(name);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}

	private boolean validate(ResenjeDTO dto) {
		if(dto.getNaziv() == null || dto.getNaziv().trim().equals(""))
			return false;
		if(dto.getOdluka() == null || dto.getOdluka().trim().equals(""))
			return false;
		if(dto.getOpisPostupka() == null || dto.getOpisPostupka().trim().equals(""))
			return false;
		if(dto.getPotpisPoverenika() == null || dto.getPotpisPoverenika().trim().equals(""))
			return false;
		if(dto.getZaglavlje() == null)
			return false;
		if(dto.getZaglavlje().getBrojResenja() == null || dto.getZaglavlje().getBrojResenja().trim().equals("")
				|| !Pattern.matches("[0-9]{3}-[0-9]{2}-[0-9]{4}/[0-9]{4}-[0-9]{2}", dto.getZaglavlje().getBrojResenja())) 
			return false; //regex za npr. -> 071-01-1114/2020-03
		if(dto.getZaglavlje().getDatum() == null)
			return false;
		// nije provereno za tekstove, nepotrebno je
		return true;
	}
	

}


