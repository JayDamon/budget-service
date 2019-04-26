package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.BudgetSubCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface BudgetCategoryRepository extends CrudRepository<BudgetSubCategory, Long> {
}
