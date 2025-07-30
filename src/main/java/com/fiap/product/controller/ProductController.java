package com.fiap.product.controller;

import com.fiap.product.controller.json.ProductRequestDTO;
import com.fiap.product.controller.json.ProductResponseDTO;
import com.fiap.product.mapper.ProductMapper;
import com.fiap.product.usecase.*;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;
import java.util.UUID;

@Path("/products")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class ProductController {

    @Inject
    ProductMapper productMapper;
    @Inject
    CreateProductUseCase createProductUseCase;
    @Inject
    RetrieveProductByIdUseCase retrieveProductByIdUseCase;
    @Inject
    RetrieveAllProductsUseCase retrieveAllProductsUseCase;
    @Inject
    RetrieveProductsBySkusUseCase retrieveProductsBySkusUseCase;
    @Inject
    UpdateProductUseCase updateProductUseCase;
    @Inject
    DeleteProductUseCase deleteProductUseCase;

    @GET
    public Response list() {
        return Response
                .status(Response.Status.OK)
                .entity(retrieveAllProductsUseCase.execute().stream().map(productMapper::mapFromDomainToDto).toList())
                .build();
    }

    @GET
    @Path("/{id}")
    public Response get(@PathParam("id") UUID id) {
        return Response
                .status(Response.Status.OK)
                .entity(productMapper.mapFromDomainToDto(retrieveProductByIdUseCase.execute(id)))
                .build();
    }

    @POST
    public Response create(ProductRequestDTO dto) {
        return Response
                .status(Response.Status.CREATED)
                .entity(productMapper.mapFromDomainToDto(createProductUseCase.execute(productMapper.mapFromDtoToDomain(dto))))
                .build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") UUID id, ProductResponseDTO dto) {
        return Response
                .status(Response.Status.OK)
                .entity(productMapper.mapFromDomainToDto(updateProductUseCase.execute(productMapper.mapFromDtoWithIdToDomain(id, dto))))
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") UUID id) {
        deleteProductUseCase.execute(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/skus")
    public Response getBySkus(@QueryParam("sku") List<String> skus) {
        return Response
                .status(Response.Status.OK)
                .entity(retrieveProductsBySkusUseCase.execute(skus).stream().map(productMapper::mapFromDomainToDto).toList())
                .build();
    }
}