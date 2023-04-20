package com.eg.swa.order.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderDto {
    private long customerId;
    private List<OrderItemDto> orderItems;
    private double total;
    private String status;

}
