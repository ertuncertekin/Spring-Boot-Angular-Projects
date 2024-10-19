package com.ertunc.draw.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name="name")
    private String name;

    @Column(name="surname")
    private String surname;

    @Column(name="birthday")
    private LocalDate birthday;

    @Column(name="email",unique = true)
    private String email;

    @Column(name="password")
    private String password;

    @OneToMany(mappedBy = "user")
    private List<DrawCode> drawCodes;

}
