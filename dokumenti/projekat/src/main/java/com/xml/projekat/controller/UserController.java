package com.xml.projekat.controller;

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

import com.xml.projekat.dto.LoginDTO;
import com.xml.projekat.dto.TUserDTO;
import com.xml.projekat.dto.UserTokenStateDTO;
import com.xml.projekat.model.TUser;
import com.xml.projekat.service.UserService;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	public UserService userService;


	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = MediaType.APPLICATION_XML_VALUE, produces = MediaType.APPLICATION_XML_VALUE)
	public ResponseEntity<UserTokenStateDTO> login(@RequestBody LoginDTO dto)
			throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException {
		UserTokenStateDTO jwt = userService.login(dto);
		if (jwt != null) {
			return new ResponseEntity<UserTokenStateDTO>(jwt, HttpStatus.OK);
		}
		return new ResponseEntity<UserTokenStateDTO>(jwt, HttpStatus.BAD_REQUEST);
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
