package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.RecipeMaterialService;
import com.example.recipe.services.dtos.requests.AddRecipeMaterialRequest;
import com.example.recipe.services.dtos.responses.AddRecipeMaterialResponse;
import com.example.recipe.services.dtos.responses.ListRecipeMaterialsResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recipematerials")
@RequiredArgsConstructor
public class RecipeMaterialController {
    private final RecipeMaterialService recipeMaterialService;

    @PostMapping("addrecipematerials")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddRecipeMaterialResponse> add(@Valid @RequestBody List<AddRecipeMaterialRequest> requests) {
        return requests.stream()
                .map(recipeMaterialService::add)
                .collect(Collectors.toList());
    }

    @DeleteMapping("deleterecipematerial")
    public void delete(@RequestParam int id){
        recipeMaterialService.delete(id);
    }


    @GetMapping("recipe/{recipeId}")
    public List<ListRecipeMaterialsResponse> getRecipeMaterialsFromRecipeId(@PathVariable int recipeId){
        return recipeMaterialService.getRecipeMaterialsByRecipeId(recipeId);
    }
}
