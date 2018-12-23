package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.RecurringTransaction;

import java.util.List;

public interface RecurringTransactionService {

    List<RecurringTransaction> getAllRecurringTransactions();

    List<RecurringTransaction> saveRecurringTransactions(List<RecurringTransaction> recurringTransactions);

    RecurringTransaction saveRecurringTransaction(RecurringTransaction recurringTransaction);

    void deleteRecurringTransaction(RecurringTransaction id);

    void deleteRecurringTransactions(List<RecurringTransaction> ids);

}
