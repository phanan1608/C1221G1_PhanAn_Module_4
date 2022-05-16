package com.codegym.book.model;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
public class RentId {
    @Id
    @GenericGenerator(name = "random_5_number_id", strategy = "com.codegym.book.utils.MyGenerator")
    @GeneratedValue(generator = "random_5_number_id")
    private Long rentId;

    @ManyToOne
    @JoinColumn(name = "book_id",referencedColumnName = "id")
    private Book bookId;

    public RentId() {
    }

    public Long getRentId() {
        return rentId;
    }

    public void setRentId(Long rentId) {
        this.rentId = rentId;
    }

    public Book getBookId() {
        return bookId;
    }

    public void setBookId(Book bookId) {
        this.bookId = bookId;
    }
}
