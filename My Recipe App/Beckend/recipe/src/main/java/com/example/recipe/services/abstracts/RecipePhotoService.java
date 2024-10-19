package com.example.recipe.services.abstracts;

import com.example.recipe.services.dtos.responses.AddRecipePhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface RecipePhotoService {
    AddRecipePhotoResponse add(int recipeId, MultipartFile file);

    void delete(int recipePhotoId);
    List<AddRecipePhotoResponse> addMultiple(int recipeId, List<MultipartFile> files);
}
