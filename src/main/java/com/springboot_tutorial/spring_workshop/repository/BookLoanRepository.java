package com.springboot_tutorial.spring_workshop.repository;

import com.springboot_tutorial.spring_workshop.entity.BookLoan;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;
import java.util.Set;

@Repository
public interface BookLoanRepository extends CrudRepository<BookLoan, Integer> {

    Optional<BookLoan> findByBorrowerId(Integer borrowerId);
    BookLoan findByBookId(Integer bookId);
    Set<BookLoan> findBookLoansByReturnedFalse();

    @Query("SELECT bl FROM BookLoan bl WHERE bl.dueDate < :today AND bl.returned = false")
    Set<BookLoan> findBookLoansWithPassedDueDateAndNotReturned(@Param("today") LocalDate today);
    Set<BookLoan> findBookLoansByLoanDateBetween(LocalDate from, LocalDate to);

    @Modifying
    @Transactional
    @Query("UPDATE BookLoan bl SET bl.returned = true WHERE bl.book.id = :bookId")
    void updateBookLoanReturnedTrueByBookId(@Param("bookId") Integer bookId);


}
