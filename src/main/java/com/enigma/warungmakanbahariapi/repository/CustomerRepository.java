package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, String> {
}
