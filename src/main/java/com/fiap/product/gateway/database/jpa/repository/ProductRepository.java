package com.fiap.product.gateway.database.jpa.repository;

import com.fiap.product.gateway.database.jpa.entity.ProductEntity;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.UUID;

@ApplicationScoped
public class ProductRepository implements PanacheRepositoryBase<ProductEntity, UUID> {
}
