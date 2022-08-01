package com.example.BookServer.service;

import com.example.BookServer.dto.BookDTO;
import com.example.BookServer.dto.ResponseDTO;
import com.example.BookServer.model.BookData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    BookData addBook(BookDTO bookDTO);

    List<BookData> getBookList();

    void deleteBookData(int bookId);

    ResponseEntity<ResponseDTO> updateBookById(int bookId, BookDTO bookDTO);


    Optional<BookData> getBookById(int bookId);
}
