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
import com.xml.projekat.dto.ZalbaCutanjeDTO;
import com.xml.projekat.model.ZalbaCutanje;
import com.xml.projekat.service.ZalbaCutanjeService;

@RestController
@RequestMapping(value = "api/zalba_cutanje", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ZalbaCutanjeController {
	private ZalbaCutanjeService service;

	public ZalbaCutanjeController(ZalbaCutanjeService service) {
		super();
		this.service = service;
	}
	
	@PostMapping()
	public ResponseEntity<ZalbaCutanjeDTO> parseZalbaCutanje(@RequestBody RetrieveDTO dto) throws Exception{
		ZalbaCutanje response = service.parseZalbaCutanje(dto);
		return new ResponseEntity<ZalbaCutanjeDTO>(new ZalbaCutanjeDTO(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<ZalbaCutanjeDTO> makeZalbaCutanje(@RequestBody ZalbaCutanjeDTO dto) throws Exception{
		ZalbaCutanje zc = new ZalbaCutanje(dto);
		
		 try {
			 service.makeZalbaCutanje(zc);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.CREATED);
	}
}
