package com.thearckay.ContactsBackend.dto.contact;

import java.util.*;

public class ContactsListResponseDTO {
    public List<ContactResponseDTO> contactsList = new ArrayList<>();

    public List<ContactResponseDTO> getContactsList() {return contactsList;}
    public void setContactsList(List<ContactResponseDTO> contactsList) {this.contactsList = contactsList;}

}
