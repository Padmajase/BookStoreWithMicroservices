package com.example.CartServer.springbatch;

import com.example.CartServer.model.CartData;
import org.springframework.batch.item.ItemProcessor;

public class CartItemProcessor implements ItemProcessor<CartData, CartData> {
    @Override
    public CartData process(CartData OrderData) throws Exception {
        return OrderData;
    }
}
