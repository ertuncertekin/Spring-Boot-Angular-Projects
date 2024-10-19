package com.ertunc.draw.controllers;

import com.ertunc.draw.services.abstracts.CodeService;
import com.ertunc.draw.services.dtos.responses.ListAllCodeResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/code")
@RequiredArgsConstructor
public class CodeController {
    private final CodeService codeService;

    @GetMapping("/getallcodes")
    public List<ListAllCodeResponse> getAll(){
        return codeService.getAllCodes();
    }
}
