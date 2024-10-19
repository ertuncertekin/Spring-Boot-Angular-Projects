package com.example.recipe.services.concrates;

import com.example.recipe.core.services.FileStorageService;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Recipe;
import com.example.recipe.entities.RecipePhoto;
import com.example.recipe.repositories.RecipePhotoRepository;
import com.example.recipe.services.abstracts.RecipePhotoService;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.responses.AddRecipePhotoResponse;
import com.example.recipe.services.mappers.RecipePhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipePhotoServiceImpl implements RecipePhotoService {
    private final RecipePhotoRepository recipePhotoRepository;
    private final RecipeService recipeService;
    private final FileStorageService fileStorageService;
    @Override
    public AddRecipePhotoResponse add(int recipeId, MultipartFile file) {
    Optional<Recipe> optionalRecipe=recipeService.findById(recipeId);
    if (optionalRecipe.isEmpty()){
        throw new BusinessException("Recipe Not Found");
    }
    String fileName=fileStorageService.storeFile(file, FileStorageService.FileType.RECIPE_PHOTO);
        RecipePhoto recipePhoto = new RecipePhoto();
        Recipe recipe = new Recipe();
        recipe.setId(recipeId);
        recipePhoto.setRecipe(recipe);
        recipePhoto.setImageUrl(fileName);

        RecipePhoto savedRecipePhoto = recipePhotoRepository.save(recipePhoto);
        AddRecipePhotoResponse response = RecipePhotoMapper.INSTANCE.toResponse(savedRecipePhoto);
        response.setSuccess(true);
        response.setMessage("Photo successfully added");
        return response;
    }

    @Override
    public void delete(int recipePhotoId) {
        Optional<RecipePhoto> existingPhoto = recipePhotoRepository.findById(recipePhotoId);

        if (existingPhoto.isPresent()) {
            RecipePhoto recipePhoto = existingPhoto.get();
            String fileName = recipePhoto.getImageUrl();

            recipePhotoRepository.delete(recipePhoto);
            fileStorageService.deleteFile(fileName, FileStorageService.FileType.RECIPE_PHOTO);
        } else {
            throw new BusinessException("Recipe photo not found");
        }
    }

    @Override
    public List<AddRecipePhotoResponse> addMultiple(int recipeId, List<MultipartFile> files) {
        List<AddRecipePhotoResponse> responses = new ArrayList<>();
        for (MultipartFile file : files) {
            responses.add(add(recipeId, file));
        }
        return responses;
    }
}
