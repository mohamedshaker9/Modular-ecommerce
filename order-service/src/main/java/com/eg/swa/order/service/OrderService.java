package com.eg.swa.order.service;

import com.eg.swa.order.dto.OrderDto;
import com.eg.swa.order.dto.OrderItemDto;
import com.eg.swa.order.mapper.OrderItemMapper;
import com.eg.swa.order.mapper.OrderMapper;
import com.eg.swa.order.model.Order;
import com.eg.swa.order.model.OrderItem;
import com.eg.swa.order.model.OrderStatus;
import com.eg.swa.order.repository.OrderRepository;
import com.eg.swa.product.repository.ProductRepository;
import com.eg.swa.product.service.ProductService;
import com.eg.swa.security.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequiredArgsConstructor

@Service
@Transactional
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final ProductService productService;

    private final OrderMapper orderMapper;

    private final OrderItemMapper orderItemMapper;

    public List<OrderDto> get() {
        return orderMapper.map(orderRepository.findAll());
    }

    public List<OrderDto> getCustomerOrders(Customer customer) {
        return orderMapper.map(orderRepository.findByCustomer(customer));
    }

    public OrderDto get(Long id) throws NotFoundException {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
        return orderMapper.map(order);
    }

    public OrderDto create(OrderDto orderDto) throws Exception {
        Map<Long, Integer> productQuantities = convertProductQuantitiesMap(orderDto.getOrderItems());

        if (!productService.isAllAvailableQuantity(productQuantities)) {
            throw new Exception("Insufficient Product Quantity");
        }

        productService.decreaseQuantities(productQuantities);

        Order savedOrder = orderRepository.save(createOrderObject(orderDto));

        return orderMapper.map(savedOrder);
    }

    public void cancel(long id) throws Exception {
        Order order = orderRepository.findById(id).get();

        if (order.getOrderStatus() != OrderStatus.CREATED) {
            throw new Exception("Order Cannot Be Cancelled");
        }

        Map<Long, Integer> productQuantities = order.getOrderItems().stream()
                .collect(Collectors
                        .toMap(
                                orderItem -> orderItem.getProduct().getId(),
                                OrderItem::getQuantity));

        productService.increaseQuantities(productQuantities);

        order.setOrderStatus(OrderStatus.CANCELLED);
        orderRepository.save(order);
    }


    private Map<Long, Integer> convertProductQuantitiesMap(List<OrderItemDto> orderItemDtos) {
        return orderItemDtos.stream()
                .collect(Collectors.toMap(OrderItemDto::getProductId, OrderItemDto::getQuantity));
    }

    private Order createOrderObject(OrderDto orderDto) {
        Order order = orderMapper.map(orderDto);
        order.setOrderStatus(OrderStatus.CREATED);

        List<OrderItem> orderItems = orderDto.getOrderItems().stream()
                .map(orderItemDto -> {
                    OrderItem orderItem = orderItemMapper.map(orderItemDto);
                    orderItem.setOrder(order);
                    return orderItem;
                })
                .collect(Collectors.toList());

        order.setOrderItems(orderItems);

        return order;
    }


    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }
}
