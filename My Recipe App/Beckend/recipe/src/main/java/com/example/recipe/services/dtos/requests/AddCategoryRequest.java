package com.example.recipe.services.dtos.requests;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryRequest{

    @NotBlank(message = "Category name cannot be null")
    @Size(min = 3, max = 25, message = "Category Name must be between 3 and 25 characters")
    private String name;

    @NotNull(message = "Parent Category ID cannot be null")
    private int parentCategoryId;
}
