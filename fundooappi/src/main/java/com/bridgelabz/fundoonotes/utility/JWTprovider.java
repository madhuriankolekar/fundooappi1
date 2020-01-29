package com.bridgelabz.fundoonotes.utility;
import org.springframework.context.annotation.Configuration;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;


@Configuration
public class JWTprovider {
	public String generateToken(String emailId) {
		
		
			return JWT.create().withClaim("email", emailId).sign(Algorithm.HMAC512(emailId));
		
	}

	public String parseToken(String token) {
		return JWT.require(Algorithm.HMAC512(token)).build().verify(token).getClaim("email").asString();
	}

}
