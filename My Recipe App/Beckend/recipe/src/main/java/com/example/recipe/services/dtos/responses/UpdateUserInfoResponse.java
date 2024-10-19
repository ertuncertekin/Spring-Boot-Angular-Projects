package com.example.recipe.services.dtos.responses;

import com.example.recipe.entities.Gender;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UpdateUserInfoResponse {
    private int id;
    private String name;
    private String surname;
    private LocalDate birthday;
    private Gender gender;
    private String message;
    private boolean success;
}
