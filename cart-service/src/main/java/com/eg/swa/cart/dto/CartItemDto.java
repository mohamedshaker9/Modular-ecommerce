package com.eg.swa.cart.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CartItemDto {

    private Long id;
    private Long productId;
    private Integer quantity;
    private BigDecimal price;
}
