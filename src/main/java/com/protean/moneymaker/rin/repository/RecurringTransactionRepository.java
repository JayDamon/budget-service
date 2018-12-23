package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.RecurringTransaction;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface RecurringTransactionRepository extends CrudRepository<RecurringTransaction, Long> {
}
