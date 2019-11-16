package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * User defined budgets
 */
@Entity
@Table(name = "budget")
public class Budget extends UserAuditable implements Serializable {

    @Id
    @Column(name = "budget_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @Column(name = "budget_item_name")
    private String name;

    @Column(name = "start_date")
    private ZonedDateTime startDate;

    @Column(name = "end_date")
    private ZonedDateTime endDate;

    @OneToOne(cascade = CascadeType.REFRESH, fetch = FetchType.EAGER)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "in_use", columnDefinition = "boolean default true")
    private Boolean inUse;

    public Budget() {
    }

    public Budget(
            BudgetCategory budgetCategory, String name, ZonedDateTime startDate, ZonedDateTime endDate,
            FrequencyType frequencyType, BigDecimal amount, Boolean inUse) {

        this.budgetCategory = budgetCategory;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
        this.frequencyType = frequencyType;
        this.amount = amount;
        this.inUse = inUse;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public FrequencyType getFrequencyType() {
        return frequencyType;
    }

    public void setFrequencyType(FrequencyType frequencyType) {
        this.frequencyType = frequencyType;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id) &&
                Objects.equals(budgetCategory, budget.budgetCategory) &&
                Objects.equals(name, budget.name) &&
                Objects.equals(startDate, budget.startDate) &&
                Objects.equals(endDate, budget.endDate) &&
                Objects.equals(frequencyType, budget.frequencyType) &&
                Objects.equals(amount, budget.amount) &&
                Objects.equals(inUse, budget.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetCategory, name, startDate, endDate, frequencyType, amount, inUse);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + id +
                ", budgetCategory=" + budgetCategory +
                ", budgetItemName='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetFrequency=" + frequencyType +
                ", amount=" + amount +
                ", inUse=" + inUse +
                '}';
    }

}
