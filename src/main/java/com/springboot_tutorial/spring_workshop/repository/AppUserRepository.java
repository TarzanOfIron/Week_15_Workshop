package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findByUsername(String username);
    AppUser findByRegDateBetween(LocalDate regDateAfter, LocalDate regDateBefore);
    AppUser findByUserDetailsId(Integer detailId);
    AppUser findByUserDetailsEmailIgnoreCase(String email);



}
