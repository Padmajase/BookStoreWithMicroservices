package com.example.BookServer.model;

import com.example.BookServer.dto.BookDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
public class BookData {
    @Id
    @GeneratedValue
    @Column(name = "book_id")
    public Integer bookId;
    public String bookName;
    public String bookAuthor;
    public Integer bookPrice;
    public Integer quantity;
    public BookData() {
        super();
    }

    public BookData(Integer bookId, BookDTO bookDTO) {
        this.bookId = bookId;
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
    }

    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
    }

    public void updateBook(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
    }
}
