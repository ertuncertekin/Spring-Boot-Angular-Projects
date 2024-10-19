package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserPhoto {

    @NotNull(message = "User ID cannot be null")
    private int userId;

    @NotNull(message = "Image file cannot be null")
    private MultipartFile file;

}
