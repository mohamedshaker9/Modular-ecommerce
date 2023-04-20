package com.eg.swa.order.mapper;


import com.eg.swa.order.dto.OrderItemDto;
import com.eg.swa.order.model.OrderItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {

    @Mapping(source = "productId", target = "product.id")
    OrderItem map(OrderItemDto orderItemDto);

    @InheritInverseConfiguration
    OrderItemDto map(OrderItem orderItem);

    List<OrderItem> map(List<OrderItemDto> orderItemDtos);


}
