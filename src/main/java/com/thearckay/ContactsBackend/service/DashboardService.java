package com.thearckay.ContactsBackend.service;

import com.thearckay.ContactsBackend.dto.contact.ContactResponseDTO;
import com.thearckay.ContactsBackend.dto.dashboard.DashboardResponseDTO;
import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DashboardService {

    // todo - tem que fazer o rest controller advice do desse método
    @Transactional
    public ResponseEntity<ResponseAPI> getAllDashboardInformation(){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (user == null){
            throw new UsernameNotFoundException("Login inválido");
        }

        DashboardResponseDTO dashboardResponseDTO = new DashboardResponseDTO();
        dashboardResponseDTO.setUserName(user.getName());
        dashboardResponseDTO.setTotalContacts(user.getContactList().size());
        dashboardResponseDTO.setTotalFavorites(user.getContactList().stream().filter( c -> c.getFavorite()).toList().size());
        dashboardResponseDTO.setContactList(user.getContactList().stream().map(contact -> new ContactResponseDTO(contact)).toList());

        ResponseAPI<DashboardResponseDTO> responseAPI = new ResponseAPI();
        List<DashboardResponseDTO> responseDTOList = new ArrayList<>();

        responseDTOList.add(dashboardResponseDTO);
        responseAPI.success(responseDTOList, "Sucesso!");

        return ResponseEntity.ok().body(responseAPI);
    }
}
