package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.model.Transaction;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.TransactionCategory;

import java.util.List;

public interface TransactionService {

    List<Transaction> saveAllTransactions(List<Transaction> transactions);

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    List<TransactionDto> getAllTransactionDtos();

    List<BudgetCategory> getAllTransactionCategories(); // TODO use transaction category controller

    List<TransactionCategory> getAllTransactionSubCategories();

    void deleteTransaction(Transaction id);

    void deleteTransactions(List<Transaction> ids);


}
