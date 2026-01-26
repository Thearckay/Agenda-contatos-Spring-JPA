package com.thearckay.ContactsBackend.dto.contact;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import java.time.LocalDate;

public record NewContactDTO(
    //todo - o contato tem que estar atrelado a um usuário - colocar um token ou algo assim
    @NotBlank
    String fullName,
    @Email(message = "E-mail inválido!")
    String email,
    @NotBlank
    @Pattern(
            regexp = "^(\\+\\d{1,3})?[\\s]?(\\(?\\d{2,3}\\)?)?[\\s]?\\d{8,9}$",
            message = "Formato de Telefone Inválido"
    )
    String phone,
    LocalDate birthdate,
    String occupation,
    String city,
    String street,
    String streetNumber,
    String neighborhood,
    String note,
    String token
){}
