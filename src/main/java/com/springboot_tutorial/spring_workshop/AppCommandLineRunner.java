package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Author;
import com.springboot_tutorial.spring_workshop.entity.Book;
import com.springboot_tutorial.spring_workshop.entity.Details;
import com.springboot_tutorial.spring_workshop.repository.AppUserRepository;
import com.springboot_tutorial.spring_workshop.repository.BookLoanRepository;
import com.springboot_tutorial.spring_workshop.repository.BookRepository;
import com.springboot_tutorial.spring_workshop.repository.DetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserRepository userRepository;
    DetailsRepository detailsRepository;
    BookRepository bookRepository;
    BookLoanRepository bookLoanRepository;



    public AppCommandLineRunner(AppUserRepository userRepository,
                                DetailsRepository detailsRepository,
                                BookRepository bookRepository,
                                BookLoanRepository bookLoanRepository) {
        this.userRepository = userRepository;
        this.detailsRepository = detailsRepository;
        this.bookRepository = bookRepository;
        this.bookLoanRepository = bookLoanRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Details details = detailsRepository.save(new Details("email", "name", LocalDate.now().minusYears(15)));
        AppUser appUser = userRepository.save(new AppUser("John", "Doe", details));

        //Book book = bookRepository.save(new Book("asdf", "Title", 15,  ));



    }
}
