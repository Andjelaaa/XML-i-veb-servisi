package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ZalbaOdlukaDTO;
import com.xml.projekat.service.ZalbaOdlukaService;

@RestController
@RequestMapping(value = "api/zalbaodluke", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ZalbaOdlukaController {
	private ZalbaOdlukaService service;

	public ZalbaOdlukaController(ZalbaOdlukaService service) {
		super();
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity<ZalbaOdlukaDTO> parseZalbaOdluke() throws Exception{
		String response = service.parseZalbaOdluke();
		return new ResponseEntity<ZalbaOdlukaDTO>(new ZalbaOdlukaDTO(response), HttpStatus.OK);
	}
	
	@GetMapping("/create")
	public ResponseEntity<ZalbaOdlukaDTO> createZalbaOdluke() throws Exception{
		String response = service.createZalbaOdluke();
		return new ResponseEntity<ZalbaOdlukaDTO>(new ZalbaOdlukaDTO(response), HttpStatus.OK);
	}
}
