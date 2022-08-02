package com.example.OrderServer.springbatch;

import com.example.OrderServer.model.OrderData;
import org.springframework.batch.item.ItemProcessor;

public class OrderItemProcessor implements ItemProcessor<OrderData, OrderData> {
    @Override
    public OrderData process(OrderData OrderData) throws Exception {
        return OrderData;
    }
}
