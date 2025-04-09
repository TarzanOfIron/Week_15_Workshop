package com.springboot_tutorial.spring_workshop;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.repository.AppUserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class AppCommandLineRunner implements CommandLineRunner {

    AppUserRepository userRepository;

    public AppCommandLineRunner(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        AppUser appUser = new AppUser("John", "Doe");
        appUser = userRepository.save(appUser);
        System.out.println(appUser);
    }
}
