package com.eg.swa.cart.mapper;

import com.eg.swa.cart.dto.CartDto;
import com.eg.swa.cart.model.Cart;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring",
    uses = CartItemMapper.class)
public interface CartMapper {

    @Mapping(source = "customerId", target = "customer.id")
    Cart map(CartDto cartDto);

    @InheritInverseConfiguration
    CartDto map(Cart cart);

    List<CartDto> map(List<Cart> cartList);
}
