package com.fiap.product.gateway.database.jpa.entity;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.util.UUID;

@Entity
@Table(name = "products")
public class ProductEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    private UUID id;
    private String name;
    private String sku;
    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
