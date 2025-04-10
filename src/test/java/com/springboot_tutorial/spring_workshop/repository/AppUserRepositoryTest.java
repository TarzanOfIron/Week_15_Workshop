package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import com.springboot_tutorial.spring_workshop.entity.Details;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;

@DataJpaTest
public class AppUserRepositoryTest {

    @Autowired
    AppUserRepository appUserRepository;
    @Autowired
    DetailsRepository detailsRepository;

    @Test
    void saveStudent() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Assertions.assertTrue(appUserRepository.findById(appUser.getId()).isPresent());
    }


    @Test
    void findByUsername() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        Assertions.assertEquals(appUser, appUserRepository.findByUsername("John"));
    }

    @Test
    void findByRegDateBetween() {
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe"));
        HashSet<AppUser> appUsers = new HashSet<>();
        appUsers.add(appUser);
        HashSet<AppUser> appUsersFromFindMethod = new HashSet<>(appUserRepository.findByRegDateBetween(LocalDate.now().minusDays(1), LocalDate.now().plusDays(1)));
        Assertions.assertEquals(appUsers, appUsersFromFindMethod);

    }


    @Test
    void findByUserDetailsId() {
        Details details = detailsRepository.save(new Details("email", "name", LocalDate.now().minusYears(15).minusDays(2)));
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe", details));
        Assertions.assertEquals(appUser, appUserRepository.findByUserDetails_Id(details.getId()));
    }

    @Test
    void findByUserDetailsEmailIgnoreCase() {
        Details details = detailsRepository.save(new Details("email", "name", LocalDate.now().minusYears(15).minusDays(2)));
        AppUser appUser = appUserRepository.save(new AppUser("John", "Doe", details));
        Assertions.assertEquals(appUser, appUserRepository.findByUserDetailsEmailIgnoreCase("email"));
    }
}
