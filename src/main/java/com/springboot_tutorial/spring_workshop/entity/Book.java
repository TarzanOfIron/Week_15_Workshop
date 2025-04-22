package com.springboot_tutorial.spring_workshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "authors")
@ToString(exclude = "authors")

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true)
    private String isbn;
    private String title;
    private int maxLoanDays;
    @ManyToMany(fetch = FetchType.EAGER, cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinTable(
            name = "Books_Authors",
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            joinColumns = @JoinColumn(name = "Author_id")
    )
    private Set<Author> authors = new HashSet<>();


    public Book(String isbn, String title, int maxLoanDays) {
        this.isbn = isbn;
        this.title = title;
        this.maxLoanDays = maxLoanDays;
    }

    public Book(String isbn, String title, int maxLoanDays, Set<Author> authors) {
        this(isbn, title, maxLoanDays);
        this.authors = authors;
    }

    // Methods

    public void addAuthor(Author author) {
        authors.add(author);
        author.getWrittenBooks().add(this);
    }

    public void removeAuthor(Author author) {
        authors.remove(author);
        author.getWrittenBooks().remove(this);
    }
}
