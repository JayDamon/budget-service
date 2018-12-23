package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

/**
 * i.e. Primary transaction category
 */
@Entity
@Table(name = "budget_category")
public class BudgetCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Long budgetCategoryId;

    @Column(name = "category_name")
    private String category;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(mappedBy = "budgetCategory", cascade = CascadeType.ALL)
    private Set<TransactionCategory> transactionCategories;

}
