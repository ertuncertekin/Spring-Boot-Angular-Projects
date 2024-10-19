package com.example.recipe.services.mappers;

import com.example.recipe.entities.RecipePhoto;
import com.example.recipe.services.dtos.responses.AddRecipePhotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipePhotoMapper {
    RecipePhotoMapper INSTANCE= Mappers.getMapper(RecipePhotoMapper.class);

    @Mapping(source = "recipe.id",target = "recipeId")
    AddRecipePhotoResponse toResponse(RecipePhoto recipePhoto);
}
