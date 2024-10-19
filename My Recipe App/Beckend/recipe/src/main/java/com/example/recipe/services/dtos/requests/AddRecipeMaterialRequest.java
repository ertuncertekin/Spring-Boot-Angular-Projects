package com.example.recipe.services.dtos.requests;

import com.example.recipe.entities.Unit;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddRecipeMaterialRequest {
    @NotNull(message = "Recipe ID cannot be null")
    @Positive(message = "Recipe ID must be a positive integer")
    private int recipeId;

    @NotNull(message = "Material ID cannot be null")
    @Positive(message = "Material ID must be a positive integer")
    private int materialId;

    @NotNull(message = "Quantity cannot be null")
    @Positive(message = "Quantity must be a positive number")
    private double quantity;

    @NotNull(message = "Unit cannot be null")
    private Unit unit;
}
