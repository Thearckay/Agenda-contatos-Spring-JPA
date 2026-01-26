package com.thearckay.ContactsBackend.exceptions;

public class LoginErrorException extends RuntimeException {
    public LoginErrorException(String message) {
        super(message);
    }
}
