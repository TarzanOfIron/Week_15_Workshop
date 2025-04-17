package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Book;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@Repository
public interface BookRepository extends CrudRepository<Book, Integer> {

    Optional<Book> findByIsbnIgnoreCase(String isbn);
    Set<Book> findByTitleContains(String title);
    Set<Book> findByMaxLoanDaysLessThan(int maxLoanDays);


}
