package com.example.recipe.repositories;

import com.example.recipe.entities.MainCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MainCategoryRepository extends JpaRepository<MainCategory,Integer> {
}
