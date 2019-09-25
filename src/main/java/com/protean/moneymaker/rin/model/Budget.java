package com.protean.moneymaker.rin.model;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

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

    @Column(name = "budget_item_name")
    private String budgetItemName;

    @Column(name = "start_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime startDate;

    @Column(name = "end_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime endDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "frequency_type_id")
    private FrequencyType frequencyType;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "in_use")
    private Boolean inUse;

    public Budget() {
    }

    public Budget(
            BudgetCategory budgetCategory, String budgetItemName, DateTime startDate, DateTime endDate,
            FrequencyType frequencyType, BigDecimal amount, Boolean inUse) {

        this.budgetCategory = budgetCategory;
        this.budgetItemName = budgetItemName;
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

    public String getBudgetItemName() {
        return budgetItemName;
    }

    public void setBudgetItemName(String budgetItemName) {
        this.budgetItemName = budgetItemName;
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
        return Objects.equals(id, budget.id) &&
                Objects.equals(budgetCategory, budget.budgetCategory) &&
                Objects.equals(budgetItemName, budget.budgetItemName) &&
                Objects.equals(startDate, budget.startDate) &&
                Objects.equals(endDate, budget.endDate) &&
                Objects.equals(frequencyType, budget.frequencyType) &&
                Objects.equals(amount, budget.amount) &&
                Objects.equals(inUse, budget.inUse);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, budgetCategory, budgetItemName, startDate, endDate, frequencyType, amount, inUse);
    }

    @Override
    public String toString() {
        return "Budget{" +
                "budgetId=" + id +
                ", budgetCategory=" + budgetCategory +
                ", budgetItemName='" + budgetItemName + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                ", budgetFrequency=" + frequencyType +
                ", amount=" + amount +
                ", inUse=" + inUse +
                '}';
    }

}
