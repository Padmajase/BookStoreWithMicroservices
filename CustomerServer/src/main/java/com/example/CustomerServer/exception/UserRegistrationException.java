package com.example.CustomerServer.exception;

/*************** handling User Registration Exception by user defined exception ***************/
public class UserRegistrationException extends RuntimeException {
    public UserRegistrationException(String message) {
        super(message);
    }
}
