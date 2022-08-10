package com.example.BookServer.dto;

import lombok.Data;

@Data
public class BookDTO {
    public String bookName;
    public String bookAuthor;
    public Integer bookPrice;
    public Integer quantity;
}
