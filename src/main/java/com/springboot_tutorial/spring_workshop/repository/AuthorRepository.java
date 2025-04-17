package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.Author;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface AuthorRepository extends CrudRepository<Author, Integer> {

    Set<Author> findByFirstName(String firstName);
    Set<Author> findByLastName(String lastName);
    Set<Author> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String keyword1, String keyword2);

    @Query("SELECT a FROM Author a JOIN a.writtenBooks b WHERE b.id = :bookId ")
    Set<Author> findByBookId(@Param("bookId") int bookId);

    @Transactional
    @Modifying
    @Query("UPDATE Author a SET a.firstName = :newFirstName, a.lastName =  :newLastName WHERE a.id = :id")
    void updateNameById(@Param("id") Integer id, @Param("newFirstName") String newFirstName, @Param("newLastName") String newLastName);
}
