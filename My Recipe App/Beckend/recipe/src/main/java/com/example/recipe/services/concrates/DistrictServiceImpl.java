package com.example.recipe.services.concrates;

import com.example.recipe.entities.District;
import com.example.recipe.repositories.DistrictRepository;
import com.example.recipe.services.abstracts.DistrictService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DistrictServiceImpl implements DistrictService {
    private final DistrictRepository districtRepository;
    @Override
    public Optional<District> findById(int id) {
        return districtRepository.findById(id);
    }
}
