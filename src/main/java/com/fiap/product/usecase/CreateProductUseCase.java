package com.fiap.product.usecase;

import com.fiap.product.domain.Product;
import com.fiap.product.gateway.ProductGateway;
import com.fiap.product.usecase.strategy.CreateProductStrategy;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Instance;
import jakarta.transaction.Transactional;

import java.util.List;

@ApplicationScoped
public class CreateProductUseCase {

    private final ProductGateway gateway;
    private final Instance<CreateProductStrategy> strategies;

    public CreateProductUseCase(ProductGateway gateway, Instance<CreateProductStrategy> strategies) {
        this.gateway = gateway;
        this.strategies = strategies;
    }

    @Transactional
    public Product execute(Product product) {
        strategies.forEach(strategy -> strategy.execute(product));
        return gateway.save(product);
    }
}