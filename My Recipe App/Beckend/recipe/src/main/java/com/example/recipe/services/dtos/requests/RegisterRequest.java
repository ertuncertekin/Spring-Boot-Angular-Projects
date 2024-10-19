package com.example.recipe.services.dtos.requests;

import com.example.recipe.entities.Gender;
import jakarta.validation.constraints.*;
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
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Email should be valid")
    private String email;
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,10}$",
            message = "Password must contain at least one digit, one uppercase letter, and be between 6 and 10 characters long"
    )
    private String password;
}
