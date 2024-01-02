package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.Order;
import com.enigma.warungmakanbahariapi.model.request.OrderRequest;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<?> create(@RequestBody OrderRequest request){
        Order order = orderService.create(request);
        WebResponse<Order> response = WebResponse.<Order>builder()
                .status(HttpStatus.CREATED.getReasonPhrase())
                .message("successfuly create new order")
                .data(order)
                .build();
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
