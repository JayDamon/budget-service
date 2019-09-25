package com.protean.moneymaker.rin.unit.service;

import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.model.Account;
import com.protean.moneymaker.rin.model.AccountClassification;
import com.protean.moneymaker.rin.model.AccountType;
import com.protean.moneymaker.rin.model.Budget;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetCategoryName;
import com.protean.moneymaker.rin.model.BudgetCategoryType;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import com.protean.moneymaker.rin.model.FrequencyType;
import com.protean.moneymaker.rin.model.Occurrence;
import com.protean.moneymaker.rin.model.RecurringTransaction;
import com.protean.moneymaker.rin.model.Transaction;
import com.protean.moneymaker.rin.model.TransactionCategory;
import com.protean.moneymaker.rin.model.TransactionType;
import com.protean.moneymaker.rin.repository.TransactionCategoryRepository;
import com.protean.moneymaker.rin.repository.TransactionRepository;
import com.protean.moneymaker.rin.repository.TransactionSubCategoryRepository;
import com.protean.moneymaker.rin.service.TransactionService;
import com.protean.moneymaker.rin.service.TransactionServiceImpl;
import org.joda.time.DateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplUnitTest {

    @Mock private TransactionRepository transactionRepository;
    @Mock private TransactionCategoryRepository transactionCategoryRepository;
    @Mock private TransactionSubCategoryRepository transactionSubCategoryRepository;

    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        transactionService = new TransactionServiceImpl(
                transactionRepository, transactionCategoryRepository, transactionSubCategoryRepository);
    }

    @Test
    void getAllTransactionDtos_GivenCompleteTransactionsFound_ThenMapCompleteDto() {

        // Arrange
        AccountType accountType = new AccountType("Account Type 1", "AccountTypeOne");
        accountType.setId(1);
        AccountClassification accountClassification = new AccountClassification("AccountClassificationOne");
        accountClassification.setId(2);
        Account account = new Account("Account 1", accountType, BigDecimal.valueOf(52.52),
                BigDecimal.valueOf(1000.22), accountClassification, true, true);
        account.setId(3L);

        BudgetCategoryType budgetCategoryType = new BudgetCategoryType("BudgetCategoryType");
        budgetCategoryType.setId(4);
        BudgetCategoryName budgetCategoryName = new BudgetCategoryName("BudgetCategoryName");
        budgetCategoryName.setId(5);
        BudgetCategory budgetCategory = new BudgetCategory(budgetCategoryType, budgetCategoryName);
        budgetCategory.setId(6);
        FrequencyType frequencyType = new FrequencyType("FrequencyTypeOne");
        frequencyType.setId(7);
        Budget budget = new Budget(budgetCategory, "BudgetItemNameOne", DateTime.now(),
                DateTime.now().plus(4), frequencyType, BigDecimal.valueOf(55.09), true);
        budget.setId(8L);

        TransactionType transactionType = new TransactionType("TransactionTypeOne");
        transactionType.setId(9);
        BudgetSubCategory budgetSubCategory = new BudgetSubCategory("BudgetSubCategoryOne", transactionType, new HashSet<>());
        budgetSubCategory.setId(10L);
        TransactionCategory transactionCategory = new TransactionCategory("TransactionCategoryOne", budgetSubCategory);
        transactionCategory.setId(11L);

        Occurrence occurrence = new Occurrence("OccurrenceOne");
        occurrence.setId(12);

        RecurringTransaction recurringTransaction = new RecurringTransaction(
                "RecurringTransactionName", account, budgetSubCategory, transactionCategory,
                frequencyType, 2, occurrence, transactionType, DateTime.now(),
                DateTime.now().plus(25), BigDecimal.valueOf(34.66));
        recurringTransaction.setId(13L);

        Transaction transaction = new Transaction(account, budget, transactionCategory,
                transactionType, recurringTransaction, DateTime.now(),
                "TransactionDescriptionOne", BigDecimal.valueOf(44.78));
        transaction.setId(14L);

        when(transactionRepository.findAllByOrderByDateDesc()).thenReturn(new HashSet<>(Collections.singletonList(transaction)));

        // Act
        Set<TransactionDto> transactionDtos = transactionService.getAllTransactionDtos();

        // Assert
        assertThat(transactionDtos, hasSize(1));
        int transactionsChecked = 0;
        for (TransactionDto dto : transactionDtos) {
            assertThat(dto.getId(), is(equalTo(14L)));
            assertThat(dto.getAmount(), is(equalTo(BigDecimal.valueOf(44.78))));
            assertThat(dto.getDescription(), is(equalTo("TransactionDescriptionOne")));
            assertThat(dto.getDate().monthOfYear(), is(equalTo(DateTime.now().monthOfYear())));
            assertThat(dto.getAccount().getId(), is(equalTo(3L)));
            assertThat(dto.getAccount().getName(), is(equalTo("Account 1")));
            assertThat(dto.getBudget().getId(), is(equalTo(8L)));
            assertThat(dto.getBudget().getName(), is(equalTo("BudgetItemNameOne")));
            assertThat(dto.getBudget().getBudgetCategory().getId(), is(equalTo(6L)));
            assertThat(dto.getBudget().getBudgetCategory().getTypeName(), is(equalTo("BudgetCategoryType")));
            assertThat(dto.getBudget().getBudgetCategory().getName(), is(equalTo("BudgetCategoryName")));
            assertThat(dto.getBudget().getStartDate().monthOfYear(), is(equalTo(DateTime.now().monthOfYear())));
            assertThat(dto.getBudget().getEndDate().monthOfYear(), is(equalTo(DateTime.now().monthOfYear())));
            assertThat(dto.getBudget().getFrequencyTypeName(), is(equalTo("FrequencyTypeOne")));
            assertThat(dto.getBudget().getAmount(), is(equalTo(BigDecimal.valueOf(55.09))));
            assertThat(dto.getBudget().getInUse(), is(equalTo(true)));
            assertThat(dto.getTransactionCategory().getId(), is(equalTo(11L)));
            assertThat(dto.getTransactionCategory().getName(), is(equalTo("TransactionCategoryOne")));
            assertThat(dto.getTransactionCategory().getBudgetSubCategory().getId(), is(equalTo(10L)));
            assertThat(dto.getTransactionCategory().getBudgetSubCategory().getName(), is(equalTo("BudgetSubCategoryOne")));
            assertThat(dto.getRecurringTransaction().getId(), is(equalTo(13L)));
            assertThat(dto.getRecurringTransaction().getName(), is(equalTo("RecurringTransactionName")));
            assertThat(dto.getRecurringTransaction().getAccount().getId(), is(equalTo(3L)));
            assertThat(dto.getRecurringTransaction().getAccount().getName(), is(equalTo("Account 1")));
            assertThat(dto.getRecurringTransaction().getBudgetSubCategory().getId(), is(equalTo(10L)));
            assertThat(dto.getRecurringTransaction().getBudgetSubCategory().getName(), is(equalTo("BudgetSubCategoryOne")));
            assertThat(dto.getRecurringTransaction().getTransactionCategory().getId(), is(equalTo(11L)));
            assertThat(dto.getRecurringTransaction().getTransactionCategory().getName(), is(equalTo("TransactionCategoryOne")));
            assertThat(dto.getRecurringTransaction().getTransactionCategory().getBudgetSubCategory().getId(), is(equalTo(10L)));
            assertThat(dto.getRecurringTransaction().getTransactionCategory().getBudgetSubCategory().getName(), is(equalTo("BudgetSubCategoryOne")));
            assertThat(dto.getRecurringTransaction().getFrequencyTypeName(), is(equalTo("FrequencyTypeOne")));
            assertThat(dto.getRecurringTransaction().getFrequency(), is(equalTo(2)));
            assertThat(dto.getRecurringTransaction().getOccurrenceName(), is(equalTo("OccurrenceOne")));
            assertThat(dto.getRecurringTransaction().getTransactionTypeName(), is(equalTo("TransactionTypeOne")));
            assertThat(dto.getRecurringTransaction().getStartDate().monthOfYear(), is(equalTo(DateTime.now().monthOfYear())));
            assertThat(dto.getRecurringTransaction().getEndDate().monthOfYear(), is(equalTo(DateTime.now().monthOfYear())));
            assertThat(dto.getRecurringTransaction().getAmount(), is(equalTo(BigDecimal.valueOf(34.66))));
            transactionsChecked++;
        }

        assertThat(transactionsChecked, is(equalTo(1)));
    }

}
