package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.math.BigDecimal;
import java.util.Date;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    private String accountName;
    private Long accountId;
    private String budgetCategory;
    private Long budgetCategoryId;
    private String transactionCategory;
    private Long transactionCategoryId; // TODO ?
    private String transactionTypeName;
    private Integer transactionTypeId;
    private String recurringTransactionName;
    private String recurringTransactionId;
    private BigDecimal amount;
    private String description;
    private Date date;
    private String formattedDate;

    public TransactionDto() {
    }

    public TransactionDto(String accountName, Long accountId, String budgetCategory, Long budgetCategoryId, String transactionCategory, Long transactionCategoryId, String transactionTypeName, Integer transactionTypeId, String recurringTransactionName, String recurringTransactionId, BigDecimal amount, String description, Date date, String formattedDate) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.budgetCategory = budgetCategory;
        this.budgetCategoryId = budgetCategoryId;
        this.transactionCategory = transactionCategory;
        this.transactionCategoryId = transactionCategoryId;
        this.transactionTypeName = transactionTypeName;
        this.transactionTypeId = transactionTypeId;
        this.recurringTransactionName = recurringTransactionName;
        this.recurringTransactionId = recurringTransactionId;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.formattedDate = formattedDate;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(String budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public Long getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Long budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public String getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(String transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public Long getTransactionCategoryId() {
        return transactionCategoryId;
    }

    public void setTransactionCategoryId(Long transactionCategoryId) {
        this.transactionCategoryId = transactionCategoryId;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public Integer getTransactionTypeId() {
        return transactionTypeId;
    }

    public void setTransactionTypeId(Integer transactionTypeId) {
        this.transactionTypeId = transactionTypeId;
    }

    public String getRecurringTransactionName() {
        return recurringTransactionName;
    }

    public void setRecurringTransactionName(String recurringTransactionName) {
        this.recurringTransactionName = recurringTransactionName;
    }

    public String getRecurringTransactionId() {
        return recurringTransactionId;
    }

    public void setRecurringTransactionId(String recurringTransactionId) {
        this.recurringTransactionId = recurringTransactionId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getFormattedDate() {
        return formattedDate;
    }

    public void setFormattedDate(String formattedDate) {
        this.formattedDate = formattedDate;
    }
}
