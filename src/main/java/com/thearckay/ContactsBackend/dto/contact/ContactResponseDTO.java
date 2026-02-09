package com.thearckay.ContactsBackend.dto.contact;


import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;

import java.time.LocalDate;

public class ContactResponseDTO {
    private Long id;
    private String fullName;
    private String email;
    private String phone;
    private LocalDate birthdate;
    private String occupation;
    private String city;
    private String neighborhood;
    private String street;
    private String streetNumber;
    private String note;
    private Boolean favorite;

    public ContactResponseDTO(Contact c){
        setId(c.getId());
        setFullName(c.getName());
        setEmail(c.getEmail());
        setPhone(c.getPhone());
        setBirthdate(c.getBirthdate());
        setOccupation(c.getOccupation());
        setCity(c.getCity());
        setNeighborhood(c.getNeighbourhood());
        setStreet(c.getStreet());
        setStreetNumber(c.getHouseNumber());
        setNote(c.getNote());
        setFavorite(c.getFavorite());


    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getNeighborhood() {
        return neighborhood;
    }

    public void setNeighborhood(String neighborhood) {
        this.neighborhood = neighborhood;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
