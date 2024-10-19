package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.MaterialService;
import com.example.recipe.services.dtos.requests.AddMaterialRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialRequest;
import com.example.recipe.services.dtos.responses.AddMaterialResponse;
import com.example.recipe.services.dtos.responses.ListMaterialResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/material")
@RequiredArgsConstructor
public class MaterialController {
    private final MaterialService materialService;

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public List<AddMaterialResponse> add(@Valid @RequestBody List<AddMaterialRequest> requests){
        return requests
                .stream()
                .map(materialService::add)
                .collect(Collectors.toList());
    }

    @PutMapping("/update")
    public UpdateMaterialResponse update(@Valid @RequestBody UpdateMaterialRequest request){
        return materialService.update(request);
    }

    @GetMapping("/getbycategory/{categoryId}")
    public List<ListMaterialResponse> getFromCategoryId(@PathVariable int categoryId){
        return materialService.getByCategoryId(categoryId);
    }

    @GetMapping("/getall")
    public List<ListMaterialResponse> getAll(){
        return materialService.getAll();
    }
}
