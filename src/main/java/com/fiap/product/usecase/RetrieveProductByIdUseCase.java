package com.fiap.product.usecase;

import com.fiap.product.domain.Product;
import com.fiap.product.exception.ProductNotFoundException;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class RetrieveProductByIdUseCase {

    private final ProductGateway gateway;

    public RetrieveProductByIdUseCase(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public Product execute(UUID id) {
        return gateway.findById(id).orElseThrow(() -> new ProductNotFoundException(id));
    }
}