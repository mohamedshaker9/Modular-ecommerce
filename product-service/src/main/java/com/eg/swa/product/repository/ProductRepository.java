package com.eg.swa.product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eg.swa.product.model.Product;

import java.util.Map;


public interface ProductRepository extends JpaRepository<Product, Long> {

    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity - :quantity WHERE p.id = :id")
    void decreaseProductQuantityBy(@Param("id") long id, @Param("quantity") int quantity);

    @Modifying
    @Query("UPDATE Product p SET p.quantity = p.quantity + :quantity WHERE p.id = :id")
    void increaseProductQuantityBy(@Param("id") long id, @Param("quantity") int quantity);
    
    default void batchDecreaseProductQuantities(Map<Long, Integer> products){
        products.entrySet()
                .forEach((entry) -> decreaseProductQuantityBy(entry.getKey(), entry.getValue()));
    }
    default void batchIncreaseProductQuantities(Map<Long, Integer> products){
        products.entrySet()
                .forEach((entry) -> increaseProductQuantityBy(entry.getKey(), entry.getValue()));
    }


}

