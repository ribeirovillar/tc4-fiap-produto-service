package com.fiap.product.gateway.database.jpa;

import com.fiap.product.domain.Product;
import com.fiap.product.gateway.ProductGateway;
import com.fiap.product.gateway.database.jpa.entity.ProductEntity;
import com.fiap.product.gateway.database.jpa.repository.ProductRepository;
import com.fiap.product.mapper.ProductMapper;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@ApplicationScoped
public class ProductJpaRepository implements ProductGateway {

    @Inject
    ProductRepository productRepository;
    @Inject
    ProductMapper productMapper;

    @Override
    public List<Product> findAll() {
        return productRepository.findAll().stream().map(productMapper::mapFromEntityToDomain).toList();
    }

    @Override
    public Product save(Product product) {
        ProductEntity entity = productMapper.mapFromDomainToEntity(product);
        productRepository.persist(entity);
        return productMapper.mapFromEntityToDomain(entity);
    }

    @Override
    public Product update(Product product) {
        ProductEntity entity = productRepository.findById(product.getId());
        entity.setName(product.getName());
        entity.setSku(product.getSku());
        entity.setPrice(product.getPrice());
        productRepository.persist(entity);
        return productMapper.mapFromEntityToDomain(entity);
    }

    @Override
    public Optional<Product> findById(UUID productId) {
        return Optional.ofNullable(productMapper.mapFromEntityToDomain(productRepository.findById(productId)));
    }

    @Override
    public Optional<Product> findBySku(String sku) {
        return productRepository.find("sku", sku).firstResultOptional()
                .map(productMapper::mapFromEntityToDomain);
    }

    @Override
    public void delete(Product product) {
        productRepository.delete(productMapper.mapFromDomainToEntity(product));
    }
}
