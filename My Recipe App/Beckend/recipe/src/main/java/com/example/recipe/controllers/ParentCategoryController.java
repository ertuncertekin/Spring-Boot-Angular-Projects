package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.ParentCategoryService;
import com.example.recipe.services.dtos.requests.AddParentCategoryRequest;
import com.example.recipe.services.dtos.responses.AddParentCategoryResponse;
import com.example.recipe.services.dtos.responses.AddRecipeMaterialResponse;
import jakarta.persistence.Table;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/parentcategory")
@RequiredArgsConstructor
public class ParentCategoryController {
    private final ParentCategoryService parentCategoryService;

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public AddParentCategoryResponse create(@Valid @RequestBody AddParentCategoryRequest addParentCategoryRequest){
        return parentCategoryService.create(addParentCategoryRequest);
    }

}
