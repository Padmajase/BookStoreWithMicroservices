package com.example.OrderServer.exception;

import com.example.OrderServer.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    private static final String message = "Exception While Processing REST Request";

    @org.springframework.web.bind.annotation.ExceptionHandler(OrderException.class)
    public ResponseEntity<ResponseDTO> handleUserRegistrationException(OrderException exception) {
        ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
