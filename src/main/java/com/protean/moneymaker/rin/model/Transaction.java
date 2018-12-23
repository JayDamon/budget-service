package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Financial transactions
 */
@Entity
@Table(name = "transaction")
public class Transaction extends UserAuditable implements Serializable {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

//    @OneToOne
//    @JoinColumn(name = "budget_id")
//    private Budget budget;

    @OneToOne
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @OneToOne
    @JoinColumn(name = "transaction_category_id")
    private TransactionCategory transactionCategory;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToOne
    @JoinColumn(name = "recurring_transaction_id")
    private RecurringTransaction recurringTransaction;

    @Column(name = "transaction_date")
    private Date date;

    @Column(name = "description")
    private String description;

    @Column(name = "amount")
    private BigDecimal amount;

}
