package com.fiap.product.gateway;

import com.fiap.product.domain.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ProductGateway {

    List<Product> findAll();

    Product save(Product product);
    Product update(Product product);

    Optional<Product> findById(UUID productId);
    Optional<Product> findBySku(String sku);
    List<Product> findBySkus(List<String> skus);

    void delete(Product product);

}
