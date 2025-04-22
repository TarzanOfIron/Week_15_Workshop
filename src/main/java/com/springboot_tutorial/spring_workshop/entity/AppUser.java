package com.springboot_tutorial.spring_workshop.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@ToString

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

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "bookLoan_id")
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

    public void addBookLoan(BookLoan bookLoan) {
        bookLoans.add(bookLoan);
        bookLoan.setBorrower(this);
    }

}
