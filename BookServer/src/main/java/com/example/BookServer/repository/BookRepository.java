package com.example.BookServer.repository;

import com.example.BookServer.model.BookData;
import org.springframework.data.jpa.repository.JpaRepository;


public interface BookRepository extends JpaRepository <BookData, Integer> {
}
