package com.example.recipe.services.mappers;

import com.example.recipe.entities.Comment;
import com.example.recipe.services.dtos.requests.AddCommentRequest;
import com.example.recipe.services.dtos.requests.UpdateCommentRequest;
import com.example.recipe.services.dtos.responses.AddCommentResponse;
import com.example.recipe.services.dtos.responses.UpdateCommentResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE= Mappers.getMapper(CommentMapper.class);

    @Mapping(source = "userId",target = "user.id")
    @Mapping(source = "recipeId",target = "recipe.id")
    Comment commentFromAddRequest(AddCommentRequest request);

    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "recipeId",source = "recipe.id")
    AddCommentResponse toResponse(Comment comment);


    @Mapping(target = "userId",source = "user.id")
    @Mapping(target = "recipeId",source = "recipe.id")
    @Mapping(target="updatedAt",source = "createdAt")
    UpdateCommentResponse toUpdateResponse(Comment comment);

    void updateFromRequest(UpdateCommentRequest updateCommentRequest, @MappingTarget Comment comment);

}
