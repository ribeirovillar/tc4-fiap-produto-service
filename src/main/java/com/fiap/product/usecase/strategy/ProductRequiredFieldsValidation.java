package com.fiap.product.usecase.strategy;

import com.fiap.product.domain.Product;
import com.fiap.product.exception.InvalidProductNameException;
import com.fiap.product.exception.InvalidProductPriceException;
import com.fiap.product.exception.InvalidProductSkuException;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class ProductRequiredFieldsValidation implements CreateProductStrategy, UpdateProductStrategy {
    @Override
    public void execute(Product product) {
        if (!product.isNameValid()) {
            throw new InvalidProductNameException(product.getName());
        }
        if (!product.isSkuValid()) {
            throw new InvalidProductSkuException(product.getSku());
        }
        if (!product.isPriceValid()) {
            throw new InvalidProductPriceException(product.getPrice());
        }
    }
}
