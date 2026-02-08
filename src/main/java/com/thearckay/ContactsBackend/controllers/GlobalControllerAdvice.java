package com.thearckay.ContactsBackend.controllers;

import com.auth0.jwt.exceptions.TokenExpiredException;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.List;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(TokenExpiredException.class)
    public ResponseEntity<ResponseAPI> tokenExpired(TokenExpiredException e){
        ResponseAPI responseAPI = new ResponseAPI();
        List<ResponseAPI.Error> errorList = List.of(new ResponseAPI.Error("Token", "Token expirado"));
        responseAPI.error(HttpStatus.UNAUTHORIZED, errorList , "Token Expirado");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(responseAPI);
    }
}
