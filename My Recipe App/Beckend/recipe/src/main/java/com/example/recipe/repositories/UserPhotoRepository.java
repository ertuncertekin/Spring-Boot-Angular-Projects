package com.example.recipe.repositories;

import com.example.recipe.entities.UserPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserPhotoRepository extends JpaRepository<UserPhoto,Integer> {
    Optional<UserPhoto> findByUserId(int userId);
}
