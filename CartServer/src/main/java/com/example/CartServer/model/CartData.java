package com.example.CartServer.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CartData {
    @Id
    @GeneratedValue
    private int cartId;
    @ManyToOne
    @JoinColumn(name = "book_data_book_id")
    private BookData bookData;
    @ManyToOne
    @JoinColumn(name = "user_data_user_id")
    private UserData userData;
    private int quantity;

    public CartData(BookData bookData, UserData userData, int quantity) {
        this.bookData = bookData;
        this.userData = userData;
        this.quantity = quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
