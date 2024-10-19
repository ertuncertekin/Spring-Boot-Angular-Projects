package com.example.recipe.services.abstracts;

import com.example.recipe.entities.MaterialCategory;
import com.example.recipe.services.dtos.requests.AddMaterialCategoryRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialCategoryRequest;
import com.example.recipe.services.dtos.responses.AddMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.ListMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialCategoryResponse;

import java.util.List;
import java.util.Optional;

public interface MaterialCategoryService {
    AddMaterialCategoryResponse add(AddMaterialCategoryRequest addMaterialCategoryRequest);

    UpdateMaterialCategoryResponse update(UpdateMaterialCategoryRequest request);

    List<ListMaterialCategoryResponse> getAllMaterialCategories();

    Optional<MaterialCategory> findById(int id);
}
