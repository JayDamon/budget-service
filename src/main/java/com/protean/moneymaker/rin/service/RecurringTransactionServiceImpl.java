package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.RecurringTransaction;
import com.protean.moneymaker.rin.repository.RecurringTransactionRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RecurringTransactionServiceImpl implements RecurringTransactionService {

    private RecurringTransactionRepository recurringTransactionRepository;

    public RecurringTransactionServiceImpl(RecurringTransactionRepository recurringTransactionRepository) {
        this.recurringTransactionRepository = recurringTransactionRepository;
    }

    @Override
    public List<RecurringTransaction> getAllRecurringTransactions() {

        List<RecurringTransaction> recurringTransactions = new ArrayList<>();
        recurringTransactionRepository.findAll().forEach(recurringTransactions::add);
        return recurringTransactions;
    }

    @Override
    public List<RecurringTransaction> saveRecurringTransactions(List<RecurringTransaction> recurringTransactions) {

        List<RecurringTransaction> savedRecurringTransactions = new ArrayList<>();
        recurringTransactionRepository.saveAll(recurringTransactions).forEach(savedRecurringTransactions::add);

        return savedRecurringTransactions;
    }

    @Override
    public RecurringTransaction saveRecurringTransaction(RecurringTransaction recurringTransaction) {
        return recurringTransactionRepository.save(recurringTransaction);
    }

    @Override
    public void deleteRecurringTransaction(RecurringTransaction recurringTransaction) {
        recurringTransactionRepository.delete(recurringTransaction);
    }

    @Override
    public void deleteRecurringTransactions(List<RecurringTransaction> recurringTransactions) {
        recurringTransactionRepository.deleteAll(recurringTransactions);
    }
}
