package com.example.recipe.repositories;

import com.example.recipe.entities.FinishedDishPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FinishedDishPhotoRepository extends JpaRepository<FinishedDishPhoto,Integer> {
    Optional<FinishedDishPhoto> findByRecipeId(int recipeId);
}
