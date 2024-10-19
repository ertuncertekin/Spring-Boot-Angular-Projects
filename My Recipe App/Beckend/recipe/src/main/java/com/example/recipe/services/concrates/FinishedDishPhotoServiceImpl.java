package com.example.recipe.services.concrates;

import com.example.recipe.core.services.FileStorageService;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.FinishedDishPhoto;
import com.example.recipe.entities.Recipe;
import com.example.recipe.repositories.FinishedDishPhotoRepository;
import com.example.recipe.services.abstracts.FinishedDishPhotoService;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.responses.AddFinishedDishPhotoResponse;
import com.example.recipe.services.mappers.FinishedDishPhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FinishedDishPhotoServiceImpl implements FinishedDishPhotoService {
    private final FinishedDishPhotoRepository finishedDishPhotoRepository;
    private final FileStorageService fileStorageService;
    private final RecipeService recipeService;
    @Override
    public AddFinishedDishPhotoResponse add(int recipeId, MultipartFile file) {
        Optional<FinishedDishPhoto> existingRecipeId=finishedDishPhotoRepository.findByRecipeId(recipeId);
        Optional<Recipe> optionalRecipe=recipeService.findById(recipeId);
        if(existingRecipeId.isPresent()){
            throw new BusinessException("Recipe already has a photo");
        } else if (optionalRecipe.isEmpty()) {
            throw new BusinessException("Recipe Not Found");
        }
        String fileName=fileStorageService.storeFile(file, FileStorageService.FileType.FINISHED_DISH_PHOTO);

        FinishedDishPhoto finishedDishPhoto=new FinishedDishPhoto();
        Recipe recipe=new Recipe();
        recipe.setId(recipeId);
        finishedDishPhoto.setRecipe(recipe);
        finishedDishPhoto.setImageUrl(fileName);

        FinishedDishPhoto savedPhoto=finishedDishPhotoRepository.save(finishedDishPhoto);
        AddFinishedDishPhotoResponse response= FinishedDishPhotoMapper.INSTANCE.toResponse(savedPhoto);
        response.setSuccess(true);
        response.setMessage("Photo successfully added");
        return response;
    }

    public void delete(int recipeId) {
        Optional<FinishedDishPhoto> existingPhoto = finishedDishPhotoRepository.findByRecipeId(recipeId);

        if (existingPhoto.isPresent()) {
            FinishedDishPhoto finishedDishPhoto = existingPhoto.get();
            String fileName = finishedDishPhoto.getImageUrl();

            finishedDishPhotoRepository.delete(finishedDishPhoto);
            fileStorageService.deleteFile(fileName, FileStorageService.FileType.FINISHED_DISH_PHOTO);
        } else {
            throw new BusinessException("Recipe photo not found");
        }
    }

//TODO:@ModelAttribute
}
