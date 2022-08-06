package com.example.OrderServer.model;

import com.example.OrderServer.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Data
@AllArgsConstructor
@Table(name = "order_data")
public class OrderData {

    @Id
    private int orderId;
    private LocalDate orderDate;
    private int quantity;
    private String address;

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "UserId")
    private UserData userData;

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "BookId")
    private BookData bookData;

    public OrderData() {
    }

    public OrderData(UserData user, BookData book, OrderDTO orderDTO) {
        this.userData = user;
        this.bookData = book;
        this.orderDate = orderDTO.getOrderDate();
        this.quantity = orderDTO.getQuantity();
        this.address = orderDTO.getAddress();
    }
}
