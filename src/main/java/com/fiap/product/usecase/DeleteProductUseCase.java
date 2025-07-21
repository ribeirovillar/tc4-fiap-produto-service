package com.fiap.product.usecase;

import com.fiap.product.exception.ProductNotFoundException;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

import java.util.UUID;

@ApplicationScoped
public class DeleteProductUseCase {

    private final ProductGateway gateway;

    public DeleteProductUseCase(ProductGateway gateway) {
        this.gateway = gateway;
    }

    @Transactional
    public void execute(UUID id) {
        gateway.delete(gateway.findById(id)
                .orElseThrow(() -> new ProductNotFoundException(id)));
    }
}