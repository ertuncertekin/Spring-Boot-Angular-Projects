package com.example.recipe.services.mappers;

import com.example.recipe.entities.User;
import com.example.recipe.services.dtos.requests.RegisterRequest;
import com.example.recipe.services.dtos.responses.RegisterResponse;
import com.example.recipe.services.dtos.responses.UpdatePasswordResponse;
import com.example.recipe.services.dtos.responses.UpdateUserInfoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface AuthMapper {

    AuthMapper INSTANCE= Mappers.getMapper(AuthMapper.class);

    @Mapping(target="password", ignore = true)
    User userFromRegisterRequest(RegisterRequest registerRequest);

    RegisterResponse toRegisterResponse(User user);

    @Mapping(source = "user.id",target = "userId")
    UpdatePasswordResponse toResponse(User user);

    UpdateUserInfoResponse toInfoResponse(User user);

}
