package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRecipeRequest {
    @NotNull(message = "ID cannot be null")
    @Positive(message = "ID must be a positive integer")
    private int id;

    @NotNull(message = "Category ID cannot be null")
    @Positive(message = "Category ID must be a positive integer")
    private int categoryId;

    @NotNull(message = "Title cannot be null")
    @NotEmpty(message = "Title cannot be empty")
    @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
    private String title;

    @NotNull(message = "Description cannot be null")
    @NotEmpty(message = "Description cannot be empty")
    @Size(min = 10, max = 1000, message = "Description must be between 10 and 1000 characters")
    private String description;

    @NotNull(message = "Preparation time cannot be null")
    @Positive(message = "Preparation time must be a positive integer")
    private int preparationTime;

    @NotNull(message = "Servings cannot be null")
    @Positive(message = "Servings must be a positive integer")
    private int servings;

    @NotNull(message = "Calorie cannot be null")
    @PositiveOrZero(message = "Calorie must be zero or a positive integer")
    private int calorie;

    @NotNull(message = "Price cannot be null")
    @PositiveOrZero(message = "Price must be zero or a positive number")
    private double price;
}
