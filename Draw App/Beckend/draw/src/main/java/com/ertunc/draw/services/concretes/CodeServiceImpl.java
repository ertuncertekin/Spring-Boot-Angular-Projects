package com.ertunc.draw.services.concretes;

import com.ertunc.draw.entities.Code;
import com.ertunc.draw.repositories.CodeRepository;
import com.ertunc.draw.services.abstracts.CodeService;
import com.ertunc.draw.services.dtos.responses.ListAllCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CodeServiceImpl implements CodeService {
    private final CodeRepository codeRepository;
    @Override
    public Optional<Code> findByCode(String code) {
        return codeRepository.findByCode(code);
    }

    @Override
    public List<ListAllCodeResponse> getAllCodes() {
        return codeRepository.findAll()
                .stream()
                .map(code -> new ListAllCodeResponse(
                        code.getId(),
                        code.getCode()))
                .collect(Collectors.toList());
    }
}
