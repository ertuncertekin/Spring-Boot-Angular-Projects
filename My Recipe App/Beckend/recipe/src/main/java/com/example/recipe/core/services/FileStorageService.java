package com.example.recipe.core.services;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Objects;

@Service
public class FileStorageService {

    private final Path userPhotosLocation;
    private final Path finishedDishPhotosLocation;
    private final Path recipePhotosLocation;

    public enum FileType {
        USER_PHOTO,

        FINISHED_DISH_PHOTO,
        RECIPE_PHOTO
    }

    public FileStorageService(@Value("${file.upload-dir.user-photos}") String userPhotosDir,
                              @Value("${file.upload-dir.finished-dish-photos}") String finishedDishPhotosDir,
                              @Value("${file.upload-dir.recipe-photos}") String recipePhotosDir) {
        this.userPhotosLocation = Paths.get(userPhotosDir).toAbsolutePath().normalize();
        this.finishedDishPhotosLocation = Paths.get(finishedDishPhotosDir).toAbsolutePath().normalize();
        this.recipePhotosLocation = Paths.get(recipePhotosDir).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.userPhotosLocation);
            Files.createDirectories(this.finishedDishPhotosLocation);
            Files.createDirectories(this.recipePhotosLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directories where the uploaded files will be stored.", ex);
        }
    }

    public String storeFile(MultipartFile file, FileType fileType) {
        String fileName = StringUtils.cleanPath(Objects.requireNonNull(file.getOriginalFilename()));

        try {
            if (fileName.contains("..")) {
                throw new RuntimeException("Invalid file path sequence " + fileName);
            }

            Path targetLocation;
            switch (fileType) {
                case USER_PHOTO:
                    targetLocation = this.userPhotosLocation.resolve(fileName);
                    break;
                case FINISHED_DISH_PHOTO:
                    targetLocation = this.finishedDishPhotosLocation.resolve(fileName);
                    break;
                case RECIPE_PHOTO:
                    targetLocation = this.recipePhotosLocation.resolve(fileName);
                    break;
                default:
                    throw new RuntimeException("Unknown file type");
            }
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new RuntimeException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public void deleteFile(String fileName, FileType fileType) {
        try {
            Path filePath;
            switch (fileType) {
                case USER_PHOTO:
                    filePath = this.userPhotosLocation.resolve(fileName);
                    break;
                case FINISHED_DISH_PHOTO:
                    filePath = this.finishedDishPhotosLocation.resolve(fileName);
                    break;
                case RECIPE_PHOTO:
                    filePath = this.recipePhotosLocation.resolve(fileName);
                    break;
                default:
                    throw new RuntimeException("Unknown file type");
            }
            Files.deleteIfExists(filePath);
        } catch (IOException ex) {
            throw new RuntimeException("Could not delete file " + fileName + ". Please try again!", ex);
        }
    }
}
