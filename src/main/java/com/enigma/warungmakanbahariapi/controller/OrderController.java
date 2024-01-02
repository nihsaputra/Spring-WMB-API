package com.enigma.warungmakanbahariapi.controller;

import com.enigma.warungmakanbahariapi.entity.Order;
import com.enigma.warungmakanbahariapi.entity.OrderDetail;
import com.enigma.warungmakanbahariapi.model.request.OrderRequest;
import com.enigma.warungmakanbahariapi.model.response.WebResponse;
import com.enigma.warungmakanbahariapi.service.OrderDetailService;
import com.enigma.warungmakanbahariapi.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/api/orders")
public class OrderController {
    private final OrderService orderService;
    private final OrderDetailService orderDetailService;

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


    @GetMapping
    public ResponseEntity<?> getAll(){
        List<OrderDetail> findAll = orderDetailService.getAll();
        WebResponse<List<OrderDetail>> response = WebResponse.<List<OrderDetail>>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly create new order")
                .data(findAll)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<?> getById(@PathVariable String id){
        OrderDetail orderDetail = orderDetailService.getById(id);
        WebResponse<OrderDetail> response = WebResponse.<OrderDetail>builder()
                .status(HttpStatus.OK.getReasonPhrase())
                .message("successfuly get by id order")
                .data(orderDetail)
                .build();
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
