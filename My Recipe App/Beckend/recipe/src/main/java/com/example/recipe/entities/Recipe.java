package com.example.recipe.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="recipes")
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="category_id")
    private Category category;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="preparation_time")
    private int preparationTime;

    @Column(name="servings")
    private int servings;

    @Column(name="calorie")
    private int calorie;

    @Column(name="price")
    private double price;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RecipeMaterial> recipeMaterials;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<Comment> comments;

    @OneToOne(mappedBy = "recipe",fetch = FetchType.LAZY)
    private FinishedDishPhoto finishedDishPhoto;

    @OneToMany(mappedBy = "recipe",fetch = FetchType.LAZY)
    private List<RecipePhoto> recipePhotos;

}
