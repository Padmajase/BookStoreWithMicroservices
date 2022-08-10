package com.example.OrderServer.model;

import com.example.OrderServer.dto.OrderDTO;
import lombok.Data;
import javax.persistence.*;

@Entity
@Data
public class OrderData {

    @Id
    @GeneratedValue
    public Integer orderId;
    public Integer quantity;
    public String address;
    public Integer price;
    public Integer userId;
    public Integer bookId;

    public OrderData() {
    }

    public OrderData(OrderDTO orderDTO) {
        this.quantity = orderDTO.getQuantity();
        this.address = orderDTO.getAddress();
//        this.price = totalPrice;
        this.userId = orderDTO.getUserId();
        this.bookId = orderDTO.getBookId();
    }

    public void updateOrder(OrderDTO orderDTO) {
        this.quantity = orderDTO.getQuantity();
        this.address = orderDTO.getAddress();
//        this.price =
    }
}
