package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.RecurringTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringTransactionRepository extends CrudRepository<RecurringTransaction, Long> {
}
