package com.example.CartServer.model;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class BookData {

    @Id
    private Integer bookId;
    private String bookName;
    private String bookAuthor;
    private Integer bookPrice;
    private Integer quantity;

    public BookData() {
    }

    public BookData(Integer bookId, String bookName, String bookAuthor, Integer bookPrice, Integer quantity) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookAuthor = bookAuthor;
        this.bookPrice = bookPrice;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }
}
