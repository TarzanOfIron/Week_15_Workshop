package com.springboot_tutorial.spring_workshop.entity;

import com.springboot_tutorial.spring_workshop.repository.BookRepository;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = "bookLoans")
@ToString(exclude = "bookLoans")

@Entity
//@Table(name = "app_user")
public class AppUser {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false, unique = true)
    private String username;
    private String password;
    private LocalDate regDate;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "details_id")
    private Details userDetails;

    @OneToMany(mappedBy = "borrower", cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    private Set<BookLoan> bookLoans = new HashSet<>();


    public AppUser(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public AppUser(String username, String password, Details userDetails) {
        this(username, password);
        this.userDetails = userDetails;
    }

    @PrePersist
    public void prePersist() {
        regDate = LocalDate.now();
    }


    // Methods

    public void addBookLoan(BookLoan bookLoan, BookRepository bookRepository) {
        if (bookLoan.getBook() == null || !bookLoan.getBook().isAvailable()) {
            System.out.println("Book is not available");
            return;
        }
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
        bookLoan.getBook().setAvailable(false);
        bookRepository.save(bookLoan.getBook());
    }

    public void removeBookLoan(BookLoan bookLoan, BookRepository bookRepository) {
        bookLoans.remove(bookLoan);
        bookLoan.setBorrower(null);
        bookLoan.getBook().setAvailable(true);
        bookRepository.save(bookLoan.getBook());
    }

}
