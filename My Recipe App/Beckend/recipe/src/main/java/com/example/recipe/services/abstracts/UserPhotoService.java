package com.example.recipe.services.abstracts;

import com.example.recipe.entities.UserPhoto;
import com.example.recipe.services.dtos.requests.AddUserPhoto;
import com.example.recipe.services.dtos.responses.AddUserPhotoResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

public interface UserPhotoService {
    AddUserPhotoResponse add(int userId, MultipartFile file);

    void delete(int userId);
}