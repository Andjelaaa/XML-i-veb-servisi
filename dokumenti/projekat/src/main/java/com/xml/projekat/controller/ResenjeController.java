package com.xml.projekat.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ResenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Resenje;
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
	public ResponseEntity<ResenjeDTO> parseResenje(@RequestBody RetrieveDTO dto) throws Exception{
		Resenje response = service.parseResenje(dto);
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(response), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<ResenjeDTO> createResenje(@RequestBody ResenjeDTO dto) throws Exception{
		Resenje entity = new Resenje(dto);
		
		 try {
			 service.createResenje(entity);
	        }catch(Exception e) {
	         return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(entity),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<ResenjeDTO>(new ResenjeDTO(entity),HttpStatus.CREATED);
	}

}


