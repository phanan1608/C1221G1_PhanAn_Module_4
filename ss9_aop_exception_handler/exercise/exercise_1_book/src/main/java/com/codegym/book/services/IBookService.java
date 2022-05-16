package com.codegym.book.services;

import com.codegym.book.model.Book;
import com.codegym.book.repository.IBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface IBookService {
    Page<Book> findAll(Pageable pageable, String keyword);

    void save(Book book);

    Book findById(int id);

    void update(Book product);

    void remove(int id);
}
