package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddUserPhotoResponse {
    private int id;
    private int userId;
    private String imageUrl;
    private String message;
    private boolean success;
}
