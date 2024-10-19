package com.example.recipe.controllers;

import com.example.recipe.entities.Recipe;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.requests.AddRecipeRequest;
import com.example.recipe.services.dtos.requests.UpdateRecipeRequest;
import com.example.recipe.services.dtos.responses.AddRecipeResponse;
import com.example.recipe.services.dtos.responses.ListRecipeResponse;
import com.example.recipe.services.dtos.responses.UpdateRecipeResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recipe")
@RequiredArgsConstructor
public class RecipeController {
    private final RecipeService recipeService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public AddRecipeResponse create(@Valid @RequestBody AddRecipeRequest addRecipeRequest){
        return recipeService.add(addRecipeRequest);
    }

    @PutMapping("update")
    @ResponseStatus(HttpStatus.CREATED)
    public UpdateRecipeResponse create(@Valid @RequestBody UpdateRecipeRequest updateRecipeRequest){
        return recipeService.update(updateRecipeRequest);
    }

    @DeleteMapping("delete")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void delete(@RequestParam int id){
        recipeService.delete(id);
    }

    @GetMapping("/user/{userId}")
    public List<ListRecipeResponse> getRecipesByUserId(@PathVariable int userId) {
        return recipeService.getRecipesByUserId(userId);
    }
    //TODO:YEMEKLERİ KALORİSİNE GÖRE LİSTELEME
    @GetMapping("/search")
    public List<ListRecipeResponse> getRecipesByTitle(@RequestParam String title){
        return recipeService.getRecipesByTitle(title);
    }
}
