package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.*;
import com.springboot_tutorial.spring_workshop.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.HashSet;

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
        Book book = new Book("isbn", "Title", 15, new HashSet<>());
        bookRepository.save(book);
        Author author = new Author("Johan", "Dadd");
        book.addAuthor(author);
        BookLoan bookLoan = new BookLoan(
                false,
                null,
                book);


        appUser.addBookLoan(bookLoan, bookRepository);
        appUser = appUserRepository.save(appUser);
        System.out.println(appUser);
        System.out.println("=====================================================");
        System.out.println("=====================================================");

        //authorRepository.save(author);

        appUser.getBookLoans().forEach(System.out::println);
        System.out.println(bookRepository.findById(1).get().isAvailable());

        BookLoan bookLoan2 = new BookLoan(
                false,
                null,
                book);

        appUser.addBookLoan(bookLoan2, bookRepository);
        appUser.removeBookLoan(bookLoan, bookRepository);
        appUser.addBookLoan(bookLoan2, bookRepository);
        appUser.getBookLoans().forEach(System.out::println);


    }

}
