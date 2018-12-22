package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.repository.AccountRepository;
import org.springframework.stereotype.Service;

import java.util.List;

public interface AccountService {

    List<String> getAccountTypes();

    Account saveAccount(Account account);

    List<Account> saveAccounts(List<Account> accounts);

}
