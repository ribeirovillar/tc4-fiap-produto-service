package com.fiap.product.infra.handler;

import com.fiap.product.exception.ProductNotFoundException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProductNotFoundExceptionMapper implements ExceptionMapper<ProductNotFoundException> {

    @Override
    public Response toResponse(ProductNotFoundException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "PRODUCT_NOT_FOUND",
                exception.getMessage(),
                Response.Status.NOT_FOUND.getStatusCode()
        );

        return Response.status(Response.Status.NOT_FOUND)
                .entity(errorResponse)
                .build();
    }
}