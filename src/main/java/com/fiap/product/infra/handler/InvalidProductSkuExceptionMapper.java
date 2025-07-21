package com.fiap.product.infra.handler;

import com.fiap.product.exception.InvalidProductSkuException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidProductSkuExceptionMapper implements ExceptionMapper<InvalidProductSkuException> {

    @Override
    public Response toResponse(InvalidProductSkuException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PRODUCT_SKU",
                exception.getMessage(),
                Response.Status.BAD_REQUEST.getStatusCode()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}