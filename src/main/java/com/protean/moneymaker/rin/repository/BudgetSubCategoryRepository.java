package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.BudgetSubCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BudgetSubCategoryRepository extends JpaRepository<BudgetSubCategory, Long> {
}
