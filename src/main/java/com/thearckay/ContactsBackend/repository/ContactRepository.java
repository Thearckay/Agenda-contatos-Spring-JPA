package com.thearckay.ContactsBackend.repository;

import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ContactRepository extends CrudRepository<Contact, Long> {

    List<Contact> findByUserAndNameContainingIgnoreCaseOrUserAndEmailContainingIgnoreCaseOrUserAndOccupationContainingIgnoreCaseOrUserAndPhoneContainingIgnoreCase(
            User user1, String name,
            User user2, String email,
            User user3, String occupation,
            User user4, String phone
    );
}
