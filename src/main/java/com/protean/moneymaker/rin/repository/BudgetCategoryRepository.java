package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.BudgetCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetCategoryRepository extends JpaRepository<BudgetCategory, Integer> {
}
