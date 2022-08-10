package com.example.CartServer.service;

import com.example.CartServer.dto.CartDTO;
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

@Service
public class CartService implements ICartService{

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    RestTemplate restTemplate;


    /*************** adding cart data ***************/
    @Override
    public ResponseEntity<ResponseDTO> saveCartData(CartDTO cartDTO) {
        BookData bookData  = restTemplate.getForObject("http://localhost:8001/book/get/" + cartDTO.getBookId(), BookData.class);
        UserData userData  = restTemplate.getForObject("http://localhost:8002/user/get/" + cartDTO.getUserId(), UserData.class);
//        UserData userData  = restTemplate.getForObject("http://localhost:8002/user?token=" + token, UserData.class);
        if(bookData == null || userData == null){
            throw new CartException("the user not found  "+ userData + " or the book not found "+bookData);
        }
        if(bookData.getQuantity() == 0)
            throw new CartException("book not available currently");
        else {
            if (cartDTO.getQuantity() > bookData.getQuantity())
                throw new CartException("exceeded the book quantity");
            CartData cartData = new CartData(bookData, userData, cartDTO.getQuantity());
            cartRepository.save(cartData);
            ResponseDTO responseDTO = new ResponseDTO("book saved in cart ", cartData);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        }
    }

    /*************** getting cart list ***************/
    @Override
    public List<CartData> getCartList() {

        return cartRepository.findAll();
    }

    /*************** getting cart data by id ***************/
    @Override
    public CartData getCartDataById(Integer cartId) {
        return cartRepository.findById(cartId).orElseThrow(() ->
               new CartException("cart data not found with given id"));
    }

    /*************** updating cart data ***************/
    @Override
    public ResponseEntity<ResponseDTO> updateCart(Integer cartId, CartDTO cartDTO) {
        BookData bookData  = restTemplate.getForObject("http://localhost:8001/book/get/" + cartDTO.getBookId(), BookData.class);
        UserData userData  = restTemplate.getForObject("http://localhost:8002/user/get/" + cartDTO.getUserId(), UserData.class);
//        UserData userData  = restTemplate.getForObject("http://localhost:8002/user?token=" + token, UserData.class);
        if(bookData == null || userData == null){
            throw new CartException("the user not found  "+ userData + " or the book not found "+bookData);
        } else {
            CartData cartData = this.getCartDataById(cartId);
            if (cartData.equals(null)) {
                throw new CartException("no cart data present");
            } else {
                cartData.setQuantity(cartDTO.getQuantity());
                cartData.setBookData(bookData);
                cartData.setUserData(userData);
                cartRepository.save(cartData);
                ResponseDTO responseDTO = new ResponseDTO("updated cart data", cartData);
                return new ResponseEntity<>(responseDTO, HttpStatus.OK);
            }
        }
    }

    /*************** deleting cart data ***************/
    @Override
    public  ResponseEntity<ResponseDTO> deleteCartData(Integer cartId) {
        if (cartRepository.findById(cartId).isPresent()){
            cartRepository.deleteById(cartId);
            ResponseDTO responseDTO = new ResponseDTO("cart data deleted successfully with id : ",cartId);
            return new ResponseEntity<>(responseDTO, HttpStatus.OK);
        } else
            throw new CartException("cart data not found with given id ");
    }
}
