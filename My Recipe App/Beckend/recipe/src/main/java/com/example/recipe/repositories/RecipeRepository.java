package com.example.recipe.repositories;

import com.example.recipe.entities.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecipeRepository extends JpaRepository<Recipe,Integer> {
    int countByUserId(int userId);

    List<Recipe> findByUserId(int userId);

    @Query("SELECT r FROM Recipe r WHERE LOWER(r.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Recipe> searchRecipesByTitle(@Param("title") String title);
}
