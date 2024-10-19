package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdatePasswordRequest {
    private int userId;

    private String password;

    @NotBlank(message = "Password cannot be blank")
    @Size(min = 6, max = 10, message = "Password must be between 6 and 10 characters")
    @Pattern(
            regexp = "^(?=.*[0-9])(?=.*[A-Z]).{6,10}$",
            message = "Password must contain at least one digit, one uppercase letter, and be between 6 and 10 characters long"
    )
    private String newPassword;
}
