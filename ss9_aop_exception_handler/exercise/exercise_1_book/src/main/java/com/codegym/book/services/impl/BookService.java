package com.codegym.book.services.impl;

import com.codegym.book.model.Book;
import com.codegym.book.repository.IBookRepository;
import com.codegym.book.services.IBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class BookService implements IBookService {
    @Autowired
    private IBookRepository bookRepository;

    @Override
    public Page<Book> findAll(Pageable pageable, String keyword) {
        return bookRepository.findAllByNameContaining(keyword,pageable);
    }

    @Override
    public void save(Book book) {
        bookRepository.save(book);
    }

    @Override
    public Book findById(int id) {
        return bookRepository.findById(id).orElse(null);
    }

    @Override
    public void update(Book product) {
        bookRepository.save(product);
    }

    @Override
    public void remove(int id) {
        bookRepository.delete(findById(id));
    }
}
