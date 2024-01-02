package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.Menu;
import com.enigma.warungmakanbahariapi.entity.OrderDetail;
import com.enigma.warungmakanbahariapi.model.request.OrderDetailRequest;
import com.enigma.warungmakanbahariapi.model.request.OrderListRequest;
import com.enigma.warungmakanbahariapi.repository.OrderDetailRepository;
import com.enigma.warungmakanbahariapi.service.MenuService;
import com.enigma.warungmakanbahariapi.service.OrderDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

}
