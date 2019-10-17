package com.keshav.homelearning.SpringJWTToken.config.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data

public class JWTResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4674291624105969907L;
	
	private final String jwttoken;
}
