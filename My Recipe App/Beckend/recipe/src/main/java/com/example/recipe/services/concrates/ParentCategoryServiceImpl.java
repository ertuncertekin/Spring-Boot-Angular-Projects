package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.MainCategory;
import com.example.recipe.entities.ParentCategory;
import com.example.recipe.repositories.ParentCategoryRepository;
import com.example.recipe.services.abstracts.MainCategoryService;
import com.example.recipe.services.abstracts.ParentCategoryService;
import com.example.recipe.services.dtos.requests.AddParentCategoryRequest;
import com.example.recipe.services.dtos.responses.AddParentCategoryResponse;
import com.example.recipe.services.mappers.ParentCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ParentCategoryServiceImpl implements ParentCategoryService {
    private final ParentCategoryRepository parentCategoryRepository;
    private final MainCategoryService mainCategoryService;
    @Override
    public AddParentCategoryResponse create(AddParentCategoryRequest addParentCategoryRequest) {
        Optional<MainCategory> mainCategory=mainCategoryService.findById(addParentCategoryRequest.getMainCategoryId());

        if (mainCategory.isPresent()){
            ParentCategory parentCategory= ParentCategoryMapper.INSTANCE.parentCategoryFromAddRequest(addParentCategoryRequest);
            ParentCategory savedParentCategory=parentCategoryRepository.save(parentCategory);
            AddParentCategoryResponse response=ParentCategoryMapper.INSTANCE.toResponse(savedParentCategory);
            response.setSuccess(true);
            response.setMessage("Parent Category added successfully");
            return response;
        }
        else{
            throw new BusinessException("Main Category Not Found");
        }
    }

    @Override
    public Optional<ParentCategory> findById(int id) {
        return parentCategoryRepository.findById(id);
    }
}
