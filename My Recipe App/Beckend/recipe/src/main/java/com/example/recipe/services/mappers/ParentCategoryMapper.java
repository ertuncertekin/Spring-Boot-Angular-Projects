package com.example.recipe.services.mappers;

import com.example.recipe.entities.ParentCategory;
import com.example.recipe.services.dtos.requests.AddParentCategoryRequest;
import com.example.recipe.services.dtos.responses.AddParentCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ParentCategoryMapper {
    ParentCategoryMapper INSTANCE= Mappers.getMapper(ParentCategoryMapper.class);

    @Mapping(target = "mainCategory.id",source = "mainCategoryId")
    ParentCategory parentCategoryFromAddRequest(AddParentCategoryRequest addParentCategoryRequest);

    @Mapping(source = "mainCategory.id",target = "mainCategoryId")
    AddParentCategoryResponse toResponse(ParentCategory parentCategory);
}
