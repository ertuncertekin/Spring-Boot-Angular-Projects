package com.example.recipe.repositories;

import com.example.recipe.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AddressRepository extends JpaRepository<Address,Integer> {
    List<Address> findByUserId(int userId);
}
