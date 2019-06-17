package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.TransactionCategory;

import java.util.List;

public interface TransactionCategoryService {

    List<TransactionCategory> findAllTransactionCategories();

}
