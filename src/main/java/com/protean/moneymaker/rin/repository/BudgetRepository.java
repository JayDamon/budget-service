package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface BudgetRepository extends CrudRepository<Budget, Long> {

    Set<Budget> findBudgetsByInUseTrue();

    Set<Budget> findBudgetsByInUseFalse();

    Set<Budget> findBudgetsByGenericTrue();

    Set<Budget> findBudgetsByGenericFalse();

}
