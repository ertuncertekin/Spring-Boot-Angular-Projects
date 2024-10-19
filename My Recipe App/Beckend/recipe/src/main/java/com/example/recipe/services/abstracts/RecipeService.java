package com.example.recipe.services.abstracts;

import com.example.recipe.entities.Recipe;
import com.example.recipe.services.dtos.requests.AddRecipeRequest;
import com.example.recipe.services.dtos.requests.UpdateRecipeRequest;
import com.example.recipe.services.dtos.responses.AddRecipeResponse;
import com.example.recipe.services.dtos.responses.ListRecipeResponse;
import com.example.recipe.services.dtos.responses.UpdateRecipeResponse;

import java.util.List;
import java.util.Optional;

public interface RecipeService {
    AddRecipeResponse add(AddRecipeRequest addRecipeRequest);

    UpdateRecipeResponse update(UpdateRecipeRequest updateRecipeRequest);

    void delete(int id);

    Optional<Recipe> findById(int id);

    List<ListRecipeResponse> getRecipesByUserId(int userId);

    List<ListRecipeResponse> getRecipesByTitle(String title);
}
