package com.springboot_tutorial.spring_workshop.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String isbn;
    private String title;
    private int maxLoanDays;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name = "Books_Authors",
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            joinColumns = @JoinColumn(name = "Author_id")
    )
    private Set<Author> authors;


    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Book(String isbn, String title, int maxLoanDays, Set<Author> authors) {
        this(isbn, title, maxLoanDays);
        this.authors = authors;
    }

    public void addAuthor(Author author) {
        authors.add(author);
    }
}
