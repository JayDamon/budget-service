package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Budget;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
public interface BudgetRepository extends CrudRepository<Budget, Long> {

    Set<Budget> findBudgetsByIsInUseTrue();

    Set<Budget> findBudgetsByIsInUseFalse();

    Set<Budget> findBudgetsByIsGenericTrue();

    Set<Budget> findBudgetsByIsGenericFalse();

    @Query(value = "SELECT DISTINCT name FROM budget", nativeQuery = true)
    Set<String> findBudgetNames();

}
