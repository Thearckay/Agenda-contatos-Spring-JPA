package com.thearckay.ContactsBackend.controllers.contact;

import com.thearckay.ContactsBackend.dto.contact.NewContactDTO;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.service.ContactService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:5173")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @PostMapping("/contacts")
    public ResponseEntity addContact(@RequestBody @Valid NewContactDTO newContact){
        contactService.salveContact(newContact);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/contacts")
    public ResponseEntity<ResponseAPI> getAllContacts(){
        return contactService.getAllContacts();
    }

    @GetMapping("/contacts/favorites")
    public ResponseEntity<ResponseAPI> getAllFavoriteContacts(){
        return contactService.getFavoriteContacts();
    }

    @GetMapping("/contacts/search")
    public ResponseEntity<ResponseAPI> searchContacts(@RequestParam(name = "q") String queryParam){
        System.out.println("A chave para a query é: "+queryParam);
        return contactService.searchContacts(queryParam);
    }

}
