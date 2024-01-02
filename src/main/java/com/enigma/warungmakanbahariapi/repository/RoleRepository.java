package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
}
