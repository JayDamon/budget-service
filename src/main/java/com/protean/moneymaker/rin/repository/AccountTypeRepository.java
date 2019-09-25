package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.AccountType;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountTypeRepository extends CrudRepository<AccountType, Integer> {
}
