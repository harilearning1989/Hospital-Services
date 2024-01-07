package com.web.demo.utils;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
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
    private long expirationTime;

    private Key key;

    @PostConstruct
    public void init() {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateJwtToken(Map<String, Object> claims) {
        long expirationTimeLong = expirationTime * 1000 * 5;
        return Jwts.builder()
                .setSubject(claims.get("username").toString())
                .setClaims(claims).setId(UUID.randomUUID().toString())
                .setIssuer("hari.learning")
                .setIssuedAt(new Date())
                .setExpiration(new Date((new Date()).getTime() + expirationTimeLong))
                //.signWith(this.key(), SignatureAlgorithm.HS256)
                .signWith(key)
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

    /*public class JwtService {

        @Value("${jwt.secret}")
        private String secret;

        @Value("${jwt.expiration}")
        private String expirationTime;

        private Key key;

        @PostConstruct
        public void init() {
            this.key = Keys.hmacShaKeyFor(secret.getBytes());
        }

        public Claims getAllClaimsFromToken(String token) {
            return Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
        }


        public Date getExpirationDateFromToken(String token) {
            return getAllClaimsFromToken(token).getExpiration();
        }

        private Boolean isTokenExpired(String token) {
            final Date expiration = getExpirationDateFromToken(token);
            return expiration.before(new Date());
        }

        public String generate(String email, String type) {
            Map<String, Object> claims = new HashMap<>();
            claims.put("email", email);
            claims.put("type", type);
            return doGenerateToken(claims, email, type);
        }

        private String doGenerateToken(Map<String, Object> claims, String username, String type) {
            long expirationTimeLong;
            if ("ACCESS".equals(type)) {
                expirationTimeLong = Long.parseLong(expirationTime) * 1000;
            } else {
                expirationTimeLong = Long.parseLong(expirationTime) * 1000 * 5;
            }
            final Date createdDate = new Date();
            final Date expirationDate = new Date(createdDate.getTime() + expirationTimeLong);

            return Jwts.builder()
                    .setClaims(claims)
                    .setSubject(username)
                    .setIssuedAt(createdDate)
                    .setExpiration(expirationDate)
                    .signWith(key)
                    .compact();
        }

        public Boolean validateToken(String token) {
            return !isTokenExpired(token);
        }
    }*/

}
