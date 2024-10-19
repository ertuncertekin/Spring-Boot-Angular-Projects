package com.example.recipe.services.concrates;

import com.example.recipe.entities.MainCategory;
import com.example.recipe.repositories.MainCategoryRepository;
import com.example.recipe.services.abstracts.MainCategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MainCategoryServiceImpl implements MainCategoryService {
    private final MainCategoryRepository mainCategoryRepository;
    @Override
    public Optional<MainCategory> findById(int id) {
        return mainCategoryRepository.findById(id);
    }
}
