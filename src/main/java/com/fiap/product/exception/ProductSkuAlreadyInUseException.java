package com.fiap.product.exception;

public class ProductSkuAlreadyInUseException extends RuntimeException {
    public ProductSkuAlreadyInUseException(String message) {
        super("SKU already exists for another product: " + message);
    }
}
