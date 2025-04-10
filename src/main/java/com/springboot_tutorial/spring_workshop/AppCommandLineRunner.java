package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Details;
import com.springboot_tutorial.spring_workshop.repository.AppUserRepository;
import com.springboot_tutorial.spring_workshop.repository.DetailsRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserRepository userRepository;

    DetailsRepository detailsRepository;


    public AppCommandLineRunner(AppUserRepository userRepository, DetailsRepository detailsRepository) {
        this.userRepository = userRepository;
        this.detailsRepository = detailsRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Details details = detailsRepository.save(new Details("email", "name", LocalDate.now().minusYears(15)));
        AppUser appUser = userRepository.save(new AppUser("John", "Doe", details));




        System.out.println(appUser);
    }
}
