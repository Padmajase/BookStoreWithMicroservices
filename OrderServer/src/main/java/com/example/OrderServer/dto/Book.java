package com.example.OrderServer.dto;

import lombok.Data;
@Data
public class Book {
   public Integer bookId;
   public  String bookName;
   public  String bookAuthor;
   public  Integer bookPrice;
   public  Integer quantity;

    public Book() {
        super();
    }

    public Book(Integer bookId, String bookName, String bookAuthor, Integer bookPrice, Integer quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public Integer getBookId() {
        return bookId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getBookPrice() {
        return bookPrice;
    }
}
