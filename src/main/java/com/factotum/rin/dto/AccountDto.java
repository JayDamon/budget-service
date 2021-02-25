package com.factotum.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class AccountDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("type")
    private AccountTypeDto type;

    @JsonProperty("startingBalance")
    private BigDecimal startingBalance;

    @JsonProperty("currentBalance")
    private BigDecimal currentbalance;

    @JsonProperty("isPrimary")
    private Boolean isPrimary;

    @JsonProperty("isInCashFlow")
    private Boolean isInCashFlow;

    public AccountDto() {
    }

    public AccountDto(
            Long id,
            String name,
            AccountTypeDto type,
            BigDecimal startingBalance,
            BigDecimal currentbalance,
            Boolean isPrimary,
            Boolean isInCashFlow) {

        this.id = id;
        this.name = name;
        this.type = type;
        this.startingBalance = startingBalance;
        this.currentbalance = currentbalance;
        this.isPrimary = isPrimary;
        this.isInCashFlow = isInCashFlow;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AccountTypeDto getType() {
        return type;
    }

    public void setType(AccountTypeDto type) {
        this.type = type;
    }

    public BigDecimal getStartingBalance() {
        return startingBalance;
    }

    public void setStartingBalance(BigDecimal startingBalance) {
        this.startingBalance = startingBalance;
    }

    public BigDecimal getCurrentbalance() {
        return currentbalance;
    }

    public void setCurrentbalance(BigDecimal currentbalance) {
        this.currentbalance = currentbalance;
    }

    public Boolean getPrimary() {
        return isPrimary;
    }

    public void setPrimary(Boolean primary) {
        isPrimary = primary;
    }

    public Boolean getInCashFlow() {
        return isInCashFlow;
    }

    public void setInCashFlow(Boolean inCashFlow) {
        isInCashFlow = inCashFlow;
    }
}
