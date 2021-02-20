package com.factotum.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.ZonedDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransactionDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("description")
    private String description;

    @JsonProperty("date")
    private ZonedDateTime date;

    @JsonProperty("account")
    @NotNull
    private ShortAccountDto account;

    @JsonProperty("budget")
    private BudgetDto budget;

    @JsonProperty("category")
    private TransactionCategoryDto transactionCategory;

    @JsonProperty("recurringTransaction")
    private RecurringTransactionDto recurringTransaction;

    public TransactionDto() {
    }

    public TransactionDto(
            Long id, BigDecimal amount, String description, ZonedDateTime date, ShortAccountDto account,
            BudgetDto budget, TransactionCategoryDto transactionCategory,
            RecurringTransactionDto recurringTransaction) {

        this.id = id;
        this.amount = amount;
        this.description = description;
        this.date = date;
        this.account = account;
        this.budget = budget;
        this.transactionCategory = transactionCategory;
        this.recurringTransaction = recurringTransaction;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public ZonedDateTime getDate() {
        return date;
    }

    public void setDate(ZonedDateTime date) {
        this.date = date;
    }

    public ShortAccountDto getAccount() {
        return account;
    }

    public void setAccount(ShortAccountDto account) {
        this.account = account;
    }

    public BudgetDto getBudget() {
        return budget;
    }

    public void setBudget(BudgetDto budget) {
        this.budget = budget;
    }

    public TransactionCategoryDto getTransactionCategory() {
        return transactionCategory;
    }

    public void setTransactionCategory(TransactionCategoryDto transactionCategory) {
        this.transactionCategory = transactionCategory;
    }

    public RecurringTransactionDto getRecurringTransaction() {
        return recurringTransaction;
    }

    public void setRecurringTransaction(RecurringTransactionDto recurringTransaction) {
        this.recurringTransaction = recurringTransaction;
    }
}
