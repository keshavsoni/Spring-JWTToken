package com.keshav.homelearning.SpringJWTToken.config.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.keshav.homelearning.SpringJWTToken.config.JwtTokenUtil;
import com.keshav.homelearning.SpringJWTToken.config.model.JWTRequest;
import com.keshav.homelearning.SpringJWTToken.config.model.JWTResponse;
import com.keshav.homelearning.SpringJWTToken.config.service.JwtUserDetailsService;

@RestController
@CrossOrigin
public class JwtAuthenticationController {
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	@Autowired
	private JwtUserDetailsService userDetailsService;
	
	private void authenticate(String username, String password) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
		} catch (DisabledException e) {
			throw new Exception("USER_DISABLED", e);
		} catch (BadCredentialsException e) {
			throw new Exception("INVALID_CREDENTIALS", e);
		}
	}
	
	@RequestMapping(value="/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody JWTRequest jWTRequest) throws Exception{

		authenticate(jWTRequest.getUsername(), jWTRequest.getPassword());
		final UserDetails userDetails = userDetailsService.loadUserByUsername(jWTRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails);
		
		return ResponseEntity.ok(new JWTResponse(token));
	}

}
