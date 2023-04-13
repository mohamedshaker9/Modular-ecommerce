package com.eg.swa.order.controller;

import java.util.List;

import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eg.swa.order.dto.OrderItemDto;
import com.eg.swa.order.model.Order;
import com.eg.swa.order.service.OrderService;

@RestController
@RequestMapping("/api/orders")
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public Order getOrderById(@PathVariable Long id) throws NotFoundException {
        return orderService.getOrderById(id);
    }

    @PostMapping("/create")
    public ResponseEntity<Order> createOrder(@RequestBody List<OrderItemDto> orderItems, Long customerId) throws Exception {
//        Customer customer = (Customer) authentication.getPrincipal();
        Order order = orderService.createOrder(customerId, orderItems);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }
    
    // Need to add cancelOrder function

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
    }
}

