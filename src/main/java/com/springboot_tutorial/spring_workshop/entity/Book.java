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
    @ManyToMany
    @JoinTable(
            name = "Books_Authors",
            inverseJoinColumns = @JoinColumn(name = "book_id"),
            joinColumns = @JoinColumn(name = "Author_id")
    )
    private Set<Author> authors;
}
