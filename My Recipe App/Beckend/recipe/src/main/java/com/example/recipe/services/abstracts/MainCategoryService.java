package com.example.recipe.services.abstracts;

import com.example.recipe.entities.MainCategory;

import java.util.Optional;

public interface MainCategoryService {
    Optional<MainCategory> findById(int id);
}
