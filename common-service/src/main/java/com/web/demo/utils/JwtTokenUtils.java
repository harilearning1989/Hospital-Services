package com.web.demo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Component
public class JwtTokenUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenUtils.class);

    @Value("${jwt.secret}")
    private String secret;
    @Value("${jwt.token.validity}")
    private long tokenValidity;

    public String generateJwtToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setSubject(claims.get("username").toString())
                .setClaims(claims).setId(UUID.randomUUID().toString())
                .setIssuer("hari.learning")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + this.tokenValidity))
                .signWith(this.key(), SignatureAlgorithm.HS256)
                .compact();
    }

    private Key key() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }

    /*public String getUserNameFromJwtToken(String token) {
        return ((Claims) Jwts.parserBuilder()
                .setSigningKey(this.key())
                .build()
                .parseClaimsJws(token)
                .getBody()).get("username").toString();
    }*/
    public String getUserNameFromJwtToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("username")
                .toString();
    }
    private Claims getAllClaimsFromToken(String token) {
        return (Claims)Jwts.parser().setSigningKey(this.secret).parseClaimsJws(token).getBody();
    }
    public boolean validateJwtToken(String authToken) {
        try {
            Jwts.parserBuilder().setSigningKey(key()).build().parse(authToken);
            return true;
        } catch (MalformedJwtException e) {
            logger.error("Invalid JWT token: {}", e.getMessage());
        } catch (ExpiredJwtException e) {
            logger.error("JWT token is expired: {}", e.getMessage());
        } catch (UnsupportedJwtException e) {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        } catch (IllegalArgumentException e) {
            logger.error("JWT claims string is empty: {}", e.getMessage());
        }
        return false;
    }

}
