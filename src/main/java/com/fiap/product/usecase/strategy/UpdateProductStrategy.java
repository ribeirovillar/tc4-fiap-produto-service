package com.fiap.product.usecase.strategy;

import com.fiap.product.domain.Product;

public interface UpdateProductStrategy {
    void execute(Product product);
}
