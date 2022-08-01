package com.example.CartServer.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    public String bookName;
    public String bookAuthor;
    public long bookPrice;
}
