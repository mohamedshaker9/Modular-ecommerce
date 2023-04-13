package com.eg.swa.order;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.eg.swa.order.*" , "com.eg.swa.product.*"})
@EnableJpaRepositories(basePackages = {"com.eg.swa.order.repository" , "com.eg.swa.product.repository"})
@ComponentScan("com.eg.swa.product")
public class OrderServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(OrderServiceApplication.class, args);
	}

}
