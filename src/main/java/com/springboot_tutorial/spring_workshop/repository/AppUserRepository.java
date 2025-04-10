package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.AppUser;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Set;

@Repository
public interface AppUserRepository extends CrudRepository<AppUser, Integer> {

    AppUser findByUsername(String username);
    Set<AppUser> findByRegDateBetween(LocalDate regDateAfter, LocalDate regDateBefore);
    AppUser findByUserDetails_Id(Integer detailId);
    AppUser findByUserDetailsEmailIgnoreCase(String email);



}
