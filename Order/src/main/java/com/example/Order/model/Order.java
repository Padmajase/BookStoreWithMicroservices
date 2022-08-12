package com.example.Order.model;

import com.example.Order.dto.OrderDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Table(name = "order")
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @Id
    @GeneratedValue
    @Column(name = "order_id", nullable = false)
    private Integer orderId;

    private Integer quantity;
    private String address;
    private Integer price;
    private Integer bookId;
    private Integer userId;

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Order(OrderDTO orderDTO) {
        this.quantity = quantity;
        this.address = address;
        this.price = price;
        this.bookId = bookId;
        this.userId = userId;
    }
}
