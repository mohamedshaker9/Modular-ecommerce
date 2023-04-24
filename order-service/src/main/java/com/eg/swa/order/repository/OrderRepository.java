package com.eg.swa.order.repository;

import com.eg.swa.order.model.Order;
import com.eg.swa.security.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
	 List<Order> findByCustomer(Customer customer);
}

