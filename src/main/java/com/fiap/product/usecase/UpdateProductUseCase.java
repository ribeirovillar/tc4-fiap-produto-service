package com.fiap.product.usecase;

import com.fiap.product.domain.Product;
import com.fiap.product.gateway.ProductGateway;
import com.fiap.product.usecase.strategy.UpdateProductStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class UpdateProductUseCase {

    private final ProductGateway gateway;
    private final Instance<UpdateProductStrategy> strategies;

    public UpdateProductUseCase(ProductGateway gateway, Instance<UpdateProductStrategy> strategies) {
        this.gateway = gateway;
        this.strategies = strategies;
    }

    @Transactional
    public Product execute(Product updated) {
        strategies.forEach(strategy -> strategy.execute(updated));
        return gateway.update(updated);
    }
}