package com.xml.projekat.controller;

import java.text.SimpleDateFormat;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<Object> makeZalbaCutanje(@RequestBody ZalbaCutanjeDTO dto) throws Exception{
		if(!validate(dto))
			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
	     
		ZalbaCutanje zc = new ZalbaCutanje(dto);
		try {
			 service.makeZalbaCutanje(zc);
	        }catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.CREATED);
	}

	private boolean validate(ZalbaCutanjeDTO dto) {
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy/MM/dd");
		SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy.MM.dd");
		boolean dateOk = false;
		try{
			sdf1.parse(dto.getDatumZalbe());
			dateOk = true;
		}catch(Exception e){}
		try{
			sdf2.parse(dto.getDatumZalbe());
			dateOk = true;
		}catch(Exception e){}
		try{
			sdf3.parse(dto.getDatumZalbe());
			dateOk = true;
		}catch(Exception e){}
		if(dateOk == false) {
			return false;
		}
		if(dto.getDrugiPodaciZaKontakt() == null || dto.getDrugiPodaciZaKontakt().trim().equals(""))
			return false;
		if(dto.getNazivPoverenika() == null || dto.getNazivPoverenika().trim().equals(""))
			return false;
		if(dto.getNaslov() == null || dto.getNaslov().trim().equals(""))
			return false;
		if(dto.getMestoZalbe() == null || dto.getMestoZalbe().trim().equals(""))
			return false;
		if(dto.getPodnosilac() == null || dto.getAdresa() == null || dto.getSedistePoverenika() == null || dto.getParagrafi() == null)
			return false;
		if((dto.getPodnosilac().getIme() == null || dto.getPodnosilac().getIme().trim().equals("")
				|| dto.getPodnosilac().getPrezime() == null || dto.getPodnosilac().getPrezime().trim().equals("")) &&
				 (dto.getPodnosilac().getNazivFirme() == null || dto.getPodnosilac().getNazivFirme().trim().equals("")))
			return false;
		if(dto.getAdresa().getUlica() == null || dto.getAdresa().getUlica().trim().equals("")
				|| dto.getAdresa().getBroj() == null || dto.getAdresa().getBroj().trim().equals("") ||
						dto.getAdresa().getGrad() == null || dto.getAdresa().getGrad().trim().equals(""))
			return false;
		if(dto.getSedistePoverenika().getUlica() == null || dto.getSedistePoverenika().getUlica().trim().equals("")
				|| dto.getSedistePoverenika().getBroj() == null || dto.getSedistePoverenika().getBroj().trim().equals("") ||
						dto.getSedistePoverenika().getGrad() == null || dto.getSedistePoverenika().getGrad().trim().equals(""))
			return false;
		if(dto.getParagrafi().size() == 0)
			return false;
		
		return true;
	}
}
