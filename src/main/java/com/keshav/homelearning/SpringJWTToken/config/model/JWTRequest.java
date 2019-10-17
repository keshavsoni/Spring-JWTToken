package com.keshav.homelearning.SpringJWTToken.config.model;

import java.io.Serializable;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Setter@Getter
public class JWTRequest implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1948571252503052503L;
	
	private String username;
	private String password;

}
