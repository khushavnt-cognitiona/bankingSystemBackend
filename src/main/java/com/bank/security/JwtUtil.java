package com.bank.security;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil  {
	
	 @Value("${jwt.secret}")
	    private String secret;

	    @Value("${jwt.expiration}")
	    private long expiration;

	    public String generateToken(String email) {

	        Key key = Keys.hmacShaKeyFor(secret.getBytes());

	        return Jwts.builder()
	                .setSubject(email)                 // username/email
	                .setIssuedAt(new Date())           // token created time
	                .setExpiration(
	                        new Date(System.currentTimeMillis() + expiration)
	                )
	                .signWith(key, SignatureAlgorithm.HS256)
	                .compact();
	    }
	    
	    public String extractEmail(String token) {
	    	 return extractClaims(token).getSubject();
	    	
	    }

		public boolean isTokenValid(String token) {
			 try {
		            extractClaims(token);
		            return true;
		        } catch (Exception e) {
		            return false;
		        }
			
		}

	 private Claims	extractClaims(String token) {
		 
		 Key key = Keys.hmacShaKeyFor(secret.getBytes());

	        return Jwts.parserBuilder()
	                .setSigningKey(key)
	                .build()
	                .parseClaimsJws(token)
	                .getBody();
		 
	 }
}
