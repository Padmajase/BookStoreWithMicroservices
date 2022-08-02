package com.example.CartServer.controller;
import com.example.CartServer.dto.ResponseDTO;
import com.example.CartServer.model.CartData;
import com.example.CartServer.rabbitmq.CartRabbit;
import com.example.CartServer.rabbitmq.MessageConfig;
import com.example.CartServer.service.ICartService;
import com.example.CartServer.token.TokenUtil;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;

/*************** getting cart list and placing order for books in cart ***************/
@RestController
@RequestMapping("/cart")
public class CartController {

    /*************** injecting Cart Interface Object ***************/
    @Autowired
    private ICartService cartService;

    /*************** injecting token util object here ***************/
    @Autowired
    private TokenUtil tokenUtil;

    /*************** injecting Rabbit Template Object ***************/
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /*************** inserting book into cart ***************/
    @PostMapping("/save/{quantity}")
    public ResponseEntity<ResponseDTO> saveBooksToCart(@PathVariable int quantity, @RequestParam("bookId") int bookId, @RequestParam("id") int userId) throws Exception {
            String token = tokenUtil.createToken(userId);
        ResponseEntity<ResponseDTO> cartRabbit = cartService.saveBooksToCart(quantity, bookId, token);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, cartRabbit);
        return cartRabbit;
        }

    /*************** updating book quantity in cart ***************/
    @PutMapping("/updateQuantity/{quantity}")
    public ResponseEntity<ResponseDTO> updateBookQuantity(@PathVariable int quantity, @RequestParam("bookId") int cartBookId){
        return cartService.updateBookQuantity(quantity, cartBookId);
   }

    /*************** deleting book quantity in cart ***************/
    @DeleteMapping("/deleteBook/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBook(@PathVariable("bookId")int bookId){
        return cartService.deleteBookFromCart(bookId);
//        ResponseDTO responseDTO = new ResponseDTO("Your Cart :",bookListInCart);
//        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** getting book list in cart ***************/
    @GetMapping(value={"", "/", "get"})
    public ResponseEntity<ResponseDTO> getCartList(){
        List<CartData> bookListInCart = null;
        bookListInCart = cartService.getBookListInCart();
        ResponseDTO responseDTO = new ResponseDTO("Your Cart :",bookListInCart);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}


