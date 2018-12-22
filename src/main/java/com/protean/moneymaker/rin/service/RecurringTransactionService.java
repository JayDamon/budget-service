package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.RecurringTransaction;

import java.util.List;

public interface RecurringTransactionService {

    // TODO all need basic crud functions, create, read, update, delete

    List<RecurringTransaction> getRecurringTransactions();

    List<RecurringTransaction> saveRecurringTransactions();

}
