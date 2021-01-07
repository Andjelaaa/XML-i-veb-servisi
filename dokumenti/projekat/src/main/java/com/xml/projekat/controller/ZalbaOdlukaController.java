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

	@PostMapping()
	public ResponseEntity<ZalbaOdlukaDTO> parseZalbaOdluke(@RequestBody RetrieveDTO dto) throws Exception{
		ZalbaOdluke response = service.parseZalbaOdluke(dto);
		return new ResponseEntity<ZalbaOdlukaDTO>(new ZalbaOdlukaDTO(response), HttpStatus.OK);
	}
	
	@PostMapping("/create")
	public ResponseEntity<Object> createZalbaOdluke(@RequestBody ZalbaOdlukaDTO dto) throws Exception{
		if(!validate(dto))
			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
	     
		ZalbaOdluke zo = new ZalbaOdluke(dto);
		
		 try {
			 service.createZalbaOdluke(zo);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.CREATED);
	
	}
	
	private boolean validate(ZalbaOdlukaDTO dto) {
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
		if(dto.getNazivPoverenika() == null || dto.getNazivPoverenika().trim().equals(""))
			return false;
		if(dto.getNaslov() == null || dto.getNaslov().trim().equals(""))
			return false;
		if(dto.getNazivOrganaVlasti() == null || dto.getNazivOrganaVlasti().trim().equals(""))
			return false;
		if(dto.getMesto() == null || dto.getMesto().trim().equals(""))
			return false;
		if(dto.getNazivPodnosioca() == null || dto.getAdresaPodnosioca() == null || dto.getSedistePoverenika() == null 
				|| dto.getParagrafi() == null || dto.getTackeNapomene() == null)
			return false;
		if((dto.getNazivPodnosioca().getIme() == null || dto.getNazivPodnosioca().getIme().trim().equals("")
				|| dto.getNazivPodnosioca().getPrezime() == null || dto.getNazivPodnosioca().getPrezime().trim().equals("")) &&
				 (dto.getNazivPodnosioca().getNazivFirme() == null || dto.getNazivPodnosioca().getNazivFirme().trim().equals("")))
			return false;
		if(dto.getAdresaPodnosioca().getUlica() == null || dto.getAdresaPodnosioca().getUlica().trim().equals("")
				|| dto.getAdresaPodnosioca().getBroj() == null || dto.getAdresaPodnosioca().getBroj().trim().equals("") ||
						dto.getAdresaPodnosioca().getGrad() == null || dto.getAdresaPodnosioca().getGrad().trim().equals(""))
			return false;
		if(dto.getSedistePoverenika().getUlica() == null || dto.getSedistePoverenika().getUlica().trim().equals("")
				|| dto.getSedistePoverenika().getBroj() == null || dto.getSedistePoverenika().getBroj().trim().equals("") ||
						dto.getSedistePoverenika().getGrad() == null || dto.getSedistePoverenika().getGrad().trim().equals(""))
			return false;
		if(dto.getParagrafi().size() == 0)
			return false;
		if(dto.getTackeNapomene().size() == 0)
			return false;
		
		return true;
	}
}
