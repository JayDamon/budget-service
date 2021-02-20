package com.factotum.rin.repository;

import com.factotum.rin.model.RecurringTransaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecurringTransactionRepository extends CrudRepository<RecurringTransaction, Long> {
}
