package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.ObavestenjeeDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.service.ObavestenjeService;


@RestController
@RequestMapping(value = "api/obavestenje", produces = MediaType.APPLICATION_JSON_VALUE, consumes =  MediaType.APPLICATION_JSON_VALUE)
public class ObavestenjeController {
	
	private ObavestenjeService service;
	
	public ObavestenjeController(ObavestenjeService service) {
		super();
		this.service = service;
	}

	@GetMapping(value = "/")
	public ResponseEntity<ObavestenjeDTO> parseObavestenje() throws Exception{
		String response = service.parseObavestenje();
		return new ResponseEntity<ObavestenjeDTO>(new ObavestenjeDTO(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/novo_obavestenje/")
	public ResponseEntity<ObavestenjeDTO> makeObavestenje(@RequestBody ObavestenjeeDTO obavestenjeDTO) throws Exception{
		//convert DTO TO obican 
		Obavestenje ob = new Obavestenje(obavestenjeDTO);
		
		 try {
			 service.makeObavestenje(ob);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ObavestenjeDTO(), HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ObavestenjeDTO(), HttpStatus.CREATED);
	}

}
