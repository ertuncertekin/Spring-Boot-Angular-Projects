package com.example.recipe.services.abstracts;

import com.example.recipe.services.dtos.requests.AddCommentRequest;
import com.example.recipe.services.dtos.requests.UpdateCommentRequest;
import com.example.recipe.services.dtos.responses.AddCommentResponse;
import com.example.recipe.services.dtos.responses.ListCommentResponse;
import com.example.recipe.services.dtos.responses.UpdateCommentResponse;

import java.util.List;

public interface CommentService {
    AddCommentResponse add(AddCommentRequest addCommentRequest);

    UpdateCommentResponse update(UpdateCommentRequest updateCommentRequest);

    void delete(int id);

    List<ListCommentResponse> getByRecipeId(int recipeId);

    List<ListCommentResponse> getByUserId(int userId);
}
