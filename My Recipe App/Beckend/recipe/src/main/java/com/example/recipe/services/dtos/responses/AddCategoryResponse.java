package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddCategoryResponse {
    private int id;
    private String name;
    private int parentCategoryId;
    private boolean success;
    private String message;
}
