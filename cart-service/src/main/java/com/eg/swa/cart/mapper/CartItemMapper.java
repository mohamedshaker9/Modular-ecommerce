package com.eg.swa.cart.mapper;

import com.eg.swa.cart.dto.CartItemDto;
import com.eg.swa.cart.model.CartItem;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CartItemMapper {

    @Mapping(source = "productId", target = "product.id")
    CartItem map(CartItemDto cartItemDto);

    @InheritInverseConfiguration
    CartItemDto map(CartItem cartItem);

    List<CartItemDto> map(List<CartItem> cartItemList);
}
