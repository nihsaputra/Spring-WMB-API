package com.enigma.warungmakanbahariapi.service.impl;

import com.enigma.warungmakanbahariapi.entity.*;
import com.enigma.warungmakanbahariapi.model.request.DiningTableRequest;
import com.enigma.warungmakanbahariapi.model.request.OrderDetailRequest;
import com.enigma.warungmakanbahariapi.model.request.OrderRequest;
import com.enigma.warungmakanbahariapi.repository.OrderRepository;
import com.enigma.warungmakanbahariapi.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final CustomerService customerService;
    private final DiningTableService diningTableService;
    private final TransTypeService transTypeService;
    private final OrderDetailService orderDetailService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Order create(OrderRequest request) {

        // customerId
        Customer customer = customerService.getById(request.getCustomerId());

        // tableId
        DiningTableRequest diningTableRequest = DiningTableRequest.builder()
                .tableName(request.getTableName())
                .build();
        DiningTable diningTable = diningTableService.getOrSave(diningTableRequest);

//        // transType
//        TransType transType = transTypeService.getOrSave(request.getTransType());

        // Save Order
        Order buildOrder = Order.builder()
                .customer(customer)
                .diningTable(diningTable)
//                .transType(transType)
                .transDate(new Date())
                .build();
        Order order = orderRepository.saveAndFlush(buildOrder);


//        // Kirim OrderDetail
//        OrderDetailRequest orderDetailRequest = OrderDetailRequest.builder()
//                .orderId(order)
//                .orderList(request.getOrderList())
//                .build();
//        orderDetailService.create(orderDetailRequest);


        return order;
    }
}
