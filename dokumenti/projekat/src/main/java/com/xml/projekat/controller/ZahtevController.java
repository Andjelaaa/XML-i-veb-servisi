package com.xml.projekat.controller;

import java.text.SimpleDateFormat;
import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dto.RetrieveDTO;
import com.xml.projekat.dto.ZahtevDTO;
import com.xml.projekat.model.Zahtev;
import com.xml.projekat.service.ZahtevService;


@RestController
@RequestMapping(value = "api/zahtev")
public class ZahtevController {
	private ZahtevService service;

	public ZahtevController(ZahtevService service) {
		super();
		this.service = service;
	}

	@PostMapping(produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ZahtevDTO> parseZahtev(@RequestBody RetrieveDTO dto) throws Exception{
		Zahtev response = service.parseZahtev(dto);
		return new ResponseEntity<ZahtevDTO>(new ZahtevDTO(response), HttpStatus.OK);
	}
	
	@PostMapping(value ="/create", produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> createZahtev(@RequestBody ZahtevDTO dto) throws Exception{
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
	
	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {
		Resource resource = service.getPdf(name);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/userRequests/{username}", produces = MediaType.APPLICATION_XML_VALUE)
//	@PreAuthorize(hasRole="GRADJANIN")
	//@CrossOrigin(origins = "http://localhost:8081")
	public ResponseEntity<List<ZahtevDTO>> userRequests(@PathVariable("username") String username) throws XMLDBException {
		System.out.println("tu sammm");
		List<ZahtevDTO> zahteviList = service.findRequestsByUser(username);
		return new ResponseEntity<List<ZahtevDTO>>(zahteviList, HttpStatus.OK);
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
