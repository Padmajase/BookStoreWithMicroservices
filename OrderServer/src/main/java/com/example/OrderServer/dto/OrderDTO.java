package com.example.OrderServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class OrderDTO {

    private Integer quantity;
    private String address;
    private Integer price;
    private Integer bookId;
    private Integer UserId;

    public OrderDTO() {
    }

    public OrderDTO(Integer quantity, String address, Integer bookId, Integer userId) {
        this.quantity = quantity;
        this.address = address;
        this.bookId = bookId;
        this.UserId = userId;
    }

    public Integer getUserId() {
        return UserId;
    }
}
