package com.thearckay.ContactsBackend.entity;

import com.thearckay.ContactsBackend.dto.contact.NewContactDTO;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.SQLRestriction;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "contacts")
@SQLDelete(sql = "UPDATE contacts SET deleted = true WHERE contact_id = ?")
@SQLRestriction("deleted = false")
public class Contact {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "contact_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String name;

    private String email;
    @Column(nullable = false)
    private String phone;

    @Column(name = "work_phone")
    private String workPhone;

    @Column(name = "birth_date")
    private LocalDate birthdate;

    @Column(name = "zip_code")
    private String zipCode;

    private String occupation;
    @Column(columnDefinition = "boolean default false")
    private Boolean favorite = false;

    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "picture_url")
    private String pictureURL;

    @Column(columnDefinition = "boolean default false")
    private Boolean deleted = false;

    private String street;
    private String neighbourhood;
    private String complement;
    private String city;
    private String state;

    @Column(name = "house_number")
    private String houseNumber;
    private String note;

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public Contact() {}

    public Contact(User user, String name, String email, String phone, String workPhone, LocalDate birthdate, String zipCode, String occupation, Boolean favorite, LocalDateTime createdAt, String pictureURL, Boolean deleted, String street, String neighbourhood, String complement, String city, String state, String houseNumber) {
        this.user = user;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.workPhone = workPhone;
        this.birthdate = birthdate;
        this.zipCode = zipCode;
        this.occupation = occupation;
        this.favorite = favorite;
        this.createdAt = createdAt;
        this.pictureURL = pictureURL;
        this.deleted = deleted;
        this.street = street;
        this.neighbourhood = neighbourhood;
        this.complement = complement;
        this.city = city;
        this.state = state;
        this.houseNumber = houseNumber;
    }

    public Contact(NewContactDTO newContactDTO){
        this.name = newContactDTO.fullName();
        this.email = newContactDTO.email();
        this.phone = newContactDTO.phone();
        this.birthdate = newContactDTO.birthdate();
        this.occupation = newContactDTO.occupation();
        this.city = newContactDTO.occupation();
        this.neighbourhood = newContactDTO.neighborhood();
        this.state = newContactDTO.street();
        this.houseNumber = newContactDTO.streetNumber();
        this.note = newContactDTO.note();
        this.favorite = newContactDTO.favorited();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getWorkPhone() {
        return workPhone;
    }

    public void setWorkPhone(String workPhone) {
        this.workPhone = workPhone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

    public Boolean getFavorite() {
        return favorite;
    }

    public void setFavorite(Boolean favorite) {
        this.favorite = favorite;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getPictureURL() {
        return pictureURL;
    }

    public void setPictureURL(String pictureURL) {
        this.pictureURL = pictureURL;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getNeighbourhood() {
        return neighbourhood;
    }

    public void setNeighbourhood(String neighbourhood) {
        this.neighbourhood = neighbourhood;
    }

    public String getComplement() {
        return complement;
    }

    public void setComplement(String complement) {
        this.complement = complement;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }
}
