package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "budget_category")
public class BudgetCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_category_id")
    private Integer budgetCategoryId;

    @OneToOne
    @JoinColumn(name = "budget_category_type_id")
    private BudgetCategory budgetCategory;

    @OneToOne
    @JoinColumn(name = "budget_category_name_id")
    private BudgetCategoryName budgetCategoryName;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(
            name = "budget_category_budget_sub_category",
            joinColumns = { @JoinColumn(name = "budget_category_id") },
            inverseJoinColumns = { @JoinColumn(name = "budget_sub_category_id") }
    )
    private Set<BudgetSubCategory> budgetSubCategories = new HashSet<>();

    public BudgetCategory() {
    }

    public BudgetCategory(BudgetCategory budgetCategory, BudgetCategoryName budgetCategoryName, Set<BudgetSubCategory> budgetSubCategories) {
        this.budgetCategory = budgetCategory;
        this.budgetCategoryName = budgetCategoryName;
        this.budgetSubCategories = budgetSubCategories;
    }

    public Integer getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Integer budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public BudgetCategory getBudgetCategory() {
        return budgetCategory;
    }

    public void setBudgetCategory(BudgetCategory budgetCategory) {
        this.budgetCategory = budgetCategory;
    }

    public BudgetCategoryName getBudgetCategoryName() {
        return budgetCategoryName;
    }

    public void setBudgetCategoryName(BudgetCategoryName budgetCategoryName) {
        this.budgetCategoryName = budgetCategoryName;
    }

    public Set<BudgetSubCategory> getBudgetSubCategories() {
        return budgetSubCategories;
    }

    public void setBudgetSubCategories(Set<BudgetSubCategory> budgetSubCategories) {
        this.budgetSubCategories = budgetSubCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetCategory that = (BudgetCategory) o;
        return Objects.equals(budgetCategoryId, that.budgetCategoryId) &&
                Objects.equals(budgetCategory, that.budgetCategory) &&
                Objects.equals(budgetCategoryName, that.budgetCategoryName) &&
                Objects.equals(budgetSubCategories, that.budgetSubCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetCategoryId, budgetCategory, budgetCategoryName, budgetSubCategories);
    }

    @Override
    public String toString() {
        return "BudgetCategory{" +
                "budgetCategoryId=" + budgetCategoryId +
                ", budgetCategory=" + budgetCategory +
                ", budgetCategoryName=" + budgetCategoryName +
                ", budgetSubCategories=" + budgetSubCategories +
                '}';
    }
}
