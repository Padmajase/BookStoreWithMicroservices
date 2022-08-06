package com.example.BookModule.service;

import com.example.BookModule.dto.BookDTO;
import com.example.BookModule.dto.ResponseDTO;
import com.example.BookModule.model.BookModuleData;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface IBookService {
    BookModuleData addBook(BookDTO bookDTO);

    List<BookModuleData> getBookList();

    void deleteBookData(int bookId);

    ResponseEntity<ResponseDTO> updateBookById(int bookId, BookDTO bookDTO);


    Optional<BookModuleData> getBookById(int bookId);
}
