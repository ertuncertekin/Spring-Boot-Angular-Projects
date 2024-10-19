package com.example.recipe.services.mappers;

import com.example.recipe.entities.Material;
import com.example.recipe.services.dtos.requests.AddMaterialRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialRequest;
import com.example.recipe.services.dtos.responses.AddMaterialResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface MaterialMapper {
    MaterialMapper INSTANCE= Mappers.getMapper(MaterialMapper.class);

    @Mapping(source = "materialCategoryId",target = "materialCategory.id")
    Material addFromRequest(AddMaterialRequest addMaterialRequest);

    @Mapping(source = "materialCategory.id",target = "materialCategoryId")
    AddMaterialResponse toAddResponse(Material material);


    void updateFromRequest(UpdateMaterialRequest updateMaterialRequest, @MappingTarget Material material);


    UpdateMaterialResponse toUpdateResponse(Material material);
}
