package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ResenjeDTO;
import com.xml.projekat.service.ResenjeService;


@RestController
@RequestMapping(value = "api/resenje", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ResenjeController {
	private ResenjeService service;

	public ResenjeController(ResenjeService service) {
		super();
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity<ResenjeDTO> parseResenje() throws Exception{
		String response = service.parseResenje();
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(response), HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ResponseEntity<ResenjeDTO> createResenje() throws Exception{
		String response = service.createResenje();
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(response), HttpStatus.OK);
	}
	/*
	@PostMapping("/jaxB")
	public ResponseEntity<ResenjeDTO> getChangedXMLJaxB(@RequestBody ResenjeDTO dto) throws Exception{
		String response = service.jaxBTest(dto);
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(response), HttpStatus.OK);
	}
	*/
}


