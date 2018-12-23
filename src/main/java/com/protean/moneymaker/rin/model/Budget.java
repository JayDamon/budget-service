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
 * User defined budgets
 */
@Entity
@Table(name = "budget")
public class Budget extends UserAuditable implements Serializable {

    @Id
    @Column(name = "budget_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType budgetFrequency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "is_generic")
    private Boolean isGeneric;

    @Column(name = "is_in_use")
    private Boolean isInUse;

    public Budget() {
    }

    public Budget(BudgetCategory budgetCategory, Date startDate, Date endDate, FrequencyType budgetFrequency, BigDecimal amount, Boolean isGeneric, Boolean isInUse) {
        this.budgetCategory = budgetCategory;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetFrequency = budgetFrequency;
        this.amount = amount;
        this.isGeneric = isGeneric;
        this.isInUse = isInUse;
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

    public FrequencyType getBudgetFrequency() {
        return budgetFrequency;
    }

    public void setBudgetFrequency(FrequencyType budgetFrequency) {
        this.budgetFrequency = budgetFrequency;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Boolean getGeneric() {
        return isGeneric;
    }

    public void setGeneric(Boolean generic) {
        isGeneric = generic;
    }

    public Boolean getInUse() {
        return isInUse;
    }

    public void setInUse(Boolean inUse) {
        isInUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(id, budget.id) &&
                Objects.equals(budgetCategory, budget.budgetCategory) &&
                Objects.equals(startDate, budget.startDate) &&
                Objects.equals(endDate, budget.endDate) &&
                Objects.equals(budgetFrequency, budget.budgetFrequency) &&
                Objects.equals(amount, budget.amount) &&
                Objects.equals(isGeneric, budget.isGeneric) &&
                Objects.equals(isInUse, budget.isInUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetCategory, startDate, endDate, budgetFrequency, amount, isGeneric, isInUse);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "id=" + id +
                ", budgetCategory=" + budgetCategory +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetFrequency=" + budgetFrequency +
                ", amount=" + amount +
                ", isGeneric=" + isGeneric +
                ", isInUse=" + isInUse +
                '}';
    }
}
