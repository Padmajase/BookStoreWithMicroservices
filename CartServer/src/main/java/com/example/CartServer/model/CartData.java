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
    public Integer cartId;
    @OneToOne
    @JoinColumn(name = "cart_data_book_id", referencedColumnName = "bookId")
    public BookData bookData;
    @OneToOne
    @JoinColumn(name = "cart_data_user_id", referencedColumnName = "userId")
    public UserData userData;
    public Integer quantity;

    public CartData(BookData bookData, UserData userData, Integer quantity) {
        this.bookData = bookData;
        this.userData = userData;
        this.quantity = quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
}
