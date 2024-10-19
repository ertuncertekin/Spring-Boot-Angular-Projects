package com.example.recipe.repositories;

import com.example.recipe.entities.MaterialCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaterialCategoryRepository extends JpaRepository<MaterialCategory,Integer> {
}
