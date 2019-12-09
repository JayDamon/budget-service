package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.BudgetSummary;
import com.protean.moneymaker.rin.dto.TransactionBudgetSummary;
import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.BudgetSubCategory;
import com.protean.moneymaker.rin.model.Transaction;
import com.protean.moneymaker.rin.model.TransactionCategory;
import com.protean.moneymaker.rin.model.TransactionType;
import com.protean.moneymaker.rin.repository.TransactionRepository;
import com.protean.moneymaker.rin.repository.TransactionSubCategoryRepository;
import com.protean.moneymaker.rin.repository.TransactionTypeRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionSubCategoryRepository transactionSubCategoryRepository;
    private TransactionTypeRepository transactionTypeRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionSubCategoryRepository transactionSubCategoryRepository,
                                  TransactionTypeRepository transactionTypeRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionSubCategoryRepository = transactionSubCategoryRepository;
        this.transactionTypeRepository = transactionTypeRepository;
    }

    @Override
    public List<Transaction> saveAllTransactions(List<Transaction> transactions) {

        List<Transaction> savedTransactions = new ArrayList<>();
        transactionRepository.saveAll(transactions).forEach(savedTransactions::add);

        return savedTransactions;
    }

    @Override
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @Override
    public List<Transaction> getAllTransactions() {

        List<Transaction> transactions = new ArrayList<>();
        transactionRepository.findAll().forEach(transactions::add);

        return transactions;
    }

    @Override
    public Set<Transaction> getAllTransactionsOrdered() {
        return transactionRepository.findAllByOrderByDateDesc();
    }

    @Override
    @Transactional
    public Set<TransactionDto> getAllTransactionDtos() {

        Set<Transaction> transactions = getAllTransactionsOrdered();
        ModelMapper modelMapper = new ModelMapper();

//        PropertyMap<Transaction, TransactionDto> propertyMap = new PropertyMap<Transaction, TransactionDto>() {
//            @Override
//            protected void configure() {
//                map().getTransactionCategory().setId(source.getRecurringTransaction().getName());
//            }
//        };
//
//        modelMapper.addMappings(propertyMap);

        Set<TransactionDto> dtos = new LinkedHashSet<>();
        for (Transaction t : transactions) {
            TransactionDto dto = modelMapper.map(t, TransactionDto.class);
            dtos.add(dto);
        }
        return dtos;
    }

    @Override
    public List<BudgetSubCategory> getAllTransactionCategories() {

//        List<BudgetSubCategory> categories = new ArrayList<>();
//        transactionCategoryRepository.findAll().forEach(categories::add);

        return null;
    }

    @Override
    public List<TransactionCategory> getAllTransactionSubCategories() {

        List<TransactionCategory> subCategories = new ArrayList<>();
        transactionSubCategoryRepository.findAll().forEach(subCategories::add);

        return subCategories;
    }

    @Override
    public void deleteTransaction(Transaction transaction) {
        transactionRepository.delete(transaction);
    }

    @Override
    public void deleteTransactions(List<Transaction> transactions) {
        transactionRepository.deleteAll(transactions);
    }

    @Override
    public Set<TransactionBudgetSummary> getTransactionBudgetSummaryForAllTransactionTypes(int year, int month, Set<BudgetSummary> budgetSummaries) {

        if (year <= 0) {
            throw  new IllegalArgumentException("Year must be greater than zero, but was <" + year + ">");
        }
        if (month <= 0 || month > 12) {
            throw  new IllegalArgumentException("Valid month between 1 and 12 must be provided, but was <" + month + ">");
        }

        Set<TransactionBudgetSummary> summaries = new HashSet<>();

        for (BudgetSummary b : budgetSummaries) {
            TransactionBudgetSummary summary = transactionRepository.getBudgetSummaries(year, month, b.getCategoryId(), b.getTransactionTypeId()).orElse(
                    new TransactionBudgetSummary(b.getTransactionType(), b.getCategory(), month, year, b.getPlanned(), BigDecimal.ZERO, false)
            );

            if (summary.getPlanned().doubleValue() > 0 || summary.getActual().doubleValue() > 0) {
                summaries.add(summary);
            }

        }

        return summaries;
    }
}
