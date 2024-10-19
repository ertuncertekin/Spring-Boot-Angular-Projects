package com.example.recipe.services.abstracts;

import com.example.recipe.entities.FinishedDishPhoto;
import com.example.recipe.services.dtos.responses.AddFinishedDishPhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface FinishedDishPhotoService {
    AddFinishedDishPhotoResponse add(int recipeId, MultipartFile file);
    void delete(int recipeId);
}
