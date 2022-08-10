package com.example.CustomerServer.dto;

import lombok.Data;

@Data
public class ResponseDTO {
    public String message;
    public Object data;

    public ResponseDTO(String message, Object data) {
        this.message = message;
        this.data = data;
    }
}
