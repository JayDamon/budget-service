package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class RecurringTransactionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String recurringTransactionName;

    @JsonProperty("account")
    private ShortAccountDto account;

    @JsonProperty("subCategory")
    private BudgetSubCategoryDto budgetSubCategory;

    @JsonProperty("transactionCategory")
    private TransactionCategoryDto transactionCategory;

    @JsonProperty("frequencyType")
    private String frequencyTypeName;

    @JsonProperty("frequency")
    private Integer frequency;

    @JsonProperty("occurrence")
    private String occurrenceName;

    @JsonProperty("transactionType")
    private String transactionTypeName;

    @JsonProperty("startDate")
    private ZonedDateTime startDate;

    @JsonProperty("endDate")
    private ZonedDateTime endDate;

    @JsonProperty("amount")
    private BigDecimal amount;

    public RecurringTransactionDto() {
    }

    public RecurringTransactionDto(
            Long id, String recurringTransactionName, ShortAccountDto account,
            BudgetSubCategoryDto budgetSubCategory, TransactionCategoryDto transactionCategory,
            String frequencyTypeName, Integer frequency, String occurrenceName, String transactionTypeName,
            ZonedDateTime startDate, ZonedDateTime endDate, BigDecimal amount) {

        this.id = id;
        this.recurringTransactionName = recurringTransactionName;
        this.account = account;
        this.budgetSubCategory = budgetSubCategory;
        this.transactionCategory = transactionCategory;
        this.frequencyTypeName = frequencyTypeName;
        this.frequency = frequency;
        this.occurrenceName = occurrenceName;
        this.transactionTypeName = transactionTypeName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecurringTransactionName() {
        return recurringTransactionName;
    }

    public void setRecurringTransactionName(String recurringTransactionName) {
        this.recurringTransactionName = recurringTransactionName;
    }

    public ShortAccountDto getAccount() {
        return account;
    }

    public void setAccount(ShortAccountDto account) {
        this.account = account;
    }

    public BudgetSubCategoryDto getBudgetSubCategory() {
        return budgetSubCategory;
    }

    public void setBudgetSubCategory(BudgetSubCategoryDto budgetSubCategory) {
        this.budgetSubCategory = budgetSubCategory;
    }

    public TransactionCategoryDto getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategoryDto transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public String getFrequencyTypeName() {
        return frequencyTypeName;
    }

    public void setFrequencyTypeName(String frequencyTypeName) {
        this.frequencyTypeName = frequencyTypeName;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }

    public String getOccurrenceName() {
        return occurrenceName;
    }

    public void setOccurrenceName(String occurrenceName) {
        this.occurrenceName = occurrenceName;
    }

    public String getTransactionTypeName() {
        return transactionTypeName;
    }

    public void setTransactionTypeName(String transactionTypeName) {
        this.transactionTypeName = transactionTypeName;
    }

    public ZonedDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
}
