package com.drone.drone.jwt;



import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.drone.drone.dto.LoginUser;
import com.drone.drone.repos.UserRepo;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtUtils {
	@Autowired
	UserRepo userrepo;

    @Value("${jwt.secret}")
    private String key;

    @Value("${jwt.expiration}")
    private Long expirationTime;

  public String getAuthToken(HttpServletRequest request) {
	  
	  
	  String token=request.getHeader("Authorization");
	  if (token!=null) {
		  return token.substring(7);
	  }
	  return null;
  }
  
  
  
  private String createToken(Map<String, Object> claims, String subject) {
      return Jwts.builder()
              .setClaims(claims)
              .setSubject(subject)
              .setIssuedAt(new Date(System.currentTimeMillis()))
              .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
              
             
              .signWith(SignatureAlgorithm.HS256,key)
              .compact();
  }
  
  
  public String extractUsername(String token) {
      return extractClaim(token, Claims::getSubject);
  }

  public Date extractExpiration(String token) {
      return extractClaim(token, Claims::getExpiration);
  }

  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
      final Claims claims = extractAllClaims(token);
      return claimsResolver.apply(claims);
  }

  @SuppressWarnings("deprecation")
private Claims extractAllClaims(String token) {
      return Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();
  }

  private Boolean isTokenExpired(String token) {
//	  System.out.print("the token expiration is :"+extractExpiration(token).before(new Date())+" expires at"+extractExpiration(token));
      return extractExpiration(token).before(new Date());
  }

  public String generateToken(String username) {
      Map<String, Object> claims = new HashMap<>();
      return createToken(claims, username);
  }

 

  public boolean validateToken(String token, UserDetails userDetails) {
      final String userName = extractUsername(token);
      return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
  }
  
  
  
  
  
}
