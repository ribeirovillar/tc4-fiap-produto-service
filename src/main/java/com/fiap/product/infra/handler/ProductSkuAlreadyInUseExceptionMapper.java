package com.fiap.product.infra.handler;

import com.fiap.product.exception.ProductSkuAlreadyInUseException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ProductSkuAlreadyInUseExceptionMapper implements ExceptionMapper<ProductSkuAlreadyInUseException> {

    @Override
    public Response toResponse(ProductSkuAlreadyInUseException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "SKU_ALREADY_IN_USE",
                exception.getMessage(),
                Response.Status.CONFLICT.getStatusCode()
        );

        return Response.status(Response.Status.CONFLICT)
                .entity(errorResponse)
                .build();
    }
}