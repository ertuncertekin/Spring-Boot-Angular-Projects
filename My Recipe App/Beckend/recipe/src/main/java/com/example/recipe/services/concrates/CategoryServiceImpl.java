package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Category;
import com.example.recipe.entities.ParentCategory;
import com.example.recipe.repositories.CategoryRepository;
import com.example.recipe.services.abstracts.CategoryService;
import com.example.recipe.services.abstracts.ParentCategoryService;
import com.example.recipe.services.dtos.requests.AddCategoryRequest;
import com.example.recipe.services.dtos.responses.AddCategoryResponse;
import com.example.recipe.services.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;
    private final ParentCategoryService parentCategoryService;
    @Override
    public AddCategoryResponse create(AddCategoryRequest addCategoryRequest) {
        Optional<ParentCategory> parentCategory=parentCategoryService.findById(addCategoryRequest.getParentCategoryId());

        if(parentCategory.isPresent()){
            Category category= CategoryMapper.INSTANCE.categoryFromAddRequest(addCategoryRequest);
            Category savedCategory=categoryRepository.save(category);
            AddCategoryResponse response=CategoryMapper.INSTANCE.toResponse(savedCategory);
            response.setMessage("Category added successfully");
            response.setSuccess(true);
            return response;
        }
        else{
            throw new BusinessException("Parent Category Not Found");
        }
    }

    @Override
    public Optional<Category> findById(int id) {
        return categoryRepository.findById(id);
    }
}
