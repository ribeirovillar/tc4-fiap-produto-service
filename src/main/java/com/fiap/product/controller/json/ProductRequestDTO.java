package com.fiap.product.controller.json;

import java.io.Serializable;
import java.math.BigDecimal;

public class ProductRequestDTO implements Serializable {
    private String name;
    private String sku;
    private BigDecimal price;

    public ProductRequestDTO(String name, String sku, BigDecimal price) {
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public BigDecimal getPrice() {
        return price;
    }
}
