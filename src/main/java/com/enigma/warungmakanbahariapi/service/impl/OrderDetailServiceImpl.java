package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.Menu;
import com.enigma.warungmakanbahariapi.entity.OrderDetail;
import com.enigma.warungmakanbahariapi.model.request.OrderDetailRequest;
import com.enigma.warungmakanbahariapi.model.request.OrderListRequest;
import com.enigma.warungmakanbahariapi.repository.OrderDetailRepository;
import com.enigma.warungmakanbahariapi.service.MenuService;
import com.enigma.warungmakanbahariapi.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;
    private final MenuService menuService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(OrderDetailRequest request) {
        for (OrderListRequest orderListRequest : request.getOrderList()) {
            Menu menu = menuService.getById(orderListRequest.getMenuId());
            OrderDetail buildOrderDetail = OrderDetail.builder()
                    .order(request.getOrderId())
                    .menu(menu)
                    .price(menu.getPrice())
                    .build();
            orderDetailRepository.save(buildOrderDetail);
        }
    }

    @Override
    public OrderDetail getById(String id) {
        Optional<OrderDetail> orderDetail = orderDetailRepository.findById(id);
        if (!orderDetail.isPresent()) throw new ResponseStatusException(HttpStatus.NOT_FOUND,"not found");

        return orderDetail.get();
    }

    @Override
    public List<OrderDetail> getAll() {
       return orderDetailRepository.findAll();
    }

}
