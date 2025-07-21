package com.fiap.product.exception;

public class InvalidProductNameException extends RuntimeException {
    public InvalidProductNameException(String message) {
        super(message != null && !message.trim().isEmpty() ? "Invalid product name: " + message : "Invalid product name");
    }
}
