package com.thearckay.ContactsBackend.service;

import com.thearckay.ContactsBackend.dto.contact.ContactResponseDTO;
import com.thearckay.ContactsBackend.dto.contact.ContactsListResponseDTO;
import com.thearckay.ContactsBackend.dto.contact.NewContactDTO;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.exceptions.LoginErrorException;
import com.thearckay.ContactsBackend.repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    public void salveContact(NewContactDTO newContactDTO){
        try{
            User userFromRequest =
                    (User) SecurityContextHolder.getContext()
                            .getAuthentication()
                            .getPrincipal();

            Authentication auth = SecurityContextHolder.getContext().getAuthentication();

            if (auth == null || !(auth.getPrincipal() instanceof User userfromRequest)) {
                throw new UsernameNotFoundException("Usuário não autenticado");
            }

            Contact contactToSave = new Contact(newContactDTO);
            contactToSave.setUser(userFromRequest);
            contactRepository.save(contactToSave);
            System.out.println("Contato foi salvo, para o usuário: ");

        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }


    }

    public ResponseEntity<ResponseAPI> getAllContacts(){
        try {
            User userLogged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (userLogged == null){
                throw new LoginErrorException("");
            }
            List<ContactResponseDTO> contactsList = userLogged.getContactList().stream().map(contact -> new ContactResponseDTO(contact)).toList();
            ContactsListResponseDTO listResponseDTO = new ContactsListResponseDTO();
            listResponseDTO.setContactsList(contactsList);
            ResponseAPI responseAPI = new ResponseAPI();
            responseAPI.success(List.of(listResponseDTO), "Sucesso!");
            return ResponseEntity.ok().body(responseAPI);
        } catch (Exception e) {
            throw new LoginErrorException(e.getMessage());
        }
    }

    public ResponseEntity<ResponseAPI> getFavoriteContacts(){
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user == null){
                throw new LoginErrorException("Usuário inválido");
            }
            ResponseAPI responseAPI = new ResponseAPI();
            ContactsListResponseDTO contactsListResponseDTO = new ContactsListResponseDTO();
            contactsListResponseDTO.setContactsList(user.getContactList().stream()
                    .filter(contact -> contact.getFavorite())
                    .toList()
                    .stream().map(contact -> new ContactResponseDTO(contact))
                    .toList());
            responseAPI.success(List.of(contactsListResponseDTO), "Sucesso!");
            return ResponseEntity.ok().body(responseAPI);
        } catch (RuntimeException e){
            throw new RuntimeException(e);
        }
    }

    public ResponseEntity<ResponseAPI> searchContacts(String query){
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (user == null){
                throw new LoginErrorException("Usuário inválido");
            }
            List<Contact> contactList = contactRepository
                    .findByUserAndNameContainingIgnoreCaseOrUserAndEmailContainingIgnoreCaseOrUserAndOccupationContainingIgnoreCaseOrUserAndPhoneContainingIgnoreCase(
                            user, query,
                            user, query,
                            user, query,
                            user, query
                    );
            ResponseAPI responseAPI = new ResponseAPI();
            List<ContactResponseDTO> responseDTOList = contactList.stream().map(contact -> new ContactResponseDTO(contact)).toList();
            responseAPI.success(responseDTOList, "Sucesso!");
            return ResponseEntity.ok().body(responseAPI);
        } catch (Exception e) {
            throw new LoginErrorException(e.getMessage());
        }
    }

}
