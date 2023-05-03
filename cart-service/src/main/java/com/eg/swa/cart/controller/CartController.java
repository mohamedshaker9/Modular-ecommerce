package com.eg.swa.cart.controller;

import com.eg.swa.cart.dto.CartDto;
import com.eg.swa.cart.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/cart")
public class CartController {

    private final CartService cartService;

    @PostMapping
    public ResponseEntity<Long> create(@RequestBody CartDto cartDto) {
        return new ResponseEntity<>(cartService.create(cartDto)
                , HttpStatus.CREATED);
    }
}
