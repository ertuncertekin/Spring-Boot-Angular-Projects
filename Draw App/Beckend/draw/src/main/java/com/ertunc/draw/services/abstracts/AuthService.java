package com.ertunc.draw.services.abstracts;

import com.ertunc.draw.entities.User;
import com.ertunc.draw.services.dtos.requests.RegisterRequest;
import com.ertunc.draw.services.dtos.responses.RegisterResponse;

import java.util.Optional;

public interface AuthService {
    RegisterResponse register(RegisterRequest registerRequest);

    Optional<User> findById(int id);
}
