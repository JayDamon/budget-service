package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.AccountClassification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountClassificationRepository extends JpaRepository<AccountClassification, Integer> {
}
