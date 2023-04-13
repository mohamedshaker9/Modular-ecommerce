package com.eg.swa.order.dto;

import java.math.BigDecimal;

public class OrderItemDto {
	
	private Long productId;
	private BigDecimal price;
    private Integer quantity;
    
    
	public OrderItemDto(Long productId, BigDecimal price, Integer quantity) {
		super();
		this.productId = productId;
		this.price = price;
		this.quantity = quantity;
	}
	
	
	public Long getProductId() {
		return productId;
	}
	public void setProductId(Long productId) {
		this.productId = productId;
	}
	public BigDecimal getPrice() {
		return price;
	}
	public void setPrice(BigDecimal price) {
		this.price = price;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
    
    


}
