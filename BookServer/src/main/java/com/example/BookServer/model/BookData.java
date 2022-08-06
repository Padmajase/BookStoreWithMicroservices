package com.example.BookServer.model;

import com.example.BookServer.dto.BookDTO;
import lombok.*;

import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@Getter
@Setter
@Table(name = "book_data")
public class BookData {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
//    @Column(name = "book_name")
    private String bookName;
//    @Column(name = "book_author")
    private String bookAuthor;
    @Column(name = "book_price")
    private long bookPrice;
    @Column(name = "quantity")
    private int quantity;

    public BookData() {
    }

    public BookData(BookDTO bookDTO) {
        this.bookName = bookDTO.bookName;
        this.bookAuthor = bookDTO.bookAuthor;
        this.bookPrice = bookDTO.bookPrice;
        this.quantity = bookDTO.quantity;
   }
}
