package com.example.OrderServer.exception;

/*************** handling User Registration Exception by user defined exception ***************/
public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }
}
