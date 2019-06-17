package com.protean.moneymaker.rin.service;

import com.protean.moneymaker.rin.model.TransactionCategory;
import org.hamcrest.collection.IsEmptyCollection;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.greaterThan;
import static org.hamcrest.Matchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.*;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class TransactionCategoryServiceImplTest {

    @Autowired TransactionCategoryService transactionCategoryService;

    @Test
    public void findAllTransactionCategories_GivenDatabaseLoadedWithValidTestData_ThenReturnListOfTransactionCategories() {
        List<TransactionCategory> transactionCategories = transactionCategoryService.findAllTransactionCategories();
        assertThat(transactionCategories, is(allOf(notNullValue(), not(IsEmptyCollection.empty()))));
        assertThat(transactionCategories.size(), is(greaterThan(0)));
        transactionCategories.forEach(System.out::println);
    }

}