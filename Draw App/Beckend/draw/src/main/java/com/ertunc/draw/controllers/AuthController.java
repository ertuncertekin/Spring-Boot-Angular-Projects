package com.ertunc.draw.controllers;

import com.ertunc.draw.services.abstracts.AuthService;
import com.ertunc.draw.services.dtos.requests.RegisterRequest;
import com.ertunc.draw.services.dtos.responses.RegisterResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    public RegisterResponse register(@RequestBody @Valid RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }
}
