package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddParentCategoryRequest {
    @NotNull(message = "Main Category ID cannot be null")
    private int mainCategoryId;

    @NotBlank(message = "Category name cannot be null")
    private String name;
}
