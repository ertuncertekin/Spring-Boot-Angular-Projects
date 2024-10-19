package com.ertunc.draw.services.abstracts;

import com.ertunc.draw.entities.Code;
import com.ertunc.draw.services.dtos.responses.ListAllCodeResponse;

import java.util.List;
import java.util.Optional;

public interface CodeService {
    Optional<Code> findByCode(String code);
    List<ListAllCodeResponse> getAllCodes();
}
