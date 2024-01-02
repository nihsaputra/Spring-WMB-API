package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.TransType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TransTyperepository extends JpaRepository<TransType, String> {
    Optional<TransType> findByType(String Type);
}
