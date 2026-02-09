package com.thearckay.ContactsBackend.dto.dashboard;

import com.thearckay.ContactsBackend.dto.contact.ContactResponseDTO;
import com.thearckay.ContactsBackend.entity.Contact;

import java.util.List;

public class DashboardResponseDTO {

    private String userName;
    private String userEmail;
    private Integer totalContacts;
    private Integer totalFavorites;
    // todo - preciso refinar a lógica dos novos contatos do Mês
    private Integer newContactsThisMonth;
    private List<ContactResponseDTO> contactList;
    private List<ContactResponseDTO> favoritedContactList;

    public List<ContactResponseDTO> getFavoritedContactList() {
        return favoritedContactList;
    }

    public void setFavoritedContactList(List<ContactResponseDTO> favoritedContactList) {
        this.favoritedContactList = favoritedContactList;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getTotalContacts() {
        return totalContacts;
    }

    public void setTotalContacts(Integer totalContacts) {
        this.totalContacts = totalContacts;
    }

    public Integer getTotalFavorites() {
        return totalFavorites;
    }

    public void setTotalFavorites(Integer totalFavorites) {
        this.totalFavorites = totalFavorites;
    }

    public Integer getNewContactsThisMonth() {
        return newContactsThisMonth;
    }

    public void setNewContactsThisMonth(Integer newContactsThisMonth) {
        this.newContactsThisMonth = newContactsThisMonth;
    }

    public List<ContactResponseDTO> getContactList() {
        return contactList;
    }

    public void setContactList(List<ContactResponseDTO> contactList) {
        this.contactList = contactList;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }
}
