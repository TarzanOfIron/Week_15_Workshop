package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Details;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.LocalDate;

@DataJpaTest
public class DetailsRepositoryTest {

    @Autowired
    DetailsRepository detailsRepository;

    @Test
    void saveDetails() {
        Details details = detailsRepository.save(new Details(
                "email",
                "name",
                LocalDate.now().minusYears(15).minusDays(2)));

        Assertions.assertTrue(detailsRepository.findById(details.getId()).isPresent());
    }


}
