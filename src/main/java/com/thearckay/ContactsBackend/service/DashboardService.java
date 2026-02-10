package com.thearckay.ContactsBackend.service;

import com.thearckay.ContactsBackend.dto.contact.ContactResponseDTO;
import com.thearckay.ContactsBackend.dto.dashboard.DashboardResponseDTO;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.entity.Contact;
import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.exceptions.LoginErrorException;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    @Transactional
    public ResponseEntity<ResponseAPI> getAllDashboardInformation(){
        try {
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            if (user == null){
                throw new UsernameNotFoundException("Login inválido");
            }

            DashboardResponseDTO dashboardResponseDTO = new DashboardResponseDTO();
            dashboardResponseDTO.setUserName(user.getName());
            dashboardResponseDTO.setUserEmail(user.getEmail());
            dashboardResponseDTO.setTotalContacts(user.getContactList().size());
            dashboardResponseDTO.setTotalFavorites(user.getContactList().stream().filter( c -> c.getFavorite()).toList().size());
            dashboardResponseDTO.setContactList(user.getContactList().stream().map(contact -> new ContactResponseDTO(contact)).toList());
            dashboardResponseDTO.setNewContactsThisMonth(this.calcNewContactsInThisMonth(user.getContactList()));
            dashboardResponseDTO.setFavoritedContactList(
                    user.getContactList().stream().filter(contact -> contact.getFavorite())
                            .map(contact -> new ContactResponseDTO(contact)).toList());

            ResponseAPI<DashboardResponseDTO> responseAPI = new ResponseAPI();
            List<DashboardResponseDTO> responseDTOList = new ArrayList<>();

            responseDTOList.add(dashboardResponseDTO);
            responseAPI.success(responseDTOList, "Sucesso!");

            return ResponseEntity.ok().body(responseAPI);

        } catch (Exception e){
            throw new LoginErrorException(e.getMessage());
        }
    }

    private Integer calcNewContactsInThisMonth(List<Contact> contactList){
        Integer month = LocalDate.now().getMonthValue();
        Integer year = LocalDate.now().getYear();

        List<Contact> contactsOfThisMonth = contactList.stream().filter(contact ->
                contact.getCreatedAt().getYear() == year &&
                contact.getCreatedAt().getMonthValue() == month

        ).toList();

        return contactsOfThisMonth.size();
    }
}
