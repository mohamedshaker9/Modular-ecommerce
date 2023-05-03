package com.eg.swa.cart.service;

import com.eg.swa.cart.dto.CartDto;
import com.eg.swa.cart.mapper.CartMapper;
import com.eg.swa.cart.model.Cart;
import com.eg.swa.cart.repository.CartRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final CartMapper cartMapper;

    public long create(CartDto cartDto) {
        Cart cart = cartRepository.save(cartMapper.map(cartDto));
        return cart.getId();
    }

    public void update(CartDto cartDto) {
        cartRepository.save(cartMapper.map(cartDto));
    }


    public void delete(long id) {
        cartRepository.deleteById(id);
    }
}
