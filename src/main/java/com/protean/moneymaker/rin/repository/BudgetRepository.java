package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Budget;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetRepository extends CrudRepository<Budget, Long> {
}
