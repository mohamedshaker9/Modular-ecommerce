package com.eg.swa.order.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.eg.swa.order.dto.OrderItemDto;
import com.eg.swa.order.model.Customer;
import com.eg.swa.order.model.Order;
import com.eg.swa.order.model.OrderItem;
import com.eg.swa.order.model.OrderStatus;
import com.eg.swa.order.repository.OrderRepository;
import com.eg.swa.product.model.Product;
import com.eg.swa.product.repository.ProductRepository;
import com.eg.swa.product.service.ProductService;


@Service
@Transactional
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private ProductService productService;

//	public OrderService(OrderRepository orderRepository, ProductRepository productRepository,
//			ProductService productService) {
//		this.orderRepository = orderRepository;
//		this.productRepository = productRepository;
//		this.productService = productService;
//	}

	public List<Order> getAllOrders() {
		return orderRepository.findAll();
	}

	public List<Order> getOrdersForCustomer(Customer customer) {
		return orderRepository.findByCustomer(customer);
	}

	public Order getOrderById(Long id) throws NotFoundException {
		return orderRepository.findById(id).orElseThrow(() -> new NotFoundException());
	}

	public Order createOrder(Long customerId, List<OrderItemDto> orderItems) throws Exception {
		Order order = new Order();

		Customer customer = new Customer();
		customer.setId(customerId);

		order.setCustomer(customer);
		order.setOrderDate(LocalDateTime.now());
		order.setOrderStatus(OrderStatus.CREATED);

		List<OrderItem> items = new ArrayList<>();
		for (OrderItemDto itemDto : orderItems) {
			
			Product product = productRepository.getReferenceById(itemDto.getProductId());
			
			// check if product is available in sufficient quantity
			if (product.getQuantity() < itemDto.getQuantity()) {
				throw new Exception("Insufficient Product Quantity");
			}

			// update product quantity
			product.setQuantity(product.getQuantity() - itemDto.getQuantity());
			productService.updateProduct(product);

			// Create order item and add to items list
			OrderItem item = new OrderItem();
			item.setOrder(order);
			item.setProduct(product);
			item.setQuantity(itemDto.getQuantity());
			item.setPrice(itemDto.getPrice());
			items.add(item);
		}
		order.setOrderItems(items);
		return orderRepository.save(order);
	}

	public void cancelOrder(Order order) throws Exception {
		if (order.getOrderStatus() != OrderStatus.CREATED) {
			throw new Exception("Order Cannot Be Cancelled");
		}

		// restore product quantities
		for (OrderItem item : order.getOrderItems()) {
			Product product = item.getProduct();
			product.setQuantity(product.getQuantity() + item.getQuantity());
			productService.updateProduct(product);
		}

		order.setOrderStatus(OrderStatus.CANCELLED);
		orderRepository.save(order);
	}

	public void deleteOrder(Long id) {
		orderRepository.deleteById(id);
	}
}
