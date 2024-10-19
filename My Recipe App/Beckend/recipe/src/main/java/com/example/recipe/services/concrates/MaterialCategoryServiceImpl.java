package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.MaterialCategory;
import com.example.recipe.repositories.MaterialCategoryRepository;
import com.example.recipe.services.abstracts.MaterialCategoryService;
import com.example.recipe.services.dtos.requests.AddMaterialCategoryRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialCategoryRequest;
import com.example.recipe.services.dtos.responses.AddMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.ListMaterialCategoryResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialCategoryResponse;
import com.example.recipe.services.mappers.MaterialCategoryMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialCategoryServiceImpl implements MaterialCategoryService {
    private final MaterialCategoryRepository materialCategoryRepository;

    @Override
    public AddMaterialCategoryResponse add(AddMaterialCategoryRequest addMaterialCategoryRequest) {
        MaterialCategory materialCategory=new MaterialCategory();
        materialCategory.setName(addMaterialCategoryRequest.getName());
        MaterialCategory savedCategory=materialCategoryRepository.save(materialCategory);
        AddMaterialCategoryResponse response=new AddMaterialCategoryResponse();
        response.setId(materialCategory.getId());
        response.setName(materialCategory.getName());
        response.setMessage("Material Added Successfully");
        response.setSuccess(true);
        response.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        return response;
    }

    @Override
    public UpdateMaterialCategoryResponse update(UpdateMaterialCategoryRequest request) {
        Optional<MaterialCategory> optionalMaterialCategory=materialCategoryRepository.findById(request.getId());
        if(optionalMaterialCategory.isEmpty()){
            throw new BusinessException("Material Category Not Found");
        }
        MaterialCategory materialCategory=optionalMaterialCategory.get();
        MaterialCategoryMapper.INSTANCE.updateFromRequest(request,materialCategory);
        MaterialCategory savedMaterialCategory=materialCategoryRepository.save(materialCategory);
        UpdateMaterialCategoryResponse response=MaterialCategoryMapper.INSTANCE.toResponse(savedMaterialCategory);
        response.setMessage("Update Successful");
        response.setSuccess(true);
        response.setUpdatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        return response;
    }

    @Override
    public List<ListMaterialCategoryResponse> getAllMaterialCategories() {
        return materialCategoryRepository.findAll()
                .stream()
                .map(materialCategory -> new ListMaterialCategoryResponse(
                        materialCategory.getId(),
                        materialCategory.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<MaterialCategory> findById(int id) {
        return materialCategoryRepository.findById(id);
    }
}
