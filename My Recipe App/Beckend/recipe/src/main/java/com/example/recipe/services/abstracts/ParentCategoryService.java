package com.example.recipe.services.abstracts;

import com.example.recipe.entities.ParentCategory;
import com.example.recipe.services.dtos.requests.AddParentCategoryRequest;
import com.example.recipe.services.dtos.responses.AddParentCategoryResponse;

import java.util.Optional;

public interface ParentCategoryService {
    AddParentCategoryResponse create(AddParentCategoryRequest addParentCategoryRequest);

    Optional<ParentCategory> findById(int id);
}
