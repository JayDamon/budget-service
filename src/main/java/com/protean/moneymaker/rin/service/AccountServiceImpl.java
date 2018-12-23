package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountType;
import com.protean.moneymaker.rin.repository.AccountRepository;
import com.protean.moneymaker.rin.repository.AccountTypeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountTypeRepository accountTypeRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountTypeRepository accountTypeRepository) {
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
    }

    @Override
    public List<AccountType> getAccountTypes() {

        List<AccountType> accountTypes = new ArrayList<>();
        accountTypeRepository.findAll().forEach(accountTypes::add);

        return accountTypes;
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = new ArrayList<>();
        accountRepository.findAll().forEach(accounts::add);

        return accounts;
    }

    @Override
    public Account saveAccount(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> saveAccounts(List<Account> accounts) {

        List<Account> savedAccounts = new ArrayList<>();
        accountRepository.saveAll(accounts).forEach(savedAccounts::add);

        return savedAccounts;
    }

    @Override
    public void deleteAccount(Account account) {
        accountRepository.delete(account);
    }

    @Override
    public void deleteAccounts(List<Account> accounts) {
        accountRepository.deleteAll(accounts);
    }
}
