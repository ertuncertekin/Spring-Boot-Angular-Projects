package com.example.recipe.services.concrates;

import com.example.recipe.core.services.JwtService;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Category;
import com.example.recipe.entities.Recipe;
import com.example.recipe.entities.User;
import com.example.recipe.repositories.RecipeRepository;
import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.abstracts.CategoryService;
import com.example.recipe.services.abstracts.RecipeService;
import com.example.recipe.services.dtos.requests.AddRecipeRequest;
import com.example.recipe.services.dtos.requests.UpdateRecipeRequest;
import com.example.recipe.services.dtos.responses.AddRecipeResponse;
import com.example.recipe.services.dtos.responses.ListRecipeResponse;
import com.example.recipe.services.dtos.responses.UpdateRecipeResponse;
import com.example.recipe.services.mappers.RecipeMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipeServiceImpl implements RecipeService {
    private final RecipeRepository recipeRepository;
    private final AuthService authService;
    private final CategoryService categoryService;
    @Override
    public AddRecipeResponse add(AddRecipeRequest addRecipeRequest) {

        Optional<User> user=authService.findById(addRecipeRequest.getUserId());
        Optional<Category> category=categoryService.findById(addRecipeRequest.getCategoryId());
        int count= recipeRepository.countByUserId(addRecipeRequest.getUserId());

        if(user.isEmpty()){
            throw new BusinessException("User Not Found");
        }
        else if (category.isEmpty()){
            throw new BusinessException("Category Not Found");
        }
        else if(count>=15){
            throw new BusinessException("A user can only have up to 15 recipes.");
        }
        Recipe recipe= RecipeMapper.INSTANCE.addRecipeFromAddRequest(addRecipeRequest);
        recipe.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        Recipe savedRecipe=recipeRepository.save(recipe);
        AddRecipeResponse response=RecipeMapper.INSTANCE.toResponse(savedRecipe);
        response.setSuccess(true);
        response.setMessage("Recipe successfully added");
        return response;
    }

    @Override
    public UpdateRecipeResponse update(UpdateRecipeRequest updateRecipeRequest) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(updateRecipeRequest.getId());
        Optional<Category> optionalCategory=categoryService.findById(updateRecipeRequest.getCategoryId());


        if(optionalRecipe.isEmpty()){
            throw new BusinessException("Recipe Not Found");
        }
        else if(optionalCategory.isEmpty()){
            throw new BusinessException("Category Not Found");
        }

        Recipe recipe=optionalRecipe.get();
        Category category=optionalCategory.get();
        recipe.setCategory(category);
        RecipeMapper.INSTANCE.updateRecipeFromUpdateRequest(updateRecipeRequest,recipe);
        Recipe savedRecipe=recipeRepository.save(recipe);
        UpdateRecipeResponse response=RecipeMapper.INSTANCE.toUpdateResponse(savedRecipe);
        response.setSuccess(true);
        response.setMessage("Recipe successfully updated");
        return response;
    }

    @Override
    public void delete(int id) {
        Optional<Recipe> optionalRecipe=recipeRepository.findById(id);
        if (optionalRecipe.isEmpty()){
            throw new BusinessException("Recipe Not Found");
        }
        recipeRepository.deleteById(id);
    }

    @Override
    public Optional<Recipe> findById(int id) {
        return recipeRepository.findById(id);
    }

    @Override
    public List<ListRecipeResponse> getRecipesByUserId(int userId) {
        List<Recipe> recipes = recipeRepository.findByUserId(userId);
        return recipes.stream().map(this::convertToRecipeResponse).collect(Collectors.toList());
    }

    @Override
    public List<ListRecipeResponse> getRecipesByTitle(String title) {
        List<Recipe> recipes = recipeRepository.searchRecipesByTitle(title);
        return recipes.stream().map(this::convertToRecipeResponse).collect(Collectors.toList());
    }

    private ListRecipeResponse convertToRecipeResponse(Recipe recipe) {
        return new ListRecipeResponse(
                recipe.getId(),
                recipe.getTitle(),
                recipe.getDescription(),
                recipe.getPreparationTime(),
                recipe.getServings(),
                recipe.getCalorie(),
                recipe.getPrice(),
                recipe.getCategory() != null ? recipe.getCategory().getName() : null,
                recipe.getFinishedDishPhoto() !=null ? recipe.getFinishedDishPhoto().getImageUrl():null
        );
    }
}
