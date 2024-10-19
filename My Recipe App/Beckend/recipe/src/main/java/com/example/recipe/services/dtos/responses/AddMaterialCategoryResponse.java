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
public class AddMaterialCategoryResponse {
    private int id;
    private String name;
    private String message;
    private boolean success;
    private LocalDateTime createdAt;
}
