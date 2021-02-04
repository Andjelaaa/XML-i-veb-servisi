package com.xml.projekat.controller;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.MediaType;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "api/soap")
public class SoapController {
	
	@GetMapping(value = "/hello",  produces = MediaType.ALL_VALUE, consumes =  MediaType.ALL_VALUE)
	public ResponseEntity<String> callFunction() throws Exception{

		 try {
			System.out.println("uso sam");
	        }catch(Exception e) {
	         return new ResponseEntity<>("predator is not here",HttpStatus.BAD_REQUEST);
	     }
			
		return new ResponseEntity<String>("predator is here",HttpStatus.CREATED);
	}
}