package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Book;
import com.springboot_tutorial.spring_workshop.entity.BookLoan;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@DataJpaTest
public class BookLoanRepositoryTest {

    @Autowired
    BookLoanRepository bookLoanRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    private EntityManager entityManager;


    @Test
    void findByBorrowerIdTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        Assertions.assertEquals(bookLoan, bookLoanRepository.findByBorrowerId(appUser.getId()).get());
    }

    @Test
    void findByBookIdTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        Assertions.assertEquals(bookLoan, bookLoanRepository.findByBookId(book.getId()).get());
    }

    @Test
    void findBookLoansByReturnedFalseTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        Set<BookLoan> bookLoanSet = new HashSet<>();
        bookLoanSet.add(bookLoan);

        Assertions.assertEquals(bookLoanSet, bookLoanRepository.findBookLoansByReturnedFalse());
    }

    @Test
    void findBookLoansWithPassedDueDateAndNotReturnedTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        Set<BookLoan> bookLoanSet = new HashSet<>();
        bookLoanSet.add(bookLoan);

        Assertions.assertEquals(bookLoanSet, bookLoanRepository.findBookLoansWithPassedDueDateAndNotReturned(LocalDate.now()));
    }

    @Test
    void findBookLoansByLoanDateBetweenTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        Set<BookLoan> bookLoanSet = new HashSet<>();
        bookLoanSet.add(bookLoan);

        Assertions.assertEquals(bookLoanSet, bookLoanRepository.findBookLoansByLoanDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(3)));

    }


    @Test
    void updateBookLoanReturnedTrueByBookIdTest() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Book book = new Book("isbn", "title_1", 25);
        BookLoan bookLoan = bookLoanRepository.save(new BookLoan(
                false,
                appUser,
                book ));

        bookLoanRepository.updateBookLoanReturnedTrueByBookId(1);

        entityManager.clear();


        Assertions.assertTrue(bookLoanRepository.findById(1).get().isReturned());
    }

}
