package com.example.BookModule.service;

import com.example.BookModule.dto.BookDTO;
import com.example.BookModule.dto.ResponseDTO;
import com.example.BookModule.model.BookModuleData;
import com.example.BookModule.repository.BookRepository;
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
    public BookModuleData addBook(BookDTO bookDTO) {
//        BookData bookData = new BookData(  bookDTO);
        BookModuleData bookData = new BookModuleData(bookDTO);
        bookRepository.save(bookData);
        return bookData;
    }

    @Override
    public List<BookModuleData> getBookList() {
        return bookRepository.findAll();
    }

    @Override
    public void deleteBookData(int bookId) {
        bookRepository.deleteById(bookId);
    }

    @Override
    public ResponseEntity<ResponseDTO> updateBookById(int bookId, BookDTO bookDTO) {
        List<BookModuleData> bookDataList = this.getBookList();
        for (BookModuleData bookData : bookDataList) {
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
    public Optional<BookModuleData> getBookById(int bookId) {
        return bookRepository.findById(bookId);
    }
}
