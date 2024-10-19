package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.RecipePhotoService;
import com.example.recipe.services.dtos.responses.AddRecipePhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/recipephoto")
@RequiredArgsConstructor
public class RecipePhotoController {
    private final RecipePhotoService recipePhotoService;

    @PostMapping("/upload")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddRecipePhotoResponse> uploadRecipePhotos(@RequestParam("recipeId") int recipeId,
                                                           @RequestParam("files") List<MultipartFile> files) {
        return recipePhotoService.addMultiple(recipeId, files);
    }

    @DeleteMapping("deleterecipephoto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipePhoto(@PathVariable int recipePhotoId) {
        recipePhotoService.delete(recipePhotoId);
    }

}
