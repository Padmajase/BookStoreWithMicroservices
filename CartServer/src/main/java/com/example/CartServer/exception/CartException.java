package com.example.CartServer.exception;

public class CartException extends RuntimeException {
    public enum ExceptionType{
        DATA_ALREADY_EXISTS,
        QUANTITY_IS_GREATER,
        ENDPOINT_INVALID_OR_NULL,
        NOT_VALID_CART_DATA,
        NO_DATA_REGISTERED
    }

    public ExceptionType type;
    public CartException(String message, ExceptionType type){
        super(message);
        this.type = type;
    }
}
