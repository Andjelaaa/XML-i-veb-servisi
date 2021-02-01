package com.xml.poverenik.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.xmldb.api.base.XMLDBException;

import com.xml.poverenik.dto.LoginDTO;
import com.xml.poverenik.dto.TUserDTO;
import com.xml.poverenik.dto.UserTokenStateDTO;
import com.xml.poverenik.model.TUser;
import com.xml.poverenik.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	public UserService userService;


	@RequestMapping(value = "/login", method = RequestMethod.POST,produces = MediaType.APPLICATION_XML_VALUE, consumes = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<UserTokenStateDTO> login(@RequestBody LoginDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		UserTokenStateDTO jwt = null;
		try {
			jwt = userService.login(dto);
			if (jwt != null) {
				return new ResponseEntity<UserTokenStateDTO>(jwt, HttpStatus.OK);
			}
		}
		catch(Exception e) {
			return new ResponseEntity<UserTokenStateDTO>(jwt, HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<UserTokenStateDTO>(jwt, HttpStatus.OK);
		
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<?> register(@RequestBody TUserDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		
		TUser user;
		if(!this.validate(dto))
    		return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        try {
        	user = new TUser(dto);
        	userService.create(user);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
	}

	private boolean validate(TUserDTO dto) {
		if(dto.getFirstName().isEmpty())
			return false;
		if(dto.getLastName().isEmpty())
			return false;
		if(dto.getPassword().isEmpty())
			return false;
		if(dto.getEmail().isEmpty())
			return false;
		if(dto.getUsername().isEmpty())
			return false;
		return true;
	}
}
