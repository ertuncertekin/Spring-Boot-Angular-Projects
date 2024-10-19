package com.example.recipe.services.abstracts;

import com.example.recipe.entities.User;
import com.example.recipe.services.dtos.requests.LoginRequest;
import com.example.recipe.services.dtos.requests.RegisterRequest;
import com.example.recipe.services.dtos.requests.UpdatePasswordRequest;
import com.example.recipe.services.dtos.requests.UpdateUserInfoRequest;
import com.example.recipe.services.dtos.responses.RegisterResponse;
import com.example.recipe.services.dtos.responses.UpdatePasswordResponse;
import com.example.recipe.services.dtos.responses.UpdateUserInfoResponse;

import java.util.Optional;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    String login(LoginRequest loginRequest);

    UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest);

    UpdateUserInfoResponse updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest);

    Optional<User> findById(int id);
}
