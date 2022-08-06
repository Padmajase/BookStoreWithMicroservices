package com.example.CartServer.service;

import com.example.CartServer.dto.ResponseDTO;
import com.example.CartServer.exception.CartException;
import com.example.CartServer.model.BookData;
import com.example.CartServer.model.CartData;
import com.example.CartServer.model.UserData;
import com.example.CartServer.repository.CartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    RestTemplate restTemplate;


    /*************** getting cart list ***************/
    @Override
    public List<CartData> getBookListInCart() {

        return cartRepository.findAll();
    }

    /*************** adding book in cart repository ***************/
    @Override
    public ResponseEntity<ResponseDTO> saveBooksToCart(int quantity, int bookId, int userId) throws Exception {
        BookData bookData  = restTemplate.getForObject("localhost:8001/book/get/" + bookId, BookData.class);
        UserData userData  = restTemplate.getForObject("localhost:8002/user/get/" + userId, UserData.class);
//        UserData userData  = restTemplate.getForObject("http://localhost:8002/user?token=" + token, UserData.class);
        if(bookData == null || userData == null){
            throw new CartException("the user not found  "+ userData + " or the book not found "+bookData, CartException.ExceptionType.ENDPOINT_INVALID_OR_NULL);
        }
        CartData cartData = new CartData(bookData, userData, quantity);
        if(quantity > bookData.getQuantity())
            throw new CartException("exceeded the book quantity", CartException.ExceptionType.QUANTITY_IS_GREATER);
        cartRepository.save(cartData);
        ResponseDTO responseDTO = new ResponseDTO("book saved in cart ", cartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** updating book quantity ***************/
    @Override
    public ResponseEntity<ResponseDTO> updateBookQuantity(int quantity, int cartBookId) {
        Optional<CartData> cartData = cartRepository.findById(cartBookId);
        if(cartData.isEmpty())
            throw new CartException("no book present", CartException.ExceptionType.NO_DATA_REGISTERED);
//        not recognized optional
        cartData.get().setQuantity(quantity);
        cartRepository.save(cartData.get());
        ResponseDTO responseDTO = new ResponseDTO("updated quantity", cartData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** deleting book from cart repository ***************/
    @Override
    public  ResponseEntity<ResponseDTO> deleteBookFromCart(int bookId) {
        if (cartRepository.findById(bookId).isPresent()){
            cartRepository.deleteById(bookId);
            ResponseDTO responseDTO = new ResponseDTO("book deleted successfully with book id : ",bookId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else
            throw new CartException("book not found with given id ", CartException.ExceptionType.NOT_VALID_CART_DATA);
    }

    @Override
    public ResponseEntity<ResponseDTO> saveBooksToCart(int quantity, int bookId, String token) throws Exception {
        return null;
    }
}
