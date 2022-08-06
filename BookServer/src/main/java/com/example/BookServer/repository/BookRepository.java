package com.example.BookServer.repository;

import com.example.BookServer.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BookRepository extends JpaRepository <BookData, Integer> {
//    Optional<BookData> findByBookId(int bookId);
}
