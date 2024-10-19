package com.example.recipe.controllers;

import com.example.recipe.repositories.MaterialCategoryRepository;
import com.example.recipe.services.abstracts.MaterialCategoryService;
import com.example.recipe.services.dtos.requests.AddMaterialCategoryRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialCategoryRequest;
import com.example.recipe.services.dtos.responses.AddMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.ListMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialCategoryResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/materialcategory")
@RequiredArgsConstructor
public class MaterialCategoryController {
    private final MaterialCategoryService materialCategoryService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddMaterialCategoryResponse> create(@Valid @RequestBody List<AddMaterialCategoryRequest> requests) {
        return requests.stream()
                .map(materialCategoryService::add)
                .collect(Collectors.toList());
    }
    @PutMapping("/update")
    public UpdateMaterialCategoryResponse update(@Valid @RequestBody UpdateMaterialCategoryRequest request){
        return materialCategoryService.update(request);
    }

    @GetMapping("/getallcategories")
    public List<ListMaterialCategoryResponse> getAll(){
        return materialCategoryService.getAllMaterialCategories();
    }
}
