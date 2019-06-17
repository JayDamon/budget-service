package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.TransactionCategory;
import com.protean.moneymaker.rin.repository.TransactionCategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionCategoryServiceImpl implements TransactionCategoryService {

    private static final Logger log = LoggerFactory.getLogger(TransactionCategoryServiceImpl.class);

    private TransactionCategoryRepository transactionCategoryRepository;

    public TransactionCategoryServiceImpl(TransactionCategoryRepository transactionCategoryRepository) {
        this.transactionCategoryRepository = transactionCategoryRepository;
    }

    @Override
    public List<TransactionCategory> findAllTransactionCategories() {

        List<TransactionCategory> transactionCategories = new ArrayList<>();
        transactionCategoryRepository.findAll().forEach(transactionCategories::add);

        return transactionCategories;
    }
}
