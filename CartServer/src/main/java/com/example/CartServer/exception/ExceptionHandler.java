package com.example.CartServer.exception;

import com.example.CartServer.dto.ResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {
    private static final String message = "Exception While Processing REST Request";

    @org.springframework.web.bind.annotation.ExceptionHandler(CartException.class)
    public ResponseEntity<ResponseDTO> handleCartException(CartException exception) {
        ResponseDTO responseDTO = new ResponseDTO(message, exception.getMessage());
        return new ResponseEntity<>(responseDTO, HttpStatus.BAD_REQUEST);
    }

}
