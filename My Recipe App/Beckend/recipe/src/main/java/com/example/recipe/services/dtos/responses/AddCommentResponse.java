package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentResponse {

    private int id;
    private int userId;
    private int recipeId;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
    private boolean success;
    private String message;

}
