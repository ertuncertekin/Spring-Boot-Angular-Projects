package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Material;
import com.example.recipe.entities.Recipe;
import com.example.recipe.entities.RecipeMaterial;
import com.example.recipe.repositories.RecipeMaterialRepository;
import com.example.recipe.services.abstracts.MaterialService;
import com.example.recipe.services.abstracts.RecipeMaterialService;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.requests.AddRecipeMaterialRequest;
import com.example.recipe.services.dtos.responses.AddRecipeMaterialResponse;
import com.example.recipe.services.dtos.responses.ListRecipeMaterialsResponse;
import com.example.recipe.services.mappers.RecipeMaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeMaterialImpl implements RecipeMaterialService {
    private final RecipeMaterialRepository recipeMaterialRepository;
    private final RecipeService recipeService;
    private final MaterialService materialService;

    @Override
    public AddRecipeMaterialResponse add(AddRecipeMaterialRequest addRecipeMaterialRequest) {
        Optional<Recipe> optionalRecipe=recipeService.findById(addRecipeMaterialRequest.getRecipeId());
        Optional<Material> optionalMaterial=materialService.findById(addRecipeMaterialRequest.getMaterialId());
        int countRecipeId=recipeMaterialRepository.countByRecipeId(addRecipeMaterialRequest.getRecipeId());
        if(optionalRecipe.isEmpty()){
            throw new BusinessException("Recipe Id Not Found");
        }
        else if(countRecipeId>=15){
            throw new BusinessException("A recipe can only have up to 15 materials.");
        }
        else if(optionalMaterial.isEmpty()){
            throw new BusinessException("Material Not Found");
        }
        RecipeMaterial recipeMaterial = RecipeMaterialMapper.INSTANCE.addRecipeMaterialFromAddRequest(addRecipeMaterialRequest);
        RecipeMaterial savedRecipeMaterial = recipeMaterialRepository.save(recipeMaterial);
        AddRecipeMaterialResponse response=RecipeMaterialMapper.INSTANCE.toResponse(savedRecipeMaterial);
        response.setSuccess(true);
        response.setMessage("Materials successfully added");
        return response;
    }

    @Override
    public void delete(int id) {
        Optional<RecipeMaterial> optionalRecipeMaterial=recipeMaterialRepository.findById(id);
        if(optionalRecipeMaterial.isEmpty()){
            throw new BusinessException("Recipe Material Not Found");
        }
        recipeMaterialRepository.deleteById(id);
    }

    @Override
    public List<ListRecipeMaterialsResponse> getRecipeMaterialsByRecipeId(int recipeId) {
        List<RecipeMaterial> recipeMaterials=recipeMaterialRepository.findByRecipeId(recipeId);
        if(recipeMaterials.isEmpty()){
            throw new BusinessException("No materials are included in this recipe");
        }
        return recipeMaterials.stream().map(this::convertToRecipeMaterialResponse).collect(Collectors.toList());
    }

    private ListRecipeMaterialsResponse convertToRecipeMaterialResponse(RecipeMaterial recipeMaterial){
        return new ListRecipeMaterialsResponse(
                recipeMaterial.getId(),
                recipeMaterial.getRecipe().getId(),
                recipeMaterial.getQuantity(),
                recipeMaterial.getUnit(),
                recipeMaterial.getMaterial().getName()
        );
    }
}
