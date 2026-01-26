package com.thearckay.ContactsBackend.dto.user;

import com.thearckay.ContactsBackend.entity.User;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import java.time.LocalDate;

public record UserRegisterDTO(

        @NotBlank
        String fullName,

        @Email(message = "E-mail invalido")
        String email,

        @NotBlank
        @Pattern(
                regexp = "^(\\+\\d{1,3})?[\\s]?(\\(?\\d{2,3}\\)?)?[\\s]?\\d{8,9}$",
                message = "Formato de Telefone Inválido"
        )
        String phoneNumber,

        LocalDate birthDate,
        String occupation,

        @NotBlank
        @Pattern(
                regexp = "^(?=.*[A-Za-z])(?=.*\\d).{8,}$",
                message = "A senha deve ter no mínimo 8 caracteres, contendo letras e números."
        )
        String password
) {

    public User convertToUser(){
        System.out.println("Convertindo");
        User user = new User();
        user.setName(this.fullName);
        user.setEmail(this.email);
        user.setPhoneNumber(this.phoneNumber);
        user.setBirthdate(this.birthDate);
        user.setOccupation(this.occupation);
        user.setPassword(this.password);
        return user;
    }
}
