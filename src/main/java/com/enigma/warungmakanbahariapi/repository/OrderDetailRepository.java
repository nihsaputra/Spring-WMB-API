package com.enigma.warungmakanbahariapi.repository;

import com.enigma.warungmakanbahariapi.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, String> {

}
