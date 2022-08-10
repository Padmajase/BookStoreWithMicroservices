package com.example.BookServer.service;

import com.example.BookServer.dto.BookDTO;
import com.example.BookServer.dto.ResponseDTO;
import com.example.BookServer.exception.BookException;
import com.example.BookServer.model.BookData;
import com.example.BookServer.repository.BookRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookData addBook(BookDTO bookDTO) {
        BookData book = new BookData(bookDTO);
        bookRepository.save(book);
        return  book;
    }

    @Override
    public List<BookData> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public BookData getBookById(Integer bookId) {
        return bookRepository.findById(bookId).orElseThrow(()
                -> new BookException("book with id"+bookId + "does not exist"));
    }
    @Override
    public ResponseEntity<ResponseDTO> updateBookById(Integer bookId, BookDTO bookDTO) {
        BookData book = this.getBookById(bookId);
        if(book == null) {
            ResponseDTO respDTO = new ResponseDTO("book not present with given Id ", bookId);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        } else {
            book.updateBook(bookDTO);
            bookRepository.save(book);
            ResponseDTO respDTO = new ResponseDTO("User Details Updated Successfully ", book);
            return new ResponseEntity<>(respDTO, HttpStatus.OK);
        }
    }

    @Override
    public void deleteBookData(Integer bookId) {
        bookRepository.deleteById(bookId);
    }
}
