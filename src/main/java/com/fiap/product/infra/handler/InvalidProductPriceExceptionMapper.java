package com.fiap.product.infra.handler;

import com.fiap.product.exception.InvalidProductPriceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidProductPriceExceptionMapper implements ExceptionMapper<InvalidProductPriceException> {

    @Override
    public Response toResponse(InvalidProductPriceException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PRODUCT_PRICE",
                exception.getMessage(),
                Response.Status.BAD_REQUEST.getStatusCode()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}