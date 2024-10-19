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
public class UpdateMaterialResponse {
    private int id;
    private int materialCategoryId;
    private String name;
    private String message;
    private boolean success;
    private LocalDateTime updatedAt;
    private String categoryName;
}
