package com.thearckay.ContactsBackend.repository;

import com.thearckay.ContactsBackend.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Long> {

    User findByEmailOrPhoneNumber(String email, String phoneNumber);
    User findByEmail(String email);
}
