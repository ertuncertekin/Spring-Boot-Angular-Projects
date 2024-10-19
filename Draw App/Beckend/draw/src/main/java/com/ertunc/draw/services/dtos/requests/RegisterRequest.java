package com.ertunc.draw.services.dtos.requests;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;

    private String surname;

    private LocalDate birthday;

    private String email;

    private String password;
}
