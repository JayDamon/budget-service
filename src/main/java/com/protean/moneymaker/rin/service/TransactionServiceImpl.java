package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.dto.TransactionDto;
import com.protean.moneymaker.rin.model.BudgetCategory;
import com.protean.moneymaker.rin.model.Transaction;
import com.protean.moneymaker.rin.model.TransactionCategory;
import com.protean.moneymaker.rin.repository.TransactionCategoryRepository;
import com.protean.moneymaker.rin.repository.TransactionRepository;
import com.protean.moneymaker.rin.repository.TransactionSubCategoryRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private TransactionRepository transactionRepository;
    private TransactionCategoryRepository transactionCategoryRepository;
    private TransactionSubCategoryRepository transactionSubCategoryRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository,
                                  TransactionCategoryRepository transactionCategoryRepository,
                                  TransactionSubCategoryRepository transactionSubCategoryRepository) {
        this.transactionRepository = transactionRepository;
        this.transactionCategoryRepository = transactionCategoryRepository;
        this.transactionSubCategoryRepository = transactionSubCategoryRepository;
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
    public List<TransactionDto> getAllTransactionDtos() {

        List<Transaction> transactions = getAllTransactions();
        ModelMapper mapper = new ModelMapper();

        List<TransactionDto> dtos = new ArrayList<>();
        for (Transaction t : transactions) {
            dtos.add(mapper.map(t, TransactionDto.class));
        }
        return dtos;
    }

    @Override
    public List<BudgetCategory> getAllTransactionCategories() {

        List<BudgetCategory> categories = new ArrayList<>();
        transactionCategoryRepository.findAll().forEach(categories::add);

        return categories;
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
}
