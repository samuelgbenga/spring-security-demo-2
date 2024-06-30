package com.samuel.security.demo2.app.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${jwt.secrete.key}")
    private String jwtSecreteKey;


    // extract the username from token
    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    // extract the claims
    public <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    // extract all claims

    private Claims extractAllClaims(String token){

       return Jwts.parser()
               .setSigningKey(getSignInKey())
               .build()
               .parseClaimsJws(token)
               .getBody();
    }

    // generate Token
    private String generateToken(Map<String, Object> extractClaim, UserDetails userDetails){
        return Jwts.builder()
                .setClaims(extractClaim)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis()+1000 * 60 * 20))
                .signWith(getSignInKey(), SignatureAlgorithm.HS256)
                .compact();
    }

    // generate token
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(), userDetails);
    }

    // check if toke is valid
    public boolean isTokenValid(String token, UserDetails userDetails){
        final String userName = extractUserName(token);
        return (userName.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    // check if token is expired
    private boolean isTokenExpired(String token){
        return extractExpirationDate(token).before(new Date());
    }

    // extract expiration date
    private Date extractExpirationDate(String token){
        return extractClaim(token, Claims::getExpiration);
    }

    // get Signed key

    private Key getSignInKey() {

        byte[] keyBytes = Decoders.BASE64.decode(jwtSecreteKey);

        return Keys.hmacShaKeyFor(keyBytes);

    }


}
