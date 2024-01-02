package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.DiningTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DiningTableRepository extends JpaRepository<DiningTable, String> {
    Optional<DiningTable> findByTableName(String tableName);
}
