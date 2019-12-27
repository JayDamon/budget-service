package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.AccountDto;
import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountClassification;
import com.protean.moneymaker.rin.model.AccountType;
import com.protean.moneymaker.rin.repository.AccountClassificationRepository;
import com.protean.moneymaker.rin.repository.AccountRepository;
import com.protean.moneymaker.rin.repository.AccountTypeRepository;
import com.protean.moneymaker.rin.util.AccountUtil;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import java.util.ArrayList;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    private AccountRepository accountRepository;
    private AccountTypeRepository accountTypeRepository;
    private AccountClassificationRepository accountClassificationRepository;

    public AccountServiceImpl(AccountRepository accountRepository,
                              AccountTypeRepository accountTypeRepository, AccountClassificationRepository accountClassificationRepository) {
        this.accountRepository = accountRepository;
        this.accountTypeRepository = accountTypeRepository;
        this.accountClassificationRepository = accountClassificationRepository;
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
    public Account getAccountById(long accountId) {

        if (accountId <= 0) {
            throw new IllegalArgumentException("Account ID must be greater than zero, but was <" + accountId + ">");
        }

        return accountRepository.findById(accountId).orElseThrow(() -> new NoResultException("Account with id <" + accountId + "> could not be found"));
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

    @Override
    public Account updateAccount(AccountDto accountDto) {

        if (accountDto == null) {
            throw new IllegalArgumentException("Account must not be null");
        }
        if (accountDto.getId() == null) {
            throw new IllegalArgumentException("A valid account id must be provided");
        }

        Account a = this.getAccountById(accountDto.getId());

        a = AccountUtil.mapDtoToEntityOnlyNonNull(accountDto, a);

        return accountRepository.saveAndFlush(a);
    }

    @Override
    public Account createAccount(AccountDto account) {

        if (account == null) {
            throw new IllegalArgumentException("Valid account dto must be provided");
        }

        Account a = AccountUtil.mapDtoToEntityOnlyNonNull(account);

        return accountRepository.saveAndFlush(a);

    }

    @Override
    public List<AccountClassification> getAccountClassifications() {

        return accountClassificationRepository.findAll();

    }
}
