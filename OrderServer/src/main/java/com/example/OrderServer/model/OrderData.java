package com.example.OrderServer.model;

import com.example.OrderServer.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderData {

    @Id
    private int orderId;

    private LocalDate orderDate;
    private int quantity;
    private String address;
    private boolean cancel = false;


    @ManyToOne
    @JoinColumn(name = "user_ID", referencedColumnName = "UserId")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "book_ID", referencedColumnName = "BookId")
    private BookData bookData;

    public OrderData(UserData user, BookData book, OrderDTO orderDTO) {
        this.userData = user;
        this.bookData = book;
    }
    public void createOrder(OrderDTO orderDTO){
        this.orderDate = orderDTO.getOrderDate();
        this.address = orderDTO.getAddress();
        this.quantity = orderDTO.getQuantity();
        this.cancel = false;
    }
}
