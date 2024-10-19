package com.example.recipe.services.abstracts;

import com.example.recipe.entities.District;

import java.util.Optional;

public interface DistrictService {
    Optional<District> findById(int id);
}
