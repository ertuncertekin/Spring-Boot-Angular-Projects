package com.ertunc.draw.repositories;

import com.ertunc.draw.entities.DrawCode;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface DrawCodeRepository extends JpaRepository<DrawCode,Integer> {
    Optional<DrawCode> findByDrawCode(String code);
    List<DrawCode> findByUserId(int userId);
    Optional<DrawCode> findUserByDrawCode(String drawCode);
}
