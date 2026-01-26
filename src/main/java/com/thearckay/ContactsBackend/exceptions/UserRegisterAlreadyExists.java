package com.thearckay.ContactsBackend.exceptions;

public class UserRegisterAlreadyExists extends RuntimeException {
    public UserRegisterAlreadyExists(String message) {
        super(message);
    }
}
