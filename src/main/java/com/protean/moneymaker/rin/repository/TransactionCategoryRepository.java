package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.TransactionCategory;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionCategoryRepository extends CrudRepository<TransactionCategory, Long> {
}
