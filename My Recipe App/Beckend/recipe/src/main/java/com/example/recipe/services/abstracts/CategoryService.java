package com.example.recipe.services.abstracts;

import com.example.recipe.entities.Category;
import com.example.recipe.services.dtos.requests.AddCategoryRequest;
import com.example.recipe.services.dtos.responses.AddCategoryResponse;

import java.util.Optional;

public interface CategoryService {
    AddCategoryResponse create(AddCategoryRequest addCategoryRequest);

    Optional<Category> findById(int id);
}
