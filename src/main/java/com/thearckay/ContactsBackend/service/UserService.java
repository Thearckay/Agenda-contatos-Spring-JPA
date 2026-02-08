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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private TokenService tokenService;

    public UserResponseRegisterDTO registerUser(UserRegisterDTO userRegisterDTO){
        var userFromDataBase = repository.findByEmailOrPhoneNumber(userRegisterDTO.email(),userRegisterDTO.phoneNumber());
        if (userFromDataBase == null){
            User newUser = userRegisterDTO.convertToUser();
            newUser.setPassword(passwordEncoder.encode(userRegisterDTO.password()));
            repository.save(newUser);
            return UserResponseRegisterDTO.convertUserInDTO(newUser, tokenService.genereteToken(newUser.getEmail()));
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
            && passwordEncoder.matches(userToLogin.password(), userFromDataBase.getPassword())){
            String token = tokenService.genereteToken(userFromDataBase.getEmail());
            System.out.println("O token de login do guri é: "+token);
            return new UserLoginResponseDTO(userFromDataBase, token);
        }

        throw new LoginErrorException("Dados inválidos");
    }
}
