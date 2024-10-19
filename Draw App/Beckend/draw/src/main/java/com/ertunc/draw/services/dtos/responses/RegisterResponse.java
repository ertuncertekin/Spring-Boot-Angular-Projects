package com.ertunc.draw.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterResponse {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private String email;
    private String password;
    private String message;
    private boolean success;
}
