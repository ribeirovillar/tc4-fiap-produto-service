package com.fiap.product.controller.json;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

public class ProductResponseDTO extends ProductRequestDTO implements Serializable {
    private UUID id;

    public ProductResponseDTO(UUID id, String name, String sku, BigDecimal price) {
        super(name, sku, price);
        this.id = id;
    }

    public UUID getId() {
        return id;
    }
}
