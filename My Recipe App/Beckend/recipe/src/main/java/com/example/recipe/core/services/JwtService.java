package com.example.recipe.core.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwt.key}")
    private String SECRET_KEY;
    @Value("${jwt.expiration}")
    private long EXPIRATION;
    public String generateToken(String userName, Map<String,Object> extraClaims){
        String token= Jwts
                .builder()
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .claims(extraClaims)
                .subject(userName)
                .signWith(getSignKey(), SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    private Key getSignKey(){
        byte[] keyBytes= Decoders.BASE64URL.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    //Token Doğrulama Kontrolü
    private Claims getClaimsFromToken(String token){
        SecretKey Key=(SecretKey) getSignKey();
        return Jwts
                .parser()
                .verifyWith(Key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public Boolean validateToken(String token){return getClaimsFromToken(token).getExpiration().after(new Date());}

    public String extractUserName(String token){return getClaimsFromToken(token).getSubject();}

    public String extractUserRole(String token){return getClaimsFromToken(token).get("UserRole", String.class);}

    //public String extractUserId(String token){return getClaimsFromToken(token).get("UserId", String.class);}
}
