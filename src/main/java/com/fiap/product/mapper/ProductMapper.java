package com.fiap.product.mapper;

import com.fiap.product.controller.json.ProductRequestDTO;
import com.fiap.product.controller.json.ProductResponseDTO;
import com.fiap.product.domain.Product;
import com.fiap.product.gateway.database.jpa.entity.ProductEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.UUID;

@Mapper(componentModel = "cdi")
public interface ProductMapper {

    ProductResponseDTO mapFromDomainToDto(Product product);
    @Mapping(target = "id", ignore = true)
    Product mapFromDtoToDomain(ProductRequestDTO productDTO);
    Product mapFromDtoWithIdToDomain(UUID id, ProductRequestDTO productDTO);

    Product mapFromEntityToDomain(ProductEntity productEntity);
    ProductEntity mapFromDomainToEntity(Product product);

}
