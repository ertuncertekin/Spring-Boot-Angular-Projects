package com.example.recipe.services.mappers;

import com.example.recipe.entities.RecipeMaterial;
import com.example.recipe.services.dtos.requests.AddRecipeMaterialRequest;
import com.example.recipe.services.dtos.responses.AddRecipeMaterialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMaterialMapper {
    RecipeMaterialMapper INSTANCE= Mappers.getMapper(RecipeMaterialMapper.class);

    @Mapping(target = "recipe.id",source = "recipeId")
    @Mapping(target = "material.id",source = "materialId")
    RecipeMaterial addRecipeMaterialFromAddRequest(AddRecipeMaterialRequest addRecipeMaterialRequest);

    @Mapping(target = "recipeId", source = "recipe.id")
    @Mapping(target = "materialId", source = "material.id")
    AddRecipeMaterialResponse toResponse(RecipeMaterial recipeMaterial);
}
