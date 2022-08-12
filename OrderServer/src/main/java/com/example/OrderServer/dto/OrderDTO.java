package com.example.OrderServer.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
public class OrderDTO {

    private Integer quantity;
    private String address;
    private Integer price;
    private Integer bookId;
    private Integer UserId;
}
