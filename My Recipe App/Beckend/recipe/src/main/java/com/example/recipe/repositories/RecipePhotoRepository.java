package com.example.recipe.repositories;

import com.example.recipe.entities.RecipePhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RecipePhotoRepository extends JpaRepository<RecipePhoto,Integer> {
}
