package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.UserPhotoService;
import com.example.recipe.services.dtos.requests.AddUserPhoto;
import com.example.recipe.services.dtos.requests.RegisterRequest;
import com.example.recipe.services.dtos.responses.AddUserPhotoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/userimage")
@RequiredArgsConstructor
public class UserPhotoController {
    private final UserPhotoService userPhotoService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public AddUserPhotoResponse uploadUserPhoto(@RequestParam("userId") int userId,
                                                @RequestParam("file") MultipartFile file) {
        return userPhotoService.add(userId, file);
    }

    @DeleteMapping("deleteuserphoto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserPhoto(@RequestParam int userId) {
        userPhotoService.delete(userId);
    }

}
