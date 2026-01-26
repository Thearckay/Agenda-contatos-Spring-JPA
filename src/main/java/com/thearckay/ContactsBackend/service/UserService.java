package com.thearckay.ContactsBackend.service;

import com.thearckay.ContactsBackend.exceptions.LoginErrorException;
import com.thearckay.ContactsBackend.exceptions.UserRegisterAlreadyExists;
import com.thearckay.ContactsBackend.dto.user.UserLoginDTO;
import com.thearckay.ContactsBackend.dto.user.UserLoginResponseDTO;
import com.thearckay.ContactsBackend.dto.user.UserRegisterDTO;
import com.thearckay.ContactsBackend.dto.user.UserResponseRegisterDTO;
import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public UserResponseRegisterDTO registerUser(UserRegisterDTO userRegisterDTO){
        System.out.println("service chamado - "+ userRegisterDTO.email());
        var userFromDataBase = repository.findByEmailOrPhoneNumber(userRegisterDTO.email(),userRegisterDTO.phoneNumber());
        if (userFromDataBase == null){
            User newUser = repository.save(userRegisterDTO.convertToUser());
            return UserResponseRegisterDTO.convertUserInDTO(newUser);
        } else {
            throw new UserRegisterAlreadyExists("As credenciais informadas já estão em uso!");
        }
    }

    public UserLoginResponseDTO loginUser(UserLoginDTO userToLogin){
        var userFromDataBase = repository.findByEmail(userToLogin.email());
        if (userFromDataBase == null){
            throw new LoginErrorException("Usuário inválido");
        }

        if (userFromDataBase.getEmail().equals(userToLogin.email())
            && userFromDataBase.getPassword().equals(userToLogin.password())){

            return new UserLoginResponseDTO(userFromDataBase);
        }

        throw new LoginErrorException("Dados inválidos");
    }
}
