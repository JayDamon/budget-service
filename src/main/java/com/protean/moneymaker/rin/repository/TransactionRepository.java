package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

    List<Transaction> findAllByOrderByDateDesc();

}
