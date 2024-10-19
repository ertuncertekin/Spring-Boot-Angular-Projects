package com.example.recipe.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name="main_categories")
public class MainCategory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Enumerated(EnumType.STRING)
    private MainCategoryType mainCategoryType;

    @OneToMany(mappedBy = "mainCategory", fetch = FetchType.LAZY)
    private List<ParentCategory> parentCategories;

}
