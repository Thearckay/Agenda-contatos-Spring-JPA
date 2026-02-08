package com.thearckay.ContactsBackend.dto.user;

import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;

import java.util.List;

public class UserLoginResponseDTO {

    private Long id;
    private String name;
    private String email;
    private String token;
    private List<Contact> contactList;

    public UserLoginResponseDTO(Long id, String name, String email, String token, List<Contact> contactList) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.token = token;
    }

    public UserLoginResponseDTO(User user, String token){
        this.id = user.getId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.token = token;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }

    public void addContactToList(Contact contactToAdd){
        this.contactList.add(contactToAdd);
    }
}
