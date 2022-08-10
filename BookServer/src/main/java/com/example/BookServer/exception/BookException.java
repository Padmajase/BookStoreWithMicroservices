package com.example.BookServer.exception;

/*************** handling User Registration Exception by user defined exception ***************/
public class BookException extends RuntimeException {
    public BookException(String message) {
        super(message);
    }
}
