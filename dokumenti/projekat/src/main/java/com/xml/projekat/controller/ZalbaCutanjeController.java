package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.dto.ZalbaCutanjeDTO;
import com.xml.projekat.service.ZalbaCutanjeService;

@RestController
@RequestMapping(value = "api/zalba_cutanje", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ZalbaCutanjeController {
	private ZalbaCutanjeService service;

	public ZalbaCutanjeController(ZalbaCutanjeService service) {
		super();
		this.service = service;
	}
	
	@GetMapping()
	public ResponseEntity<ZalbaCutanjeDTO> parseZalbaCutanje() throws Exception{
		String response = service.parseZalbaCutanje();
		return new ResponseEntity<ZalbaCutanjeDTO>(new ZalbaCutanjeDTO(response), HttpStatus.OK);
	}
}
