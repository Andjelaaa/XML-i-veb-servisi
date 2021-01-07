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

	@PostMapping()
	public ResponseEntity<ZahtevDTO> parseZahtev(@RequestBody RetrieveDTO dto) throws Exception{
		Zahtev response = service.parseZahtev(dto);
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(response), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createResenje(@RequestBody ZahtevDTO dto) throws Exception{
		if(!validate(dto)) {
			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);     
		}
		Zahtev z = new Zahtev(dto);
		
		 try {
			 service.createZahtev(z);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ZahtevDTO(z),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZahtevDTO(z),HttpStatus.CREATED);
	}

	private boolean validate(ZahtevDTO dto) {
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
		if(dto.getDrugiPodaciZaKontakt() == null || dto.getDrugiPodaciZaKontakt().trim().equals(""))
			return false;
		if(dto.getNazivOrganaVlasti() == null || dto.getNazivOrganaVlasti().trim().equals(""))
			return false;
		if(dto.getSedisteOrgana() == null || dto.getSedisteOrgana().trim().equals(""))
			return false;
		if(dto.getNaslov() == null || dto.getNaslov().trim().equals(""))
			return false;
		if(dto.getTrazeneInformacije() == null || dto.getTrazeneInformacije().trim().equals(""))
			return false;
		if(dto.getMesto() == null || dto.getMesto().trim().equals(""))
			return false;
		if(dto.getPodnosilac() == null || dto.getAdresa() == null || 
				dto.getParagrafi() == null || dto.getFusnote() == null)
			return false;
		if((dto.getPodnosilac().getIme() == null || dto.getPodnosilac().getIme().trim().equals("")
				|| dto.getPodnosilac().getPrezime() == null || dto.getPodnosilac().getPrezime().trim().equals("")) &&
				 (dto.getPodnosilac().getNazivFirme() == null || dto.getPodnosilac().getNazivFirme().trim().equals("")))
			return false;
		if(dto.getAdresa().getUlica() == null || dto.getAdresa().getUlica().trim().equals("")
				|| dto.getAdresa().getBroj() == null || dto.getAdresa().getBroj().trim().equals("") ||
						dto.getAdresa().getGrad() == null || dto.getAdresa().getGrad().trim().equals(""))
			return false;
		if(dto.getParagrafi().size() == 0)
			return false;
		return true;
	}

}
