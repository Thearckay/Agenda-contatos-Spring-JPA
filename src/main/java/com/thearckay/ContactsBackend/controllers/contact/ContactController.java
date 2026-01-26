package com.thearckay.ContactsBackend.controllers.contact;

import com.thearckay.ContactsBackend.dto.contact.NewContactDTO;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

@RestController
@RequestMapping("/users")
public class ContactController {

    //todo - terminar de fazer o controller

    @PostMapping("/{userId}/contacts")
    public ResponseEntity addContact(@RequestBody @Valid NewContactDTO newContact, @PathVariable(name = "userId") String userId){
        System.out.println("Nome Completo: "+newContact.fullName());
        System.out.println("Numero: "+newContact.phone());
        System.out.println("O id do usuário é: "+userId);
        return ResponseEntity.ok().build();
    }
}
