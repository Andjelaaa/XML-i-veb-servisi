package com.xml.poverenik.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.xml.poverenik.dto.LoginDTO;
import com.xml.poverenik.dto.UserTokenStateDTO;
import com.xml.poverenik.model.TUser;
import com.xml.poverenik.repository.UserRepository;
import com.xml.poverenik.security.TokenUtils;

@Service
public class UserService {

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
       
        // Ubaci korisnika u trenutni security kontekst
        SecurityContextHolder.getContext().setAuthentication(authentication);
        
        // Kreiraj token za tog korisnika
        TUser user = (TUser) authentication.getPrincipal();
        String jwt = tokenUtils.generateToken(user.getUsername()); // prijavljujemo se na sistem sa email adresom
        int expiresIn = tokenUtils.getExpiredIn();
        
        // Vrati token kao odgovor na uspesnu autentifikaciju
        return new UserTokenStateDTO(jwt, expiresIn);
	}

}
