package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import com.protean.moneymaker.rin.model.Transaction;
import com.protean.moneymaker.rin.model.TransactionCategory;

import java.util.List;
import java.util.Set;

public interface TransactionService {

    Set<Transaction> saveAllTransactions(Set<TransactionDto> transactions);

    Transaction saveTransaction(Transaction transaction);

    List<Transaction> getAllTransactions();

    Set<Transaction> getAllTransactionsOrdered();

    Set<TransactionDto> getAllTransactionDtos();

    List<BudgetSubCategory> getAllTransactionCategories(); // TODO use transaction category controller

    List<TransactionCategory> getAllTransactionSubCategories();

    void deleteTransaction(Transaction id);

    void deleteTransactions(List<Transaction> ids);

    Set<TransactionBudgetSummary> getTransactionBudgetSummaryForAllTransactionTypes(int year, int month, List<BudgetSummary> budgetCategoryTypeId);

}
