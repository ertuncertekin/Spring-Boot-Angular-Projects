package com.example.recipe.repositories;

import com.example.recipe.entities.RecipeMaterial;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecipeMaterialRepository extends JpaRepository<RecipeMaterial,Integer> {
    int countByRecipeId(int recipeId);

    List<RecipeMaterial> findByRecipeId(int recipeId);
}
