package com.example.BookServer.service;

import com.example.BookServer.dto.BookDTO;
import com.example.BookServer.dto.ResponseDTO;
import com.example.BookServer.model.BookData;
import com.example.BookServer.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements IBookService {

    @Autowired
    private BookRepository bookRepository;
    @Override
    public BookData addBook(BookDTO bookDTO) {
        BookData bookData = new BookData(bookRepository.findAll().size() + 1, bookDTO);
        bookRepository.save(bookData);
        return bookData;
    }

    @Override
    public List<BookData> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookData(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateBookById(int bookId, BookDTO bookDTO) {
        List<BookData> bookDataList = this.getBookList();
        for (BookData bookData : bookDataList) {
            if(bookData.getBookId() == bookId) {
                bookData.setBookName(bookDTO.bookName);
                bookData.setBookAuthor(bookDTO.bookAuthor);
                bookData.setBookPrice(bookDTO.bookPrice);
                bookData.setQuantity(bookDTO.quantity);

                bookRepository.save(bookData);
                ResponseDTO respDTO = new ResponseDTO("Book Data Updated Successfully ", bookData);
                return new ResponseEntity<>(respDTO, HttpStatus.OK);
            } else {
                ResponseDTO respDTO = new ResponseDTO("Book not present with given Id ", bookId);
                return new ResponseEntity<>(respDTO, HttpStatus.OK);
            }
        }
        return null;
    }

    @Override
    public Optional<BookData> getBookById(int bookId) {
        return bookRepository.findByBookId(bookId);
    }
}
