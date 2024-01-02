package com.enigma.warungmakanbahariapi.service;

import com.enigma.warungmakanbahariapi.entity.Order;
import com.enigma.warungmakanbahariapi.model.request.OrderRequest;

public interface OrderService {
    Order create(OrderRequest request);
}
