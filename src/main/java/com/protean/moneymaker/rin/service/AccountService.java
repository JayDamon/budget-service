package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Account;

import java.util.List;

public interface AccountService {

    List<String> getAccountTypes(); // TODO uses AccountTypeRepository

    List<Account> getAllAccounts();

    Account saveAccount(Account account);

    List<Account> saveAccounts(List<Account> accounts);

    void removeAccountById(Long id);

    void removeAccountsById(List<Long> ids);

}
