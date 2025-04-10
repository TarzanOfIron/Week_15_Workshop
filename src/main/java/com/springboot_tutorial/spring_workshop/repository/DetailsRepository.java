package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Details;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DetailsRepository extends CrudRepository<Details, Integer> {

    Details findByEmail(String email);
    Set<Details> findByNameContains(String name);
    Details findByNameIgnoreCase(String name);

}


