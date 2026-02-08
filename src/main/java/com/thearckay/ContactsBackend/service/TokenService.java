package com.thearckay.ContactsBackend.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.thearckay.ContactsBackend.entity.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${token.secret.key}")
    private String secretKey;

    public String genereteToken(String email){
        try{
            System.out.println("Token gerado - para o email: "+email);
            Algorithm algorithm = Algorithm.HMAC256(secretKey);
            return JWT.create()
                    .withIssuer("contact-api")
                    .withSubject(email)
                    .withExpiresAt(timeExpiration())
                    .sign(algorithm);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private Instant timeExpiration() {
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String verifyToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("contact-api")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException e) {
            return "";
        }
    }

}
