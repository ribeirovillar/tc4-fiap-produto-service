package com.fiap.product.usecase.strategy;

import com.fiap.product.domain.Product;

public interface CreateProductStrategy {
    void execute(Product product);
}
