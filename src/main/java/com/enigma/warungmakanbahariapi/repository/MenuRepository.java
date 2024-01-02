package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuRepository extends JpaRepository<Menu, String> {
}
