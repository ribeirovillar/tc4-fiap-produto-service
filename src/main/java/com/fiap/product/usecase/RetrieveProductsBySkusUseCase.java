package com.fiap.product.usecase;

import com.fiap.product.domain.Product;
import com.fiap.product.gateway.ProductGateway;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class RetrieveProductsBySkusUseCase {

    private final ProductGateway gateway;

    public RetrieveProductsBySkusUseCase(ProductGateway gateway) {
        this.gateway = gateway;
    }

    public List<Product> execute(List<String> skus) {
        return gateway.findBySkus(skus);
    }
}
