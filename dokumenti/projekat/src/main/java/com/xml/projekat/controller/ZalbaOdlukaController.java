package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ZalbaOdlukaDTO;
import com.xml.projekat.model.ZalbaOdluke;
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
	
	@PostMapping("/create")
	public ResponseEntity<ZalbaOdlukaDTO> createZalbaOdluke(@RequestBody ZalbaOdlukaDTO dto) throws Exception{
		System.out.println(dto);
		ZalbaOdluke zo = new ZalbaOdluke(dto);
		
		 try {
			 service.createZalbaOdluke(zo);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.CREATED);
	
	}
}
