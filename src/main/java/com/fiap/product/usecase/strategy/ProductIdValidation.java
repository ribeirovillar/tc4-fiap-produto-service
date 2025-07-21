package com.fiap.product.usecase.strategy;

import com.fiap.product.domain.Product;
import com.fiap.product.exception.ProductNotFoundException;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductIdValidation implements UpdateProductStrategy {

    private final ProductGateway gateway;

    public ProductIdValidation(ProductGateway gateway) {
        this.gateway = gateway;
    }

    @Override
    public void execute(Product product) {
        gateway.findById(product.getId()).orElseThrow(
                () -> new ProductNotFoundException(product.getId())
        );
    }
}
