package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.Order;
import com.enigma.warungmakanbahariapi.entity.OrderDetail;
import com.enigma.warungmakanbahariapi.model.request.OrderDetailRequest;

import java.util.List;

public interface OrderDetailService {
    void create(OrderDetailRequest request);

    OrderDetail getById(String id);

    List<OrderDetail> getAll();
}
