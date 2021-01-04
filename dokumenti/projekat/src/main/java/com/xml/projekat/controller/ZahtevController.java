package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.model.Zahtev;
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
	public ResponseEntity<ZahtevDTO> parseZahtev(@RequestBody RetrieveDTO dto) throws Exception{
		Zahtev response = service.parseZahtev(dto);
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(response), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ZahtevDTO> createResenje(@RequestBody ZahtevDTO dto) throws Exception{
		Zahtev z = new Zahtev(dto);
		
		 try {
			 service.createZahtev(z);
	        }catch(Exception e) {
	         return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(z),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(z),HttpStatus.CREATED);
	}

}
