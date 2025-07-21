package com.fiap.product.infra.handler;

public record ErrorResponse(String errorCode, String message, int status) {
}
