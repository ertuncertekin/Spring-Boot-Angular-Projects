package com.example.recipe.services.abstracts;

import com.example.recipe.entities.Material;
import com.example.recipe.services.dtos.requests.AddMaterialRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialRequest;
import com.example.recipe.services.dtos.responses.AddMaterialResponse;
import com.example.recipe.services.dtos.responses.ListMaterialResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialResponse;

import java.util.List;
import java.util.Optional;

public interface MaterialService {
    AddMaterialResponse add(AddMaterialRequest addMaterialRequest);
    Optional<Material> findById(int id);

    UpdateMaterialResponse update(UpdateMaterialRequest updateMaterialRequest);

    List<ListMaterialResponse> getByCategoryId(int categoryId);

    List<ListMaterialResponse> getAll();
}
