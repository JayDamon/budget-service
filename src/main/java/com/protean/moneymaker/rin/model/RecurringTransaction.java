package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
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
 * Recurring transactions that are applied on a defined time period that are not bills
 * These may be better defined as allocations
 */
@Entity
@Table(name = "recurring_transaction")
public class RecurringTransaction extends UserAuditable implements Serializable {

    @Id
    @Column(name = "recurring_transaction_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long recurringTransactionId;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @OneToOne
    @JoinColumn(name = "budget_category_id")
    private BudgetSubCategory budgetSubCategory;

    @OneToOne
    @JoinColumn(name = "transaction_category_id")
    private TransactionCategory transactionCategory;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    // This is the number of days, months or years between occurrences based on the FrequencyType
    @Column(name = "frequency")
    private Integer frequency;

    @OneToOne
    @JoinColumn(name = "occurrence_id")
    private Occurrence occurrence;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @Column(name = "amount")
    private BigDecimal amount;

    public RecurringTransaction() {
    }

    public RecurringTransaction(String name, Account account, BudgetSubCategory budgetSubCategory, TransactionCategory transactionCategory, FrequencyType frequencyType, Integer frequency, Occurrence occurrence, TransactionType transactionType, Date startDate, Date endDate, BigDecimal amount) {
        this.name = name;
        this.account = account;
        this.budgetSubCategory = budgetSubCategory;
        this.transactionCategory = transactionCategory;
        this.frequencyType = frequencyType;
        this.frequency = frequency;
        this.occurrence = occurrence;
        this.transactionType = transactionType;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Long getRecurringTransactionId() {
        return recurringTransactionId;
    }

    public void setRecurringTransactionId(Long recurringTransactionId) {
        this.recurringTransactionId = recurringTransactionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public BudgetSubCategory getBudgetSubCategory() {
        return budgetSubCategory;
    }

    public void setBudgetSubCategory(BudgetSubCategory budgetSubCategory) {
        this.budgetSubCategory = budgetSubCategory;
    }

    public TransactionCategory getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategory transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public Occurrence getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(Occurrence occurrence) {
        this.occurrence = occurrence;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
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
        RecurringTransaction that = (RecurringTransaction) o;
        return Objects.equals(recurringTransactionId, that.recurringTransactionId) &&
                Objects.equals(name, that.name) &&
                Objects.equals(account, that.account) &&
                Objects.equals(budgetSubCategory, that.budgetSubCategory) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(frequencyType, that.frequencyType) &&
                Objects.equals(frequency, that.frequency) &&
                Objects.equals(occurrence, that.occurrence) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(startDate, that.startDate) &&
                Objects.equals(endDate, that.endDate) &&
                Objects.equals(amount, that.amount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(recurringTransactionId, name, account, budgetSubCategory, transactionCategory, frequencyType, frequency, occurrence, transactionType, startDate, endDate, amount);
    }

    @Override
    public String toString() {
        return "RecurringTransaction{" +
                "recurringTransactionId=" + recurringTransactionId +
                ", name='" + name + '\'' +
                ", account=" + account +
                ", budgetSubCategory=" + budgetSubCategory +
                ", transactionCategory=" + transactionCategory +
                ", frequencyType=" + frequencyType +
                ", frequency=" + frequency +
                ", occurrence=" + occurrence +
                ", transactionType=" + transactionType +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", amount=" + amount +
                '}';
    }
}
