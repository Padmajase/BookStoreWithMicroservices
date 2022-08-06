package com.example.BookModule.repository;

import com.example.BookModule.model.BookModuleData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<BookModuleData, Integer> {
//    Optional<BookData> findByBookId(int bookId);
}
