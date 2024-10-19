package com.example.recipe.services.mappers;

import com.example.recipe.entities.FinishedDishPhoto;
import com.example.recipe.services.dtos.responses.AddFinishedDishPhotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface FinishedDishPhotoMapper {
    FinishedDishPhotoMapper INSTANCE= Mappers.getMapper(FinishedDishPhotoMapper.class);

    @Mapping(source = "recipe.id",target = "recipeId")
    AddFinishedDishPhotoResponse toResponse(FinishedDishPhoto finishedDishPhoto);
}
