package com.task.ING.repository;

import com.task.ING.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {

    @Query(value = "SELECT t from Transaction t WHERE (t.toAccount = :accountId OR t.fromAccount = :accountId) and t.createdDate between :localDateTime and :now")
    List<Transaction> findByFromAccountOrToAccount(Integer accountId, LocalDateTime localDateTime, LocalDateTime now);
}
