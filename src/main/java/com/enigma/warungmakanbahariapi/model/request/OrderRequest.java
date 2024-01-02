package com.enigma.warungmakanbahariapi.model.request;

import lombok.*;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class OrderRequest {

    private String customerId;
    private String tableName;
    private String transType;
    private List<OrderListRequest> orderList;

}
