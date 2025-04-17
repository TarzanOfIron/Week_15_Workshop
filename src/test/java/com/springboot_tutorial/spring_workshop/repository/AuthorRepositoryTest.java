package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Author;
import com.springboot_tutorial.spring_workshop.entity.Book;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@DataJpaTest
public class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    private EntityManager entityManager;

    Author author;

    @BeforeEach
    void setUp() {
        //author = authorRepository.save(new Author("John", "Doe"));
        //Set<Author> authorSet = new HashSet<>();
    }

    @Test
    void findByFirstName() {
        Set<Author> authorSet = new HashSet<>();
        author = authorRepository.save(new Author("John", "Doe"));
        authorSet.add(author);

        Assertions.assertEquals(authorSet, authorRepository.findByFirstName("John"));
    }

    @Test
    void findByLastName() {
        Set<Author> authorSet = new HashSet<>();
        author = authorRepository.save(new Author("John", "Doe"));
        authorSet.add(author);

        Assertions.assertEquals(authorSet, authorRepository.findByLastName("Doe"));
    }

    @Test
    void findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase() {
        Set<Author> authorSet = new HashSet<>();
        author = authorRepository.save(new Author("John", "Doe"));
        authorSet.add(author);

        Assertions.assertEquals(authorSet, authorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase("Jo", "D"));
    }

    @Test
    void findByBookId() {
        Set<Author> authorSet = new LinkedHashSet<>();
        authorSet.add(new Author("Adam", "Smith"));
        authorSet.add(new Author("Attila", "Smith"));

        Book book = new Book("isbn", "BookTitle", 45, authorSet);
        System.out.println(book);

        bookRepository.save(book);

        Assertions.assertEquals(authorSet.size(), authorRepository.findByBookId(1).size());
    }


    @Test
    void updateNameById() {
        author = authorRepository.save(new Author("John", "Doe"));
        authorRepository.updateNameById(1, "Adam", "Smith");
        entityManager.clear();
        Assertions.assertEquals("Adam", authorRepository.findById(1).get().getFirstName());


    }

}
