package com.example.CartServer.dto;

import lombok.Data;

@Data
public class CartDTO {

    public Integer userId;
    public Integer bookId;
    private Integer quantity;
}
