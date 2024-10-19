package com.example.recipe.services.mappers;

import com.example.recipe.entities.UserPhoto;
import com.example.recipe.services.dtos.requests.AddUserPhoto;
import com.example.recipe.services.dtos.responses.AddUserPhotoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserPhotoMapper {
    UserPhotoMapper INSTANCE= Mappers.getMapper(UserPhotoMapper.class);

//    @Mapping(source = "userId",target = "user.id")
//    UserPhoto userPhotoFromAddRequest(AddUserPhoto addUserPhoto);

    @Mapping(target = "userId",source = "user.id")
    AddUserPhotoResponse toResponse(UserPhoto userPhoto);
}
