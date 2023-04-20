package com.eg.swa.order.controller;

import java.util.List;

import com.eg.swa.order.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.eg.swa.order.service.OrderService;

@RequiredArgsConstructor

@RestController
@RequestMapping("/api/order")
public class OrderController {
    private final OrderService orderService;

    @GetMapping
    public ResponseEntity<List<OrderDto>> get() {
        return ResponseEntity.ok(orderService.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDto> get(@PathVariable Long id) throws NotFoundException {
        return ResponseEntity.ok(orderService.get(id));
    }

    @PostMapping
    public ResponseEntity<OrderDto> create(@RequestBody OrderDto orderDto) throws Exception {
    //        Customer customer = (Customer) authentication.getPrincipal();
//
        return new ResponseEntity<>(orderService.create(orderDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Boolean> cancel(@PathVariable("id") Long id) throws Exception{
        orderService.cancel(id);
        return ResponseEntity.ok(Boolean.TRUE);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}

