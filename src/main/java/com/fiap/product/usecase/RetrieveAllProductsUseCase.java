package com.fiap.product.usecase;

import com.fiap.product.domain.Product;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RetrieveAllProductsUseCase {

    private final ProductGateway gateway;

    public RetrieveAllProductsUseCase(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public List<Product> execute() {
        return gateway.findAll();
    }
}