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
@Table(name="materials")
public class Material {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private int id;

    @Column(name = "name",unique = true)
    private String name;

    @OneToMany(mappedBy = "material")
    private List<RecipeMaterial> recipeMaterials;

    @ManyToOne
    @JoinColumn(name = "material_category_id")
    private MaterialCategory materialCategory;

}
