package com.thearckay.ContactsBackend.controllers.dashboard;

import com.thearckay.ContactsBackend.dto.responseAPI.ResponseAPI;
import com.thearckay.ContactsBackend.entity.User;
import com.thearckay.ContactsBackend.service.DashboardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/app/dashboard")
public class DashboardController {

    @Autowired
    private DashboardService dashboardService;

    @GetMapping
    public ResponseEntity<ResponseAPI> getAllInformations(){
        System.out.println("dashboard chamou o controller");
        User userLoged = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println("O usuário do dashboard é: "+userLoged.getName());
        return dashboardService.getAllDashboardInformation();
    }

}
