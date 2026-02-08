package com.thearckay.ContactsBackend.dto.dashboard;

import com.thearckay.ContactsBackend.entity.Contact;

import java.util.List;

public class DashboardResponseDTO {

    private String userName;
    private Integer totalContacts;
    private Integer totalFavorites;
    // todo - preciso refinar a lógica dos novos contatos do Mês
    private Integer newContactsThisMonth;
    private List<Contact> contactList;

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

    public List<Contact> getContactList() {
        return contactList;
    }

    public void setContactList(List<Contact> contactList) {
        this.contactList = contactList;
    }
}
