package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.FinishedDishPhotoService;
import com.example.recipe.services.dtos.responses.AddFinishedDishPhotoResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/finisheddishphoto")
@RequiredArgsConstructor
public class FinishedDishPhotoController {
    private final FinishedDishPhotoService finishedDishPhotoService;

    @PostMapping("addphoto")
    @ResponseStatus(HttpStatus.CREATED)
    public AddFinishedDishPhotoResponse add(@RequestParam int recipeId, @RequestParam MultipartFile file){
        return finishedDishPhotoService.add(recipeId,file);
    }

    @DeleteMapping("deletephoto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteRecipePhoto(@RequestParam int recipeId) {
        finishedDishPhotoService.delete(recipeId);
    }

}
