package com.xml.projekat.service;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.xml.sax.SAXException;
import org.xmldb.api.base.XMLDBException;

import com.xml.projekat.dom.DOMParser;
import com.xml.projekat.dom.DOMWriter;
import com.xml.projekat.dto.LoginDTO;
import com.xml.projekat.dto.UserTokenStateDTO;
import com.xml.projekat.model.TUser;
import com.xml.projekat.repository.UserRepository;
import com.xml.projekat.security.TokenUtils;


@Service
public class UserService {

	private final DOMWriter domWriter = new DOMWriter();
	
	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
    private TokenUtils tokenUtils;

    @Autowired
    private CustomUserDetailsService userDetailsService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	public TUser findOneByUsername(String username) {
		return userRepository.findOneByUsername(username);
	}

	public UserTokenStateDTO login(LoginDTO authenticationRequest) {
        Authentication authentication = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(),
                        authenticationRequest.getPassword()));
        
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Kreiraj token za tog korisnika
        TUser user = (TUser) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user); // prijavljujemo se na sistem sa email adresom
        int expiresIn = tokenUtils.getExpiredIn();
        
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return new UserTokenStateDTO(jwt, expiresIn);
	}

	public void create(TUser user) throws Exception {
		
		//TUser tUser = userRepository.findOne(user.getUsername());
		//if (tUser != null)
		//	throw new Exception("User with given username already exist");
		TUser t = new TUser();
		t.setFirstName(user.getFirstName());
		t.setLastName(user.getLastName());
		t.setEmail(user.getEmail());
		t.setUsername(user.getUsername());
		t.setPassword(passwordEncoder.encode(user.getPassword()));
		t.setTitle(user.getTitle());
		t.setRole(user.getRole());
		String documentContent = domWriter.generateUser(t);
        userRepository.save(documentContent, t.getUsername());
		
	}

	public List<String> findAll() throws ClassNotFoundException, InstantiationException, IllegalAccessException, XMLDBException, ParserConfigurationException, SAXException, IOException {
		// TODO Auto-generated method stub
		return userRepository.findAllUsernames();
	}

}
