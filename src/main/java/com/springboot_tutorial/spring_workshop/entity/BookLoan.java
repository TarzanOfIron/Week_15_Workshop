package com.springboot_tutorial.spring_workshop.entity;


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString(exclude = {"borrower", "book"})

@Entity
public class BookLoan {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private LocalDate loanDate;
    private LocalDate dueDate;
    private boolean returned;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "borrower")
    private AppUser borrower;

    @ManyToOne(fetch = FetchType.EAGER)//, cascade = CascadeType.ALL)
    @JoinColumn(name = "book_id")
    private Book book;

    public BookLoan( boolean returned, AppUser borrower, Book book) {
        this.returned = returned;
        this.borrower = borrower;
        this.book = book;
    }

    @PrePersist
    private void prePersist() {
        this.loanDate = LocalDate.now();
        this.dueDate = LocalDate.now().plusDays(book.getMaxLoanDays());
    }

//    public void setBook(Book book) {
//        if (!book.isAvailable()) {
//            System.out.println("Book is not available");
//            return;
//        }
//        this.book = book;
//
//    }
}
