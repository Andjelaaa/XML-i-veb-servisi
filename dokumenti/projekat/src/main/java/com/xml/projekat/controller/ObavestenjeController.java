package com.xml.projekat.controller;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xml.projekat.dto.ObavestenjeDTO;
import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.model.Obavestenje;
import com.xml.projekat.service.ObavestenjeService;


@RestController
@RequestMapping(value = "api/obavestenje", produces = MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
public class ObavestenjeController {
	
	private ObavestenjeService service;
	
	public ObavestenjeController(ObavestenjeService service) {
		super();
		this.service = service;
	}

	@PostMapping()
	public ResponseEntity<ObavestenjeDTO> parseObavestenje(@RequestBody RetrieveDTO dto) throws Exception{
		Obavestenje response = service.parseObavestenje(dto);
		return new ResponseEntity<ObavestenjeDTO>(new ObavestenjeDTO(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create")
	public ResponseEntity<Object> makeObavestenje(@RequestBody ObavestenjeDTO dto) throws Exception{
		if(!validate(dto)) {
			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
		}
		Obavestenje ob = new Obavestenje(dto);
		
		 try {
			 service.makeObavestenje(ob);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ObavestenjeDTO(ob),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ObavestenjeDTO(ob),HttpStatus.CREATED);
	}
	
	private boolean validate(ObavestenjeDTO dto) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");
		boolean dateOk = false;
		try{
			sdf1.parse(dto.getDatum());
			dateOk = true;
		}catch(Exception e){}
		try{
			sdf2.parse(dto.getDatum());
			dateOk = true;
		}catch(Exception e){}
		try{
			sdf3.parse(dto.getDatum());
			dateOk = true;
		}catch(Exception e){}
		if(dateOk == false) {
			return false;
		}
		if(dto.getNazivOrganaVlasti() == null || dto.getNazivOrganaVlasti().trim().equals(""))
			return false;
		if(dto.getSedisteOrgana() == null || dto.getSedisteOrgana().trim().equals(""))
			return false;
		if(dto.getNazivOrganaVlasti() == null || dto.getNazivOrganaVlasti().trim().equals(""))
			return false;
		if(dto.getNaslov() == null || dto.getNaslov().trim().equals(""))
			return false;
		if(dto.getBrojPredmeta() == null || dto.getBrojPredmeta().trim().equals(""))
			return false; // TODO proveriti sablon
		if(dto.getMestoPecata() == null || dto.getMestoPecata().trim().equals(""))
			return false;
		if(dto.getPodnosilac() == null)
			return false;
		if((dto.getPodnosilac().getIme() == null || dto.getPodnosilac().getIme().trim().equals("")
				|| dto.getPodnosilac().getPrezime() == null || dto.getPodnosilac().getPrezime().trim().equals("")) &&
				 (dto.getPodnosilac().getNazivFirme() == null || dto.getPodnosilac().getNazivFirme().trim().equals("")))
			return false;
		if(dto.getAdresa() == null)
			return false;
		if(dto.getAdresa().getUlica() == null || dto.getAdresa().getUlica().trim().equals("")
				|| dto.getAdresa().getBroj() == null || dto.getAdresa().getBroj().trim().equals("") ||
						dto.getAdresa().getGrad() == null || dto.getAdresa().getGrad().trim().equals(""))
			return false;
		if(dto.getPobavestenjeDTO() == null || dto.getPobavestenjeDTO().size() == 0)
			return false;
		return true;
	}

}
