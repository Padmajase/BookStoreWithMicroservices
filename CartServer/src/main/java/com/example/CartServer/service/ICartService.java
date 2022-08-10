package com.example.CartServer.service;


import com.example.CartServer.dto.CartDTO;
import com.example.CartServer.dto.ResponseDTO;
import com.example.CartServer.model.CartData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICartService {

    ResponseEntity<ResponseDTO> saveCartData(CartDTO cartDTO);

    List<CartData> getCartList();

    CartData getCartDataById(Integer cartId);


    ResponseEntity<ResponseDTO> updateCart(Integer quantity, CartDTO cartDTO);

    ResponseEntity<ResponseDTO> deleteCartData(Integer cartId);
}
