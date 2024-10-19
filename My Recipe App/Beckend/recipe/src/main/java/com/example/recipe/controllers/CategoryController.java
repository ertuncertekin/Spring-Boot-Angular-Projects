package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.CategoryService;
import com.example.recipe.services.dtos.requests.AddCategoryRequest;
import com.example.recipe.services.dtos.responses.AddCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @PostMapping("createcategory")
    @ResponseStatus(HttpStatus.CREATED)
    public AddCategoryResponse create(@Valid @RequestBody AddCategoryRequest request){
        return categoryService.create(request);
    }
}
