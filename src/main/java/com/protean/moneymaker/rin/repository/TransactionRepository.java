package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.ZonedDateTime;
import java.util.Set;

@Repository
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    Set<Transaction> findAllByOrderByDateDesc();

    Set<Transaction> findAllByDateAfterAndDateBefore(ZonedDateTime startDate, ZonedDateTime after);

}
