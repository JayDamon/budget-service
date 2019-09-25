package com.protean.moneymaker.rin.repository;

import com.protean.moneymaker.rin.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {
}
