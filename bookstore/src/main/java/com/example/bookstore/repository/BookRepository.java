package com.example.bookstore.repository;

import com.example.bookstore.domain.Book;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

@RepositoryRestRecource
public interface BookRepository extends JpaRepository<Book, Long>{
    List<Book> findByTitle(@Param("title") String title);
}
