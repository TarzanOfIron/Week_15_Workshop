package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Book;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;


    @Test
    void findByIsbnIgnoreCase() {
        Book book = bookRepository.save(new Book("isbn", "Title", 56));
        Assertions.assertEquals(book, bookRepository.findByIsbnIgnoreCase("isbn").get());
    }

    @Test
    void findByTitleContains() {
        Book book = bookRepository.save(new Book("isbn", "Title", 56));
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book);
        Assertions.assertEquals(bookSet, bookRepository.findByTitleContains("Ti"));
    }


    @Test
    void findByMaxLoanDaysLessThan() {
        Book book = bookRepository.save(new Book("isbn", "Title", 56));
        Set<Book> bookSet = new HashSet<>();
        bookSet.add(book);

        Assertions.assertEquals(bookSet, bookRepository.findByMaxLoanDaysLessThan(500));
    }

}
