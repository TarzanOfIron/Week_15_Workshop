package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Details;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;
import java.util.HashSet;

@DataJpaTest
public class DetailsRepositoryTest {

    @Autowired
    DetailsRepository detailsRepository;

    Details details;


    @BeforeEach
    void setUp() {
        details = detailsRepository.save(new Details(
                "email",
                "name",
                LocalDate.now().minusYears(15).minusDays(2)));
    }

    @Test
    void saveDetails() {
        Assertions.assertTrue(detailsRepository.findById(details.getId()).isPresent());
    }

    @Test
    void findByEmail() {
        Assertions.assertEquals(details, detailsRepository.findByEmail("email"));
    }

    @Test
    void findByNameContains() {
        HashSet<Details> detailsSet = new HashSet<>();
        detailsSet.add(details);
        Assertions.assertEquals(detailsSet, detailsRepository.findByNameContains("na"));
    }

    @Test
    void findByNameIgnoreCase() {
        Assertions.assertEquals(details, detailsRepository.findByNameIgnoreCase("NAME"));
    }


}
