package com.eg.swa.cart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {"com.eg.swa.cart.*", "com.eg.swa.product.*", "com.eg.swa.security.*"})
@EnableJpaRepositories(basePackages = {"com.eg.swa.product.repository", "com.eg.swa.security.repository"})
public class CartServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }

}
