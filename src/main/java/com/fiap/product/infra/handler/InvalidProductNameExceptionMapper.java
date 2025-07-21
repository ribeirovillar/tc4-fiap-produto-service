package com.fiap.product.infra.handler;

import com.fiap.product.exception.InvalidProductNameException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class InvalidProductNameExceptionMapper implements ExceptionMapper<InvalidProductNameException> {

    @Override
    public Response toResponse(InvalidProductNameException exception) {
        ErrorResponse errorResponse = new ErrorResponse(
                "INVALID_PRODUCT_NAME",
                exception.getMessage(),
                Response.Status.BAD_REQUEST.getStatusCode()
        );

        return Response.status(Response.Status.BAD_REQUEST)
                .entity(errorResponse)
                .build();
    }
}