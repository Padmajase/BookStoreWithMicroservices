package com.example.CartServer.controller;
import com.example.CartServer.dto.CartDTO;
import com.example.CartServer.dto.ResponseDTO;
import com.example.CartServer.model.CartData;
import com.example.CartServer.service.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private ICartService cartService;


    /*************** inserting cart details ***************/
    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveBooksToCart(CartDTO cartDTO){
        return cartService.saveCartData(cartDTO);
    }

    /*************** getting cart list ***************/
    @GetMapping(value={"", "/", "get"})
    public ResponseEntity<ResponseDTO> getCartList(){
        List<CartData> bookListInCart = null;
        bookListInCart = cartService.getCartList();
        ResponseDTO responseDTO = new ResponseDTO("Your Cart :",bookListInCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** getting cart data by id ***************/
    @GetMapping("/get/{cartId}")
    public ResponseEntity<ResponseDTO> getById(@PathVariable Integer cartId){
        CartData cartData = cartService.getCartDataById(cartId);
        ResponseDTO responseDTO = new ResponseDTO("cart data by given id", cartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** updating cart Data ***************/
    @PutMapping("/update/{cartId}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable Integer cartId, CartDTO cartDTO){
        return cartService.updateCart(cartId, cartDTO);
   }

    /*************** deleting book quantity in cart ***************/
    @DeleteMapping("/delete/{cartId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable Integer cartId){
        return cartService.deleteCartData(cartId);
   }
}


