package com.example.recipe.services.abstracts;

import com.example.recipe.services.dtos.requests.AddRecipeMaterialRequest;
import com.example.recipe.services.dtos.responses.AddRecipeMaterialResponse;
import com.example.recipe.services.dtos.responses.ListRecipeMaterialsResponse;

import java.util.List;

public interface RecipeMaterialService {
    AddRecipeMaterialResponse add(AddRecipeMaterialRequest addRecipeMaterialRequest);
    void delete(int id);

    List<ListRecipeMaterialsResponse> getRecipeMaterialsByRecipeId(int recipeId);
}
