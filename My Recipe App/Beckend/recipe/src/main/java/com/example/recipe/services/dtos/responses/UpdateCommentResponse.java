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

public class UpdateCommentResponse {
    private int id;
    private int userId;
    private int recipeId;
    private String comment;
    private int rating;
    private LocalDateTime updatedAt;
    private boolean success;
    private String message;
}
