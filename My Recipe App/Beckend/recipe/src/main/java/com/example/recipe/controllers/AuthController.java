
package com.example.recipe.controllers;

import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.dtos.requests.LoginRequest;
import com.example.recipe.services.dtos.requests.RegisterRequest;
import com.example.recipe.services.dtos.requests.UpdatePasswordRequest;
import com.example.recipe.services.dtos.requests.UpdateUserInfoRequest;
import com.example.recipe.services.dtos.responses.RegisterResponse;
import com.example.recipe.services.dtos.responses.UpdatePasswordResponse;
import com.example.recipe.services.dtos.responses.UpdateUserInfoResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("register")
    @ResponseStatus(HttpStatus.CREATED)
    public RegisterResponse register(@Valid @RequestBody RegisterRequest registerRequest){
        return authService.register(registerRequest);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.CREATED)
    public String login(@Valid @RequestBody LoginRequest request){
        return authService.login(request);
    }


    @PutMapping("changepassword")
    public UpdatePasswordResponse update(@Valid @RequestBody UpdatePasswordRequest updatePasswordRequest){
        return  authService.updatePassword(updatePasswordRequest);
    }

    @PutMapping("updateInfo")
    public UpdateUserInfoResponse update(@Valid @RequestBody UpdateUserInfoRequest updateUserInfoRequest){
        return  authService.updateUserInfo(updateUserInfoRequest);
    }

}
