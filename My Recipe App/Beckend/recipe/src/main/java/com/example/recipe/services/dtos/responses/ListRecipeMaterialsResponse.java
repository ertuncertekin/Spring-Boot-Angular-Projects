package com.example.recipe.services.dtos.responses;

import com.example.recipe.entities.Unit;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ListRecipeMaterialsResponse {
    private int id;
    private int recipeId;
    private double quantity;
    private Unit unit;
    private String materialName;
}
