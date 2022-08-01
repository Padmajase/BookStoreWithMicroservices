package com.example.CartServer.service;


import com.example.CartServer.dto.ResponseDTO;
import com.example.CartServer.model.CartData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ICartService {
    List<CartData> getBookListInCart();

    ResponseEntity<ResponseDTO> updateBookQuantity(int quantity, int cartBookId);

    ResponseEntity<ResponseDTO> deleteBookFromCart(int bookId);

    ResponseEntity<ResponseDTO> saveBooksToCart(int quantity, int bookId, String token) throws Exception;
}
