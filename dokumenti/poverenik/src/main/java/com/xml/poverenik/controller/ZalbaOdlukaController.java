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
import com.xml.poverenik.dto.ZalbaOdlukaDTO;
import com.xml.poverenik.model.ZalbaOdluke;
import com.xml.poverenik.service.ZalbaOdlukaService;

@RestController
@RequestMapping(value = "api/zalba_odluke")
public class ZalbaOdlukaController {
	private ZalbaOdlukaService service;

	public ZalbaOdlukaController(ZalbaOdlukaService service) {
		super();
		this.service = service;
	}

	@PostMapping( produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<ZalbaOdlukaDTO> parseZalbaOdluke(@RequestBody RetrieveDTO dto) throws Exception{
		ZalbaOdluke response = service.parseZalbaOdluke(dto);
		return new ResponseEntity<ZalbaOdlukaDTO>(new ZalbaOdlukaDTO(response), HttpStatus.OK);
	}
	
	@GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> getHTML(@PathVariable("name") String name) throws XMLDBException {
		String result = service.convertXMLtoHTML(name);
		return new ResponseEntity<String>(result, HttpStatus.OK);
	}
	
	@PostMapping(value = "/create" ,produces =MediaType.APPLICATION_XML_VALUE, consumes =  MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<Object> createZalbaOdluke(@RequestBody ZalbaOdlukaDTO dto) throws Exception{
//		if(!validate(dto))
//			return new ResponseEntity<>("Invalid format!",HttpStatus.BAD_REQUEST);
	     
		ZalbaOdluke zo = new ZalbaOdluke(dto);
		
		 try {
			 service.createZalbaOdluke(zo);
	        }catch(Exception e) {
	         return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<>(new ZalbaOdlukaDTO(zo),HttpStatus.CREATED);
	
	}
	
	@GetMapping(value = "/{name}/pdf", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> getPdf(@PathVariable("name") String name) throws Exception {
		
		Resource resource = service.getPdf(name);
		
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value = "/userAppeal/{username}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaOdlukaDTO>> userRequests(@PathVariable("username") String username) throws XMLDBException {
		List<ZalbaOdlukaDTO> zalbeList = service.findAppealsByUser(username);
		return new ResponseEntity<List<ZalbaOdlukaDTO>>(zalbeList, HttpStatus.OK);
	}
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaOdlukaDTO>> getAll() throws XMLDBException {
		List<ZalbaOdlukaDTO> zalbeList = service.findAllAppeal();
		return new ResponseEntity<List<ZalbaOdlukaDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/new", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaOdlukaDTO>> getNew() throws XMLDBException {
		List<ZalbaOdlukaDTO> zalbeList = service.findNewAppeal();
		return new ResponseEntity<List<ZalbaOdlukaDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@GetMapping(value = "/searchRequests/{search}", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaOdlukaDTO>> searchRequests(@PathVariable("search") String search) throws XMLDBException, SAXException, IOException, ParserConfigurationException {
		List<ZalbaOdlukaDTO> zalbeList = service.findAppealsByContent(search);
		return new ResponseEntity<List<ZalbaOdlukaDTO>>(zalbeList, HttpStatus.OK);
	}
	
	@PostMapping(value = "/searchByMetadata", produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<List<ZalbaOdlukaDTO>> searchByMetadata(@RequestBody SearchDTO dto) throws IOException {
		List<ZalbaOdlukaDTO> result = service.searhByMetadata(dto);
		return new ResponseEntity<List<ZalbaOdlukaDTO>>(result, HttpStatus.OK);
	}
	
	@GetMapping(value = "/rdf/{uri}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> rdf(@PathVariable String uri) throws IOException {
		Resource resource = service.findRdf(uri);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value = "/json/{uri}", produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
	public ResponseEntity<Object> metadataJson(@PathVariable String uri) throws IOException {
		Resource resource = service.findJsonMetadata(uri);
		return ResponseEntity.ok().contentType(MediaType.APPLICATION_OCTET_STREAM)
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
				.body(resource);
	}
	
	@GetMapping(value = "/sendMail/{uri}")
	public ResponseEntity<Object> sendMail(@PathVariable String uri) throws IOException {
		
		try {
			 service.sendMail(uri);
	     }catch(Exception e) {
	    	 e.printStackTrace();
	         return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
	     }
		
		return new ResponseEntity<>(HttpStatus.OK);
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
