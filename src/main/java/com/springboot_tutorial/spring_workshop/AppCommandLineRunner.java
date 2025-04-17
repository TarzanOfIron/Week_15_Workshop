package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Author;
import com.springboot_tutorial.spring_workshop.entity.Book;
import com.springboot_tutorial.spring_workshop.entity.Details;
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


//    public AppCommandLineRunner(AppUserRepository appUserRepository,
//                                DetailsRepository detailsRepository,
//                                BookRepository bookRepository,
//                                BookLoanRepository bookLoanRepository,
//                                AuthorRepository authorRepository) {
//        this.appUserRepository = appUserRepository;
//        this.detailsRepository = detailsRepository;
//        this.bookRepository = bookRepository;
//        this.bookLoanRepository = bookLoanRepository;
//        this.authorRepository = authorRepository;
//    }

    @Override
    public void run(String... args) throws Exception {
        AppUser appUser = new AppUser("John", "Doe", new Details("email", "name", LocalDate.now().minusYears(15)));
        appUser = appUserRepository.save(appUser);
//        Author author = authorRepository.save(new Author("firstName", "lastName"));
//        Book book = new Book("asdf", "Title", 15);
//        book.addAuthor(author);
//        book = bookRepository.save(book);


    }
}
