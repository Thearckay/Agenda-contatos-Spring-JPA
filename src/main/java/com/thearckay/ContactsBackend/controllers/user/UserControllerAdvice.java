package com.thearckay.ContactsBackend.controllers.user;

import com.thearckay.ContactsBackend.exceptions.LoginErrorException;
import com.thearckay.ContactsBackend.exceptions.UserRegisterAlreadyExists;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestControllerAdvice
public class UserControllerAdvice {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> registerDataNotValid(MethodArgumentNotValidException e){
        BindingResult results = e.getBindingResult();
        List<ResponseAPI.Error> errorList = new ArrayList<>();
        results.getFieldErrors().forEach(fieldError -> {
            var err = new ResponseAPI.Error(
                    fieldError.getField(),
                    fieldError.getDefaultMessage()
            );
            errorList.add(err);
        });
        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.error("400", errorList, "Dados inválidos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseAPI);
    }

    @ExceptionHandler(UserRegisterAlreadyExists.class)
    public ResponseEntity<ResponseAPI> registerAlreadyExist(UserRegisterAlreadyExists e){
        ResponseAPI responseAPI = new ResponseAPI();
        List<ResponseAPI.Error> errors = Arrays.asList(
                new ResponseAPI.Error(
                        "Usuário já existe",
                        e.getMessage()
                )
        );
        responseAPI.error("409",errors, "Usuário já existe");

        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseAPI);
    }

    // todo- implementar erro de login
    @ExceptionHandler(LoginErrorException.class)
    public ResponseEntity<ResponseAPI> loginError(LoginErrorException e){
        ResponseAPI responseAPI = new ResponseAPI();
        ResponseAPI.Error error = new ResponseAPI.Error("Login", "Dados inválidos");
        responseAPI.error(HttpStatus.BAD_REQUEST, List.of(error), "Dados inválidos");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseAPI);
    }

}
