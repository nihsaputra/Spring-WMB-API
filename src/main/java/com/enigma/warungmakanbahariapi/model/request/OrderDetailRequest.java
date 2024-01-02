package com.enigma.warungmakanbahariapi.model.request;

import com.enigma.warungmakanbahariapi.entity.Order;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Builder
@Getter
public class OrderDetailRequest {

    private Order orderId;
    private List<OrderListRequest> orderList;

}
