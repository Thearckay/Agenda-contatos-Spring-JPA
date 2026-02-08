package com.thearckay.ContactsBackend.repository;

import com.thearckay.ContactsBackend.entity.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepository extends CrudRepository<Contact, Long> {


}
