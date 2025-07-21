package com.fiap.product.exception;

public class InvalidProductSkuException extends RuntimeException {
    public InvalidProductSkuException(String message) {
        super(message != null && !message.trim().isEmpty() ? "Invalid SKU code: " + message : "Invalid SKU code");
    }
}
