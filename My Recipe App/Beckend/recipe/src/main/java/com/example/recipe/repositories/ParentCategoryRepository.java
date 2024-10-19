package com.example.recipe.repositories;

import com.example.recipe.entities.ParentCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParentCategoryRepository extends JpaRepository<ParentCategory,Integer> {
}
