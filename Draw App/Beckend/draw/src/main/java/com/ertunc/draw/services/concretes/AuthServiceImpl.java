package com.ertunc.draw.services.concretes;

import com.ertunc.draw.entities.User;
import com.ertunc.draw.repositories.UserRepository;
import com.ertunc.draw.services.abstracts.AuthService;
import com.ertunc.draw.services.dtos.requests.RegisterRequest;
import com.ertunc.draw.services.dtos.responses.RegisterResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Optional<User> optionalUser=userRepository.findByEmail(registerRequest.getEmail());
        if(optionalUser.isPresent()){
            throw new RuntimeException("This Email Has Already Taken");
        }
        User user=new User();
        user.setName(registerRequest.getName());
        user.setSurname(registerRequest.getSurname());
        user.setBirthday(registerRequest.getBirthday());
        user.setEmail(registerRequest.getEmail());
        user.setPassword(registerRequest.getPassword());
        User savedUser=userRepository.save(user);
        RegisterResponse response=new RegisterResponse();
        response.setId(user.getId());
        response.setName(user.getName());
        response.setSurname(user.getSurname());
        response.setBirthday(user.getBirthday());
        response.setEmail(user.getEmail());
        response.setPassword(user.getPassword());
        response.setMessage("Register Successful");
        response.setSuccess(true);
        return response;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }
}
