package com.eg.swa.order.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class OrderItemDto {
	private Long productId;
	private BigDecimal price;
    private Integer quantity;

}
