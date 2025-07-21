package com.fiap.product.domain;

import java.math.BigDecimal;
import java.util.UUID;

public class Product {
    private UUID id;
    private String name;
    private String sku;
    private BigDecimal price;

    public Product(UUID id, String name, String sku, BigDecimal price) {
        this.id = id;
        this.name = name;
        this.sku = sku;
        this.price = price;
    }

    public UUID getId() {
        return id;
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

    public boolean isNameValid() {
        return name != null && !name.trim().isEmpty();
    }

    public boolean isSkuValid() {
        return sku != null && !sku.trim().isEmpty();
    }

    public boolean isPriceValid() {
        return price != null && price.compareTo(BigDecimal.ZERO) > 0;
    }
}
