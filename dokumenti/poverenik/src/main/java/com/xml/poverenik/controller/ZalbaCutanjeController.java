package com.xml.poverenik.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.dto.RetrieveDTO;
import com.xml.poverenik.dto.SearchDTO;
import com.xml.poverenik.dto.ZalbaCutanjeDTO;
import com.xml.poverenik.dto.ZalbaOdlukaDTO;
import com.xml.poverenik.model.ZalbaCutanje;
import com.xml.poverenik.service.ZalbaCutanjeService;

@RestController
@RequestMapping(value = "api/zalba_cutanje")
public class ZalbaCutanjeController {
	private ZalbaCutanjeService service;

	public ZalbaCutanjeController(ZalbaCutanjeService service) {
		super();
		this.service = service;
	}
	
	@PostMapping( produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ZalbaCutanjeDTO> parseZalbaCutanje(@RequestBody RetrieveDTO dto) throws Exception{
		ZalbaCutanje response = service.parseZalbaCutanje(dto);
		return new ResponseEntity<ZalbaCutanjeDTO>(new ZalbaCutanjeDTO(response), HttpStatus.OK);
	}
	
	@PostMapping(value = "/create",  produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> makeZalbaCutanje(@RequestBody ZalbaCutanjeDTO dto) throws Exception{
//		if(!validate(dto))
//			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
		System.out.print(dto.toString());
		ZalbaCutanje zc = new ZalbaCutanje(dto);
		try {
			 service.makeZalbaCutanje(zc);
	        }catch(Exception e) {
	         e.printStackTrace();
	         return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaCutanjeDTO(zc),HttpStatus.CREATED);
	}

	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {
		Resource resource = service.getPdf(name);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		System.out.println(result);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	@GetMapping(value = "/userAppeal/{username}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaCutanjeDTO>> userRequests(@PathVariable("username") String username) throws XMLDBException {
		List<ZalbaCutanjeDTO> zalbeList = service.findAppealsByUser(username);
//		for (ZalbaCutanjeDTO zalba: zalbeList) {
//			
//		}
		return new ResponseEntity<List<ZalbaCutanjeDTO>>(zalbeList, HttpStatus.OK);
	}
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaCutanjeDTO>> getAll() throws XMLDBException {
		List<ZalbaCutanjeDTO> zalbeList = service.findAllAppeal();
		return new ResponseEntity<List<ZalbaCutanjeDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/new", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaCutanjeDTO>> getNew() throws XMLDBException {
		List<ZalbaCutanjeDTO> zalbeList = service.findNewAppeal();
		return new ResponseEntity<List<ZalbaCutanjeDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchRequests/{search}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaCutanjeDTO>> searchRequests(@PathVariable("search") String search) throws XMLDBException, SAXException, IOException, ParserConfigurationException {
		List<ZalbaCutanjeDTO> zalbeList = service.findAppealsByContent(search);
		return new ResponseEntity<List<ZalbaCutanjeDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchByMetadata", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaCutanjeDTO>> searchByMetadata(@RequestBody SearchDTO dto) throws IOException {
		List<ZalbaCutanjeDTO> result = service.searhByMetadata(dto);
		return new ResponseEntity<List<ZalbaCutanjeDTO>>(result, HttpStatus.OK);
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
