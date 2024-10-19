package com.example.recipe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="finished_dish_photo")
public class FinishedDishPhoto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name="image_url")
    private String imageUrl;

    @OneToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

}
