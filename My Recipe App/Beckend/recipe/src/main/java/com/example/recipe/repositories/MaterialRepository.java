package com.example.recipe.repositories;

import com.example.recipe.entities.Material;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MaterialRepository extends JpaRepository<Material,Integer> {
    List<Material> findByMaterialCategoryId(int materialCategoryId);
}
