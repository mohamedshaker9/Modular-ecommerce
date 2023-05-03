package com.eg.swa.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartDto {

    private long id;
    private long customerId;
    private double total;
    private String status;
    private List<CartItemDto> items;
}
