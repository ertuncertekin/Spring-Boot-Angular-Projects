package com.example.recipe.services.concrates;

import com.example.recipe.core.services.FileStorageService;
import com.example.recipe.core.utils.exceptions.BusinessException;
import com.example.recipe.entities.User;
import com.example.recipe.entities.UserPhoto;
import com.example.recipe.repositories.UserPhotoRepository;
import com.example.recipe.services.abstracts.AuthService;
import com.example.recipe.services.abstracts.UserPhotoService;
import com.example.recipe.services.dtos.responses.AddUserPhotoResponse;
import com.example.recipe.services.mappers.UserPhotoMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPhotoServiceImpl implements UserPhotoService {
    private final UserPhotoRepository userPhotoRepository;
    private final FileStorageService fileStorageService;
    private final AuthService authService;
    @Override
    public AddUserPhotoResponse add(int userId, MultipartFile file) {
        Optional<UserPhoto> existingPhoto = userPhotoRepository.findByUserId(userId);
        Optional<User> optionalUser=authService.findById(userId);

        if (existingPhoto.isPresent()) {
            throw new BusinessException("User already has a photo");
        } else if (optionalUser.isEmpty()) {
            throw new BusinessException("User Not Found");
        }

        String fileName = fileStorageService.storeFile(file, FileStorageService.FileType.USER_PHOTO);

        UserPhoto userPhoto = new UserPhoto();
        User user = new User();
        user.setId(userId);  // Setter kullanarak ID'yi ayarlıyoruz
        userPhoto.setUser(user);
        userPhoto.setImageUrl(fileName);

        UserPhoto savedUserPhoto = userPhotoRepository.save(userPhoto);
        AddUserPhotoResponse response = UserPhotoMapper.INSTANCE.toResponse(savedUserPhoto);
        response.setSuccess(true);
        response.setMessage("Photo successfully added");
        return response;
    }

    @Override
    public void delete(int userId) {
        Optional<UserPhoto> existingPhoto = userPhotoRepository.findByUserId(userId);

        if (existingPhoto.isPresent()) {
            UserPhoto userPhoto = existingPhoto.get();//
            String fileName = userPhoto.getImageUrl();//

            userPhotoRepository.delete(userPhoto);
            fileStorageService.deleteFile(fileName, FileStorageService.FileType.USER_PHOTO);
        } else {
            throw new BusinessException("User photo not found");
        }
    }

}
