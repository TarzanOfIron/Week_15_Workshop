package com.springboot_tutorial.spring_workshop.Entity;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Book;
import com.springboot_tutorial.spring_workshop.entity.BookLoan;
import com.springboot_tutorial.spring_workshop.repository.AppUserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class AppUserTest {

    @Autowired
    AppUserRepository appUserRepository;


    @Test
    void AddBookLoanToAppUserShouldSetTheBorrowerToo() {
        AppUser appUser = new AppUser("email", "password");
        appUserRepository.save(appUser);
        Book book = new Book("title", "author", 15, null);
        BookLoan bookLoan = new BookLoan(
                false,
                appUser,
                book);

    }
}
