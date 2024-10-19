package com.example.recipe.services.concrates;

import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Material;
import com.example.recipe.entities.MaterialCategory;
import com.example.recipe.repositories.MaterialRepository;
import com.example.recipe.services.abstracts.MaterialCategoryService;
import com.example.recipe.services.abstracts.MaterialService;
import com.example.recipe.services.dtos.requests.AddMaterialRequest;
import com.example.recipe.services.dtos.requests.UpdateMaterialRequest;
import com.example.recipe.services.dtos.responses.AddMaterialResponse;
import com.example.recipe.services.dtos.responses.ListMaterialResponse;
import com.example.recipe.services.dtos.responses.UpdateMaterialResponse;
import com.example.recipe.services.mappers.MaterialMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final MaterialCategoryService materialCategoryService;

    @Override
    public AddMaterialResponse add(AddMaterialRequest addMaterialRequest) {
        Optional< MaterialCategory> optionalMaterialCategory=materialCategoryService.findById(addMaterialRequest.getMaterialCategoryId());
        if(optionalMaterialCategory.isEmpty()){
            throw new BusinessException("Material Category Not Found");
        }
        Material material= MaterialMapper.INSTANCE.addFromRequest(addMaterialRequest);
        Material savedMaterial=materialRepository.save(material);
        AddMaterialResponse response=MaterialMapper.INSTANCE.toAddResponse(savedMaterial);
        response.setMessage("Material Added Successful");
        response.setSuccess(true);
        response.setCreatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        return response;
    }

    @Override
    public Optional<Material> findById(int id) {
        return materialRepository.findById(id);
    }

    @Override
    public UpdateMaterialResponse update(UpdateMaterialRequest updateMaterialRequest) {
        Optional<MaterialCategory> optionalMaterialCategory=materialCategoryService.findById(updateMaterialRequest.getMaterialCategoryId());
        Optional<Material> optionalMaterial=materialRepository.findById(updateMaterialRequest.getId());
        if(optionalMaterial.isEmpty()){
            throw new BusinessException("Material Not Found");
        }
        if(optionalMaterialCategory.isEmpty()){
            throw new BusinessException("Material Category Not Found");
        }
        Material material=optionalMaterial.get();
        MaterialCategory materialCategory=optionalMaterialCategory.get();
        material.setMaterialCategory(materialCategory);
        MaterialMapper.INSTANCE.updateFromRequest(updateMaterialRequest,material);
        Material savedMaterial=materialRepository.save(material);
        UpdateMaterialResponse response=MaterialMapper.INSTANCE.toUpdateResponse(savedMaterial);
        response.setMaterialCategoryId(materialCategory.getId());
        response.setCategoryName(materialCategory.getName());
        response.setMessage("Material Update Successful");
        response.setSuccess(true);
        response.setUpdatedAt(LocalDateTime.now().withSecond(0).withNano(0));
        return response;
    }

    @Override
    public List<ListMaterialResponse> getByCategoryId(int categoryId) {
        List<Material> materials=materialRepository.findByMaterialCategoryId(categoryId);
        if(materials.isEmpty()){
            throw new BusinessException("Material Not Found");
        }
        return materials
                .stream()
                .map(material -> new ListMaterialResponse(
                    material.getId(),
                    material.getMaterialCategory().getId(),
                    material.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public List<ListMaterialResponse> getAll() {
        return materialRepository.findAll()
                .stream()
                .map(material -> new ListMaterialResponse(
                        material.getId(),
                        material.getMaterialCategory() !=null ? material.getMaterialCategory().getId():0,
                        material.getName()))
                .collect(Collectors.toList());
    }
}
