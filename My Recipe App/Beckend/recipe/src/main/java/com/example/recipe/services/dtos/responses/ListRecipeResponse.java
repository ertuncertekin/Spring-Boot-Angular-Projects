package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRecipeResponse {
    private int id;
    private String title;
    private String description;
    private int preparationTime;
    private int servings;
    private int calorie;
    private double price;
    private String categoryName;
    private String imageUrl;
}
