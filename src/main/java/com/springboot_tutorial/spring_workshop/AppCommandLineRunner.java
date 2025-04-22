package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.*;
import com.springboot_tutorial.spring_workshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    @Autowired
    AuthorRepository authorRepository;
    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;
    @Autowired
    BookRepository bookRepository;
    @Autowired
    BookLoanRepository bookLoanRepository;


    @Override
    public void run(String... args) throws Exception {
        AppUser appUser = new AppUser("John", "Doe", new Details("email", "name", LocalDate.now().minusYears(15)));
        Book book = new Book("isbn", "Title", 15, null);
        BookLoan bookLoan = new BookLoan(
                LocalDate.now().minusDays(1),
                LocalDate.now().plusDays(1),
                false,
                null,
                book);


        appUser = appUserRepository.save(appUser);
        System.out.println(appUser);
        System.out.println("=====================================================");
        System.out.println("=====================================================");



    }

}
