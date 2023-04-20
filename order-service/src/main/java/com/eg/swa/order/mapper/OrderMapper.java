package com.eg.swa.order.mapper;


import com.eg.swa.order.dto.OrderDto;
import com.eg.swa.order.model.Order;
import org.mapstruct.*;
import org.springframework.core.annotation.OrderUtils;

import java.util.List;

@Mapper(componentModel = "spring",
        uses = OrderMapper.class)
public interface OrderMapper {

    @Mapping(source = "customerId", target = "customer.id")
    @Mapping(source = "status", target = "orderStatus")
    Order map(OrderDto orderDto);

    @InheritInverseConfiguration
    OrderDto map(Order order);

    List<OrderDto> map(List<Order> orders);


}