package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Objects;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    @JsonProperty("accountName")
    private String accountName;
    @JsonProperty("accountId")
    private Long accountId;
    @JsonProperty("budgetSubCategory")
    private String budgetSubCategory;
    @JsonProperty("budgetSubCategoryId")
    private Long budgetSubCategoryId;
    @JsonProperty("transactionCategory")
    private String transactionCategory;
    @JsonProperty("transactionCategoryId")
    private Long transactionCategoryId;
    @JsonProperty("budgetCategory")
    private String transactionCategoryBudgetCategory;
    @JsonProperty("budgetCategoryId")
    private String transactionCategoryBudgetCategoryId;
    @JsonProperty("transactionTypeName")
    private String transactionTypeName;
    @JsonProperty("transactionTypeId")
    private Integer transactionTypeId;
    @JsonProperty("recurringTransactionName")
    private String recurringTransactionName;
    @JsonProperty("recurringTransactionId")
    private Long recurringTransactionId;
    @JsonProperty("amount")
    private BigDecimal amount;
    @JsonProperty("description")
    private String description;
    @JsonProperty("date")
    private Date date;
    @JsonProperty("formattedDate")
    private String formattedDate;

    public TransactionDto() {
    }

    public TransactionDto(String accountName, Long accountId, String budgetSubCategory, Long budgetSubCategoryId, String transactionCategory, Long transactionCategoryId, String transactionCategoryBudgetCategory, String transactionCategoryBudgetCategoryId, String transactionTypeName, Integer transactionTypeId, String recurringTransactionName, Long recurringTransactionId, BigDecimal amount, String description, Date date, String formattedDate) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.budgetSubCategory = budgetSubCategory;
        this.budgetSubCategoryId = budgetSubCategoryId;
        this.transactionCategory = transactionCategory;
        this.transactionCategoryId = transactionCategoryId;
        this.transactionCategoryBudgetCategory = transactionCategoryBudgetCategory;
        this.transactionCategoryBudgetCategoryId = transactionCategoryBudgetCategoryId;
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

    public String getBudgetSubCategory() {
        return budgetSubCategory;
    }

    public void setBudgetSubCategory(String budgetSubCategory) {
        this.budgetSubCategory = budgetSubCategory;
    }

    public Long getBudgetSubCategoryId() {
        return budgetSubCategoryId;
    }

    public void setBudgetSubCategoryId(Long budgetSubCategoryId) {
        this.budgetSubCategoryId = budgetSubCategoryId;
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

    public String getTransactionCategoryBudgetCategory() {
        return transactionCategoryBudgetCategory;
    }

    public void setTransactionCategoryBudgetCategory(String transactionCategoryBudgetCategory) {
        this.transactionCategoryBudgetCategory = transactionCategoryBudgetCategory;
    }

    public String getTransactionCategoryBudgetCategoryId() {
        return transactionCategoryBudgetCategoryId;
    }

    public void setTransactionCategoryBudgetCategoryId(String transactionCategoryBudgetCategoryId) {
        this.transactionCategoryBudgetCategoryId = transactionCategoryBudgetCategoryId;
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

    public Long getRecurringTransactionId() {
        return recurringTransactionId;
    }

    public void setRecurringTransactionId(Long recurringTransactionId) {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TransactionDto that = (TransactionDto) o;
        return Objects.equals(accountName, that.accountName) &&
                Objects.equals(accountId, that.accountId) &&
                Objects.equals(budgetSubCategory, that.budgetSubCategory) &&
                Objects.equals(budgetSubCategoryId, that.budgetSubCategoryId) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(transactionCategoryId, that.transactionCategoryId) &&
                Objects.equals(transactionCategoryBudgetCategory, that.transactionCategoryBudgetCategory) &&
                Objects.equals(transactionCategoryBudgetCategoryId, that.transactionCategoryBudgetCategoryId) &&
                Objects.equals(transactionTypeName, that.transactionTypeName) &&
                Objects.equals(transactionTypeId, that.transactionTypeId) &&
                Objects.equals(recurringTransactionName, that.recurringTransactionName) &&
                Objects.equals(recurringTransactionId, that.recurringTransactionId) &&
                Objects.equals(amount, that.amount) &&
                Objects.equals(description, that.description) &&
                Objects.equals(date, that.date) &&
                Objects.equals(formattedDate, that.formattedDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountName, accountId, budgetSubCategory, budgetSubCategoryId, transactionCategory, transactionCategoryId, transactionCategoryBudgetCategory, transactionCategoryBudgetCategoryId, transactionTypeName, transactionTypeId, recurringTransactionName, recurringTransactionId, amount, description, date, formattedDate);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "accountName='" + accountName + '\'' +
                ", accountId=" + accountId +
                ", budgetSubCategory='" + budgetSubCategory + '\'' +
                ", budgetSubCategoryId=" + budgetSubCategoryId +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", transactionCategoryId=" + transactionCategoryId +
                ", transactionCategoryBudgetCategory='" + transactionCategoryBudgetCategory + '\'' +
                ", transactionCategoryBudgetCategoryId='" + transactionCategoryBudgetCategoryId + '\'' +
                ", transactionTypeName='" + transactionTypeName + '\'' +
                ", transactionTypeId=" + transactionTypeId +
                ", recurringTransactionName='" + recurringTransactionName + '\'' +
                ", recurringTransactionId=" + recurringTransactionId +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", formattedDate='" + formattedDate + '\'' +
                '}';
    }
}
