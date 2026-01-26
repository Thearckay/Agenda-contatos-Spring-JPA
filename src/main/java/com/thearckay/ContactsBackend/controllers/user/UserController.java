package com.thearckay.ContactsBackend.controllers.user;

import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.dto.user.UserLoginDTO;
import com.thearckay.ContactsBackend.dto.user.UserLoginResponseDTO;
import com.thearckay.ContactsBackend.dto.user.UserRegisterDTO;
import com.thearckay.ContactsBackend.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping("/register")
    public ResponseEntity<ResponseAPI> register(@RequestBody @Valid UserRegisterDTO userRegisterDTO){
        System.out.println(userRegisterDTO.email());
        var resp = service.registerUser(userRegisterDTO);
        List<Object> list = new ArrayList<>();
        list.add(resp);
        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.success(list, "Registrado com sucesso!");
        return ResponseEntity.ok(responseAPI);
    }

    @PostMapping("/login")
    public ResponseEntity<ResponseAPI> loginUser(@RequestBody @Valid UserLoginDTO userLoginDTO){
        System.out.println("Email de login: "+userLoginDTO.email());
        System.out.println("Senha de login: "+userLoginDTO.password());

        UserLoginResponseDTO respLogin = service.loginUser(userLoginDTO);
        ResponseAPI responseAPI = new ResponseAPI();
        responseAPI.success(List.of(respLogin), "Logado com Sucesso!");
        return ResponseEntity.status(200).body(responseAPI);
    }
}
