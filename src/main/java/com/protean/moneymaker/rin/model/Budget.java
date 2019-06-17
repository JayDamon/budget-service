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
    private Long budgetId;

    @OneToOne
    @JoinColumn(name = "budget_category_id")
    private BudgetCategory budgetCategory;

    @Column(name = "budget_item_name")
    private String budgetItemName;

    @Column(name = "start_date")
    private Date startDate;

    @Column(name = "end_date")
    private Date endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType budgetFrequency;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "in_use")
    private Boolean inUse;

    public Budget() {
    }

    public Budget(BudgetCategory budgetCategory, String budgetItemName, Date startDate, Date endDate, FrequencyType budgetFrequency, BigDecimal amount, Boolean inUse) {
        this.budgetCategory = budgetCategory;
        this.budgetItemName = budgetItemName;
        this.startDate = startDate;
        this.endDate = endDate;
        this.budgetFrequency = budgetFrequency;
        this.amount = amount;
        this.inUse = inUse;
    }

    public Long getBudgetId() {
        return budgetId;
    }

    public void setBudgetId(Long budgetId) {
        this.budgetId = budgetId;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public String getBudgetItemName() {
        return budgetItemName;
    }

    public void setBudgetItemName(String budgetItemName) {
        this.budgetItemName = budgetItemName;
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

    public Boolean isInUse() {
        return inUse;
    }

    public void isInUse(Boolean inUse) {
        this.inUse = inUse;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Budget budget = (Budget) o;
        return Objects.equals(budgetId, budget.budgetId) &&
                Objects.equals(budgetCategory, budget.budgetCategory) &&
                Objects.equals(budgetItemName, budget.budgetItemName) &&
                Objects.equals(startDate, budget.startDate) &&
                Objects.equals(endDate, budget.endDate) &&
                Objects.equals(budgetFrequency, budget.budgetFrequency) &&
                Objects.equals(amount, budget.amount) &&
                Objects.equals(inUse, budget.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetId, budgetCategory, budgetItemName, startDate, endDate, budgetFrequency, amount, inUse);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + budgetId +
                ", budgetCategory=" + budgetCategory +
                ", budgetItemName='" + budgetItemName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetFrequency=" + budgetFrequency +
                ", amount=" + amount +
                ", inUse=" + inUse +
                '}';
    }

}
