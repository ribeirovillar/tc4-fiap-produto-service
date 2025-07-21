package com.fiap.product.usecase.strategy;

import com.fiap.product.domain.Product;
import com.fiap.product.exception.ProductSkuAlreadyInUseException;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.Objects;

@ApplicationScoped
public class ProductSkuValidation implements CreateProductStrategy, UpdateProductStrategy {

    private final ProductGateway gateway;


    public ProductSkuValidation(ProductGateway productGateway) {
        this.gateway = productGateway;
    }

    @Override
    public void execute(Product product) {
        gateway.findBySku(product.getSku())
                .ifPresent(existingProduct -> {
                    if (Objects.nonNull(product.getId()) && existingProduct.getId().equals(product.getId())) {
                        return;
                    }
                    throw new ProductSkuAlreadyInUseException(product.getSku());
                });
    }
}
