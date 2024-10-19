package com.example.recipe.services.mappers;

import com.example.recipe.entities.MaterialCategory;
import com.example.recipe.services.dtos.requests.UpdateMaterialCategoryRequest;
import com.example.recipe.services.dtos.responses.AddMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialCategoryMapper {
    MaterialCategoryMapper INSTANCE= Mappers.getMapper(MaterialCategoryMapper.class);

    void updateFromRequest(UpdateMaterialCategoryRequest request, @MappingTarget MaterialCategory materialCategory);

    UpdateMaterialCategoryResponse toResponse(MaterialCategory materialCategory);
}
