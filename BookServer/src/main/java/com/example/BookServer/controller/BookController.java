package com.example.BookServer.controller;

import com.example.BookServer.dto.BookDTO;
import com.example.BookServer.dto.ResponseDTO;
import com.example.BookServer.model.BookData;
import com.example.BookServer.rabbitmq.MessageConfig;
import com.example.BookServer.service.IBookService;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/*
giving permission for
accessing book list,
adding book,
deleting book,
update book details.
*/
@RestController
@RequestMapping("/book")
public class BookController {

    /*************** injecting Book Interface Object ***************/
    @Autowired
    private IBookService bookInterface;

    /*************** injecting Rabbit Template Object ***************/
    @Autowired
    RabbitTemplate rabbitTemplate;

    @PostMapping("/addbook/")
    public ResponseEntity<ResponseDTO> addBookInStore(@RequestBody BookDTO bookDTO) {
        BookData bookData;
        bookData = bookInterface.addBook(bookDTO);
        ResponseDTO responseDTO = new ResponseDTO("Book Added With Details : ", bookData);
        rabbitTemplate.convertAndSend(MessageConfig.EXCHANGE, MessageConfig.ROUTING_KEY, responseDTO);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }


    /*************** getting book list in book store ***************/
    @GetMapping(value = {"","/","/bookList/"})
    public ResponseEntity<ResponseDTO> getBookList() {
        List<BookData> listOfBook;
        listOfBook = bookInterface.getBookList();
        ResponseDTO responseDTO = new ResponseDTO("Books Available :", listOfBook);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** getting book by id in book store ***************/
    @GetMapping("/getById/{bookId}")
    public ResponseEntity<ResponseDTO> getBookById(@PathVariable("bookId") int bookId) {
        Optional<BookData> bookData;
        bookData = bookInterface.getBookById(bookId);
        ResponseDTO responseDTO = new ResponseDTO("Books details with given id is : ", bookData);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }

    /*************** update book by ID ***************/
    @PutMapping("/update/{bookId}")
    public ResponseEntity<ResponseDTO> updateBookData(@PathVariable("bookId") int bookId,
                                                      @RequestBody BookDTO bookDTO){
        return  bookInterface.updateBookById(bookId, bookDTO);
    }

    /*************** delete book by its Id ***************/
    @DeleteMapping("/deletebook/{bookId}")
    public ResponseEntity<ResponseDTO> deleteBookById(@PathVariable int bookId){
        bookInterface.deleteBookData(bookId);
        ResponseDTO respDTO = new ResponseDTO("Book Deleted Successfully", "with book Id : " +bookId);
        return new ResponseEntity<>(respDTO, HttpStatus.OK);
    }
}
