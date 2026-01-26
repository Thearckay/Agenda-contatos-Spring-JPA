package com.thearckay.ContactsBackend.dto.user;

import com.thearckay.ContactsBackend.entity.User;

public class UserResponseRegisterDTO {

    private Long id;
    private String name;
    private String email;
    private String token;

    public static UserResponseRegisterDTO convertUserInDTO(User userDataBase){
        UserResponseRegisterDTO responseRegisterDTO = new UserResponseRegisterDTO();

        responseRegisterDTO.setId(userDataBase.getId());
        responseRegisterDTO.setName(userDataBase.getName());
        responseRegisterDTO.setEmail(userDataBase.getEmail());
        responseRegisterDTO.setToken("TokenTemporárioFake");

        return responseRegisterDTO;
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
}
