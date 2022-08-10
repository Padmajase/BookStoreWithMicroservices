package com.example.OrderServer.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class Book {
    public Integer bookId;
    public String bookName;
    public String bookAuthor;
    public Integer bookPrice;
    public Integer quantity;

    public Book() {

    }

    public Book(Integer bookId, String bookName, String bookAuthor, Integer bookPrice, Integer quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
    }

    public String getBookName() {
        return bookName;
    }
}
