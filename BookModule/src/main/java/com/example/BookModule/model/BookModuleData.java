package com.example.BookModule.model;

import com.example.BookModule.dto.BookDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

//import javax.persistence.*;


@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book_module_data")
public class  BookModuleData {
    @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "book_id", nullable = false)
    private int bookId;

    @Column(name = "book_name", length = 225, nullable = false)
    private String bookName;
    @Column(name = "book_author", length = 225, nullable = false)
    private String bookAuthor;
    @Column(name = "book_Price", nullable = false)
    private long bookPrice;
    @Column(name = "quantity", nullable = false)
    private int quantity;

    public BookModuleData() {
    }

    public BookModuleData(int bookId, BookDTO bookDTO) {
        this.bookId=bookId;
        this.bookName=bookDTO.bookName;
        this.bookAuthor=bookDTO.bookAuthor;
        this.bookPrice=bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
    }

   public BookModuleData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
   }
}
