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
    @JsonProperty("budgetName")
    private String budgetItemName;
    @JsonProperty("budgetId")
    private Long budgetId;
    @JsonProperty("budgetCategoryId")
    private Integer budgetCategoryId;
    @JsonProperty("budgetCategoryType")
    private String budgetCategoryType;
    @JsonProperty("budgetCategoryTypeId")
    private Integer budgetCategoryTypeId;
    @JsonProperty("budgetCategoryName")
    private String budgetCategoryName;
    @JsonProperty("budgetCategoryNameId")
    private Integer budgetCategoryNameId;
    @JsonProperty("transactionCategory")
    private String transactionCategory;
    @JsonProperty("transactionCategoryId")
    private Long transactionCategoryId;
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

    public TransactionDto(String accountName, Long accountId, String budgetItemName, Long budgetId, Integer budgetCategoryId, String budgetCategoryType, Integer budgetCategoryTypeId, String budgetCategoryName, Integer budgetCategoryNameId, String transactionCategory, Long transactionCategoryId, String transactionTypeName, Integer transactionTypeId, String recurringTransactionName, Long recurringTransactionId, BigDecimal amount, String description, Date date, String formattedDate) {
        this.accountName = accountName;
        this.accountId = accountId;
        this.budgetItemName = budgetItemName;
        this.budgetId = budgetId;
        this.budgetCategoryId = budgetCategoryId;
        this.budgetCategoryType = budgetCategoryType;
        this.budgetCategoryTypeId = budgetCategoryTypeId;
        this.budgetCategoryName = budgetCategoryName;
        this.budgetCategoryNameId = budgetCategoryNameId;
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

    public String getBudgetItemName() {
        return budgetItemName;
    }

    public void setBudgetItemName(String budgetItemName) {
        this.budgetItemName = budgetItemName;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public Integer getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public String getBudgetCategoryType() {
        return budgetCategoryType;
    }

    public void setBudgetCategoryType(String budgetCategoryType) {
        this.budgetCategoryType = budgetCategoryType;
    }

    public Integer getBudgetCategoryTypeId() {
        return budgetCategoryTypeId;
    }

    public void setBudgetCategoryTypeId(Integer budgetCategoryTypeId) {
        this.budgetCategoryTypeId = budgetCategoryTypeId;
    }

    public String getBudgetCategoryName() {
        return budgetCategoryName;
    }

    public void setBudgetCategoryName(String budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }

    public Integer getBudgetCategoryNameId() {
        return budgetCategoryNameId;
    }

    public void setBudgetCategoryNameId(Integer budgetCategoryNameId) {
        this.budgetCategoryNameId = budgetCategoryNameId;
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
                Objects.equals(budgetItemName, that.budgetItemName) &&
                Objects.equals(budgetId, that.budgetId) &&
                Objects.equals(budgetCategoryId, that.budgetCategoryId) &&
                Objects.equals(budgetCategoryType, that.budgetCategoryType) &&
                Objects.equals(budgetCategoryTypeId, that.budgetCategoryTypeId) &&
                Objects.equals(budgetCategoryName, that.budgetCategoryName) &&
                Objects.equals(budgetCategoryNameId, that.budgetCategoryNameId) &&
                Objects.equals(transactionCategory, that.transactionCategory) &&
                Objects.equals(transactionCategoryId, that.transactionCategoryId) &&
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
        return Objects.hash(accountName, accountId, budgetItemName, budgetId, budgetCategoryId, budgetCategoryType, budgetCategoryTypeId, budgetCategoryName, budgetCategoryNameId, transactionCategory, transactionCategoryId, transactionTypeName, transactionTypeId, recurringTransactionName, recurringTransactionId, amount, description, date, formattedDate);
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "accountName='" + accountName + '\'' +
                ", accountId=" + accountId +
                ", budgetItemName='" + budgetItemName + '\'' +
                ", budgetId=" + budgetId +
                ", budgetCategoryId=" + budgetCategoryId +
                ", budgetCategoryType='" + budgetCategoryType + '\'' +
                ", budgetCategoryTypeId=" + budgetCategoryTypeId +
                ", budgetCategoryName='" + budgetCategoryName + '\'' +
                ", budgetCategoryNameId=" + budgetCategoryNameId +
                ", transactionCategory='" + transactionCategory + '\'' +
                ", transactionCategoryId=" + transactionCategoryId +
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
