package com.example.recipe.services.concrates;

import com.example.recipe.core.services.JwtService;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.Role;
import com.example.recipe.entities.User;
import com.example.recipe.repositories.UserRepository;
import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.dtos.requests.LoginRequest;
import com.example.recipe.services.dtos.requests.RegisterRequest;
import com.example.recipe.services.dtos.requests.UpdatePasswordRequest;
import com.example.recipe.services.dtos.requests.UpdateUserInfoRequest;
import com.example.recipe.services.dtos.responses.RegisterResponse;
import com.example.recipe.services.dtos.responses.UpdatePasswordResponse;
import com.example.recipe.services.dtos.responses.UpdateUserInfoResponse;
import com.example.recipe.services.mappers.AuthMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    @Override
    public RegisterResponse register(RegisterRequest registerRequest) {
        Optional<User> optionalUser=userRepository.findByEmail(registerRequest.getEmail());
        if(optionalUser.isPresent()){
            throw new BusinessException("This Email Has Already Taken");
        }

        User user= AuthMapper.INSTANCE.userFromRegisterRequest(registerRequest);
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setRole(Role.USER);
        User savedUser=userRepository.save(user);
        RegisterResponse response=AuthMapper.INSTANCE.toRegisterResponse(savedUser);
        response.setMessage("Register Successful");
        response.setSuccess(true);
        return response;
    }

    @Override
    public String login(LoginRequest loginRequest) {
        User user=userRepository.findByEmail(loginRequest.getEmail()).orElseThrow(() -> new BusinessException("Email or password incorrect!"));

       Authentication authentication=authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),loginRequest.getPassword()));
       if(!authentication.isAuthenticated()){
           throw new BusinessException("Email or password incorrect!");
       }
       Map<String,Object> extraClaims=new HashMap<>();
       extraClaims.put("UserId",user.getId());
       extraClaims.put("UserName",user.getName());
       extraClaims.put("UserRole",user.getRole().name());
        return jwtService.generateToken(user.getUsername(),extraClaims);
    }

    @Override
    public UpdatePasswordResponse updatePassword(UpdatePasswordRequest updatePasswordRequest) {
        Optional<User> optionalUser=userRepository.findById(updatePasswordRequest.getUserId());

        if(optionalUser.isEmpty()){
        throw new BusinessException("User Not Found");
        }

        User user=optionalUser.get();

        // Şifreyi hashleyerek kontrol et
        if (!passwordEncoder.matches(updatePasswordRequest.getPassword(), user.getPassword())) {
            throw new BusinessException("Current password is incorrect!");
        }
        // Yeni şifre mevcut şifreyle aynı mı kontrol et
        else if (passwordEncoder.matches(updatePasswordRequest.getNewPassword(), user.getPassword())) {
            throw new BusinessException("New password cannot be the same as the current password.");
        }

        user.setPassword(passwordEncoder.encode(updatePasswordRequest.getNewPassword()));
        User savedUser=userRepository.save(user);

        UpdatePasswordResponse response=AuthMapper.INSTANCE.toResponse(savedUser);
        response.setMessage("Password Change Successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public UpdateUserInfoResponse updateUserInfo(UpdateUserInfoRequest updateUserInfoRequest) {
        Optional<User> optionalUser=userRepository.findById(updateUserInfoRequest.getId());
        if (optionalUser.isEmpty()){
            throw new BusinessException("User Not Found");
        }
        User user=optionalUser.get();
        user.setName(updateUserInfoRequest.getName());
        user.setSurname(updateUserInfoRequest.getSurname());
        user.setBirthday(updateUserInfoRequest.getBirthday());
        user.setGender(updateUserInfoRequest.getGender());
        User savedUser=userRepository.save(user);
        UpdateUserInfoResponse response=AuthMapper.INSTANCE.toInfoResponse(savedUser);
        response.setMessage("Info Change Successfully");
        response.setSuccess(true);
        return response;
    }

    @Override
    public Optional<User> findById(int id) {
        return userRepository.findById(id);
    }

}
