package com.protean.moneymaker.rin.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.joda.time.DateTime;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class BudgetDto {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("budgetCategory")
    private BudgetCategoryDto budgetCategory;

    @JsonProperty("startDate")
    private DateTime startDate;

    @JsonProperty("endDate")
    private DateTime endDate;

    @JsonProperty("frequencyType")
    private String frequencyTypeName;

    @JsonProperty("amount")
    private BigDecimal amount;

    @JsonProperty("inUse")
    private Boolean inUse;

    public BudgetDto() {
    }

    public BudgetDto(
            Long id, String name, BudgetCategoryDto budgetCategory, DateTime startDate,
            DateTime endDate, String frequencyTypeName, BigDecimal amount, Boolean inUse) {

        this.id = id;
        this.name = name;
        this.budgetCategory = budgetCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequencyTypeName = frequencyTypeName;
        this.amount = amount;
        this.inUse = inUse;
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

    public BudgetCategoryDto getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategoryDto budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public String getFrequencyTypeName() {
        return frequencyTypeName;
    }

    public void setFrequencyTypeName(String frequencyTypeName) {
        this.frequencyTypeName = frequencyTypeName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getInUse() {
        return inUse;
    }

    public void setInUse(Boolean inUse) {
        this.inUse = inUse;
    }
}
