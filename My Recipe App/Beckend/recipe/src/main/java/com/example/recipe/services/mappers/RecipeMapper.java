package com.example.recipe.services.mappers;

import com.example.recipe.entities.Recipe;
import com.example.recipe.services.dtos.requests.AddRecipeRequest;
import com.example.recipe.services.dtos.requests.UpdateRecipeRequest;
import com.example.recipe.services.dtos.responses.AddRecipeResponse;
import com.example.recipe.services.dtos.responses.UpdateRecipeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface RecipeMapper {
    RecipeMapper INSTANCE= Mappers.getMapper(RecipeMapper.class);


    @Mapping(target = "user.id",source = "userId")
    @Mapping(target = "category.id",source = "categoryId")
    Recipe addRecipeFromAddRequest(AddRecipeRequest addRecipeRequest);


    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    AddRecipeResponse toResponse(Recipe recipe);


    void updateRecipeFromUpdateRequest(UpdateRecipeRequest updateRecipeRequest, @MappingTarget Recipe recipe);

    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "categoryId", source = "category.id")
    UpdateRecipeResponse toUpdateResponse(Recipe recipe);
}
