package com.example.recipe.services.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddParentCategoryResponse {
    private int id;
    private int mainCategoryId;
    private String name;
    private String message;
    private boolean success;
}
