package com.example.recipe.repositories;

import com.example.recipe.entities.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment,Integer> {
    Optional<Comment> findByUserIdAndRecipeId(int userId, int recipeId);

    List<Comment> findByRecipeId(int recipeId);

    List<Comment> findByUserId(int id);
}
