package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCommentRequest {

    @NotNull(message = "User ID cannot be null")
    @Positive(message = "User ID must be a positive integer")
    private int userId;

    @NotNull(message = "Recipe ID cannot be null")
    @Positive(message = "Recipe ID must be a positive integer")
    private int recipeId;

    @NotBlank(message = "Comment text cannot be blank")
    private String comment;

    @Min(value = 1, message = "Rating must be at least 1")
    @Max(value = 5, message = "Rating must be at most 5")
    private int rating;

}
