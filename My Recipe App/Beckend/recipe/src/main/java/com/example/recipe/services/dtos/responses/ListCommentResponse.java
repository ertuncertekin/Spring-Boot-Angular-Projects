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
public class ListCommentResponse {
    private int id;
    private int recipeId;
    private String imageUrl;
    private String comment;
    private int rating;
    private LocalDateTime createdAt;
    private String userName;
    private String userSurname;
}
