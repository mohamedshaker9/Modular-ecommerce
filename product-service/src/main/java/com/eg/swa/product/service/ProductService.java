package com.eg.swa.product.service;

import java.util.List;
import java.util.Map;

import com.eg.swa.product.dto.ProductDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import com.eg.swa.product.model.Product;
import com.eg.swa.product.repository.ProductRepository;

@RequiredArgsConstructor

@Service
public class ProductService {
	
	private final ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product get(Long id) throws NotFoundException {
        return productRepository.findById(id)
                .orElseThrow(() -> new NotFoundException());
    }

    public Product saveProduct(Product product) {
        return productRepository.save(product);
    }
    
    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public boolean isAllAvailableQuantity(Map<Long, Integer> productQuantities) throws NotFoundException{
           return productQuantities.entrySet().stream()
                    .allMatch(entry ->
                     productRepository.findById(entry.getKey())
                             .get().getQuantity() > entry.getValue());
    }

    public void decreaseQuantities(Map<Long, Integer> quantities) {
        productRepository.batchDecreaseProductQuantities(quantities);
    }

    public void increaseQuantities(Map<Long, Integer> quantities) {
        productRepository.batchIncreaseProductQuantities(quantities);
    }
}
