package com.example.CartServer.model;

import com.example.CartServer.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private int quantity;
    private String bookName;
    private String bookAuthor;
    private long bookPrice;

    public BookData(int bookId, BookDTO bookDTO) {
        this.bookId=bookId;
        this.bookName=bookDTO.bookName;
        this.bookAuthor=bookDTO.bookAuthor;
        this.bookPrice=bookDTO.bookPrice;
    }

     public String getBookName() {
        return bookName;
    }

    public int getQuantity() {
        return quantity;
    }
}
