package com.eg.swa.product.mapper;


import com.eg.swa.product.dto.ProductDto;
import com.eg.swa.product.model.Product;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProductMapper {

    ProductDto map(Product product);

    List<ProductDto> map(List<Product> products);
}
