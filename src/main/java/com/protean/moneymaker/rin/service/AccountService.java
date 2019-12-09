package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.AccountDto;
import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountClassification;
import com.protean.moneymaker.rin.model.AccountType;

import java.util.List;

public interface AccountService {

    List<AccountType> getAccountTypes(); // TODO uses AccountTypeRepository

    List<Account> getAllAccounts();

    Account getAccountById(long accountId);

    Account saveAccount(Account account);

    List<Account> saveAccounts(List<Account> accounts);

    void deleteAccount(Account id);

    void deleteAccounts(List<Account> ids);

    Account updateAccount(AccountDto accountDto);

    Account createAccount(AccountDto account);

    List<AccountClassification> getAccountClassifications();

}
