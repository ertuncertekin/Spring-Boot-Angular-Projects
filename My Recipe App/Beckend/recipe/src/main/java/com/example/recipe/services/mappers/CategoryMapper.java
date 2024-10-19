package com.example.recipe.services.mappers;

import com.example.recipe.entities.Category;
import com.example.recipe.services.dtos.requests.AddCategoryRequest;
import com.example.recipe.services.dtos.responses.AddCategoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper INSTANCE= Mappers.getMapper(CategoryMapper.class);

    @Mapping(source = "parentCategoryId",target = "parentCategory.id")
    Category categoryFromAddRequest(AddCategoryRequest addCategoryRequest);

    @Mapping(target = "parentCategoryId",source = "parentCategory.id")
    AddCategoryResponse toResponse(Category category);

}
