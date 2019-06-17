package com.protean.moneymaker.rin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(name = "budget_category")
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Integer budgetCategoryId;

    @OneToOne
    @JoinColumn(name = "budget_category_type_id")
    private BudgetCategoryType budgetCategoryType;

    @OneToOne
    @JoinColumn(name = "budget_category_name_id")
    private BudgetCategoryName budgetCategoryName;

    // TODO I removed BudgetSubCategory, but it had a direct link to TransactionCategory, so i may want that back if it is possible to link them up
    public BudgetCategory() {
    }

    public BudgetCategory(BudgetCategoryType budgetCategoryType, BudgetCategoryName budgetCategoryName) {
        this.budgetCategoryType = budgetCategoryType;
        this.budgetCategoryName = budgetCategoryName;
    }

    public Integer getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public BudgetCategoryType getBudgetCategoryType() {
        return budgetCategoryType;
    }

    public void setBudgetCategoryType(BudgetCategoryType budgetCategoryType) {
        this.budgetCategoryType = budgetCategoryType;
    }

    public BudgetCategoryName getBudgetCategoryName() {
        return budgetCategoryName;
    }

    public void setBudgetCategoryName(BudgetCategoryName budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetCategory that = (BudgetCategory) o;
        return Objects.equals(budgetCategoryId, that.budgetCategoryId) &&
                Objects.equals(budgetCategoryType, that.budgetCategoryType) &&
                Objects.equals(budgetCategoryName, that.budgetCategoryName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetCategoryId, budgetCategoryType, budgetCategoryName);
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "budgetCategoryId=" + budgetCategoryId +
                ", budgetCategoryType=" + budgetCategoryType +
                ", budgetCategoryName=" + budgetCategoryName +
                '}';
    }
}
