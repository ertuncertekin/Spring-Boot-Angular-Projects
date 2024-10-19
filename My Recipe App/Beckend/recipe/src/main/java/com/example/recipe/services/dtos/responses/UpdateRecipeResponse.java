package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeResponse {
    private int id;
    private int userId;
    private int categoryId;
    private String title;
    private String description;
    private int preparationTime;
    private int servings;
    private int calorie;
    private double price;
    private boolean success;
    private String message;
}
