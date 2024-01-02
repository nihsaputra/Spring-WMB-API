package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.constan.ERole;
import com.enigma.warungmakanbahariapi.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Optional<Role> findByRole(ERole role);

}
