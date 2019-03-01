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
import java.util.Objects;

/**
 * Financial transactions
 */
@Entity
@Table(name = "transaction")
public class Transaction extends UserAuditable implements Serializable {

    @Id
    @Column(name = "transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "budget_id")
    private Budget budget;

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

    public Transaction() {
    }

    public Transaction(Account account, Budget budget, BudgetCategory budgetCategory, TransactionCategory transactionCategory, TransactionType transactionType, RecurringTransaction recurringTransaction, Date date, String description, BigDecimal amount) {
        this.account = account;
        this.budget = budget;
        this.budgetCategory = budgetCategory;
        this.transactionCategory = transactionCategory;
        this.transactionType = transactionType;
        this.recurringTransaction = recurringTransaction;
        this.date = date;
        this.description = description;
        this.amount = amount;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public Budget getBudget() {
        return budget;
    }

    public void setBudget(Budget budget) {
        this.budget = budget;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public RecurringTransaction getRecurringTransaction() {
        return recurringTransaction;
    }

    public void setRecurringTransaction(RecurringTransaction recurringTransaction) {
        this.recurringTransaction = recurringTransaction;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(transactionId, that.transactionId) &&
                Objects.equals(account, that.account) &&
                Objects.equals(budget, that.budget) &&
                Objects.equals(budgetCategory, that.budgetCategory) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(recurringTransaction, that.recurringTransaction) &&
                Objects.equals(date, that.date) &&
                Objects.equals(description, that.description) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId, account, budget, budgetCategory, transactionCategory, transactionType, recurringTransaction, date, description, amount);
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "transactionId=" + transactionId +
                ", account=" + account +
                ", budget=" + budget +
                ", budgetCategory=" + budgetCategory +
                ", transactionCategory=" + transactionCategory +
                ", transactionType=" + transactionType +
                ", recurringTransaction=" + recurringTransaction +
                ", date=" + date +
                ", description='" + description + '\'' +
                ", amount=" + amount +
                '}';
    }
}
