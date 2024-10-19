package com.ertunc.draw.repositories;

import com.ertunc.draw.entities.Code;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CodeRepository extends JpaRepository<Code,Integer> {
    Optional<Code> findByCode(String code);
}
