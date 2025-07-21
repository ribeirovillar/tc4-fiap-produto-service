package com.fiap.product.exception;

import java.math.BigDecimal;

public class InvalidProductPriceException extends RuntimeException {
    public InvalidProductPriceException(BigDecimal price) {
        super(price != null && !price.toString().trim().isEmpty() ? "Invalid product price: " + price : "Invalid product price");
    }
}
