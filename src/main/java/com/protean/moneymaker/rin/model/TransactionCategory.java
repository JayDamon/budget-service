package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * Sub categories of transactions, tied to transaction category
 */
@Entity
@Table(name = "transaction_category")
public class TransactionCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_category_id")
    private Long subCategoryId;

    @Column(name = "category_name")
    private String transactionCategory;

    @ManyToOne
    @JoinColumn(name = "budget_category_id", nullable = false)
    private BudgetCategory budgetCategory;

}
