package com.thearckay.ContactsBackend.service;

import com.thearckay.ContactsBackend.dto.contact.NewContactDTO;
import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.repository.ContactRepository;
import com.thearckay.ContactsBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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
}
