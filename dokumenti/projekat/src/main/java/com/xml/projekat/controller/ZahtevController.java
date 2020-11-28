package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.service.ZahtevService;


@RestController
@RequestMapping(value = "api/zahtev", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ZahtevController {
	private ZahtevService service;

	public ZahtevController(ZahtevService service) {
		super();
		this.service = service;
	}

	@GetMapping()
	public ResponseEntity<ZahtevDTO> parseZahtev() throws Exception{
		String response = service.parseZahtev();
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(response), HttpStatus.OK);
	}
	/*
	@PostMapping("/jaxB")
	public ResponseEntity<ZahtevDTO> getChangedXMLJaxB(@RequestBody ZahtevDTO dto) throws Exception{
		String response = service.jaxBTest(dto);
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(response), HttpStatus.OK);
	}
	*/
}
