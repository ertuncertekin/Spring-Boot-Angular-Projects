package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddFinishedDishPhotoResponse {
    private int id;
    private int recipeId;
    private String imageUrl;
    private boolean success;
    private String message;
}
