package com.codegym.book.repository;

import com.codegym.book.model.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IBookRepository extends JpaRepository<Book,Integer> {
    Page<Book> findAllByNameContaining(String keyword, Pageable pageable);

}
