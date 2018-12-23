package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountType;

import java.util.List;

public interface AccountService {

    List<AccountType> getAccountTypes(); // TODO uses AccountTypeRepository

    List<Account> getAllAccounts();

    Account saveAccount(Account account);

    List<Account> saveAccounts(List<Account> accounts);

    void deleteAccount(Account id);

    void deleteAccounts(List<Account> ids);

}
