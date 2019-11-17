package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Budget;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface BudgetRepository extends JpaRepository<Budget, Long> {

    Set<Budget> findBudgetsByInUseTrue();

    Set<Budget> findBudgetsByInUseFalse();

}
