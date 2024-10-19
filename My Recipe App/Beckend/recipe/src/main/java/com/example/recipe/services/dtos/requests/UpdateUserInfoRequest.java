package com.example.recipe.services.dtos.requests;

import com.example.recipe.entities.Gender;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateUserInfoRequest {
    @NotNull(message = "Id Cannot Be Null")
    private int id;
    @NotBlank(message = "Name cannot be blank")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String name;
    @NotBlank(message = "Surname cannot be blank")
    @Size(min = 2, max = 50, message = "Surname must be between 2 and 50 characters")
    private String surname;
    @NotNull(message = "Birthday cannot be null")
    @Past(message = "Birthday must be a past date")
    private LocalDate birthday;
    @NotNull(message = "Gender cannot be null")
    private Gender gender;
}
