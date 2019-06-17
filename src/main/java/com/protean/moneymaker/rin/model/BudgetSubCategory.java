package com.protean.moneymaker.rin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * i.e. Primary transaction category // TODO fix this comment
 */
@Entity
@Table(name = "budget_sub_category")
public class BudgetSubCategory implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "budget_sub_category_id")
    private Long budgetCategoryId;

    @Column(name = "sub_category_name")
    private String category;

    @OneToOne
    @JoinColumn(name = "transaction_type_id")
    private TransactionType transactionType;

    @OneToMany(mappedBy = "budgetSubCategory", cascade = CascadeType.ALL)
    private Set<TransactionCategory> transactionCategories = new HashSet<>();

    @ManyToMany(mappedBy = "budgetSubCategories", cascade = CascadeType.ALL)
    private Set<BudgetCategory> budgetCategories = new HashSet<>();

    public BudgetSubCategory() {
    }

    public BudgetSubCategory(String category, TransactionType transactionType, Set<TransactionCategory> transactionCategories, Set<BudgetCategory> budgetCategories
    ) {
        this.category = category;
        this.transactionType = transactionType;
        this.transactionCategories = transactionCategories;
        this.budgetCategories = budgetCategories;
    }

    public Long getBudgetCategoryId() {
        return budgetCategoryId;
    }

    public void setBudgetCategoryId(Long budgetCategoryId) {
        this.budgetCategoryId = budgetCategoryId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public TransactionType getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }

    public Set<TransactionCategory> getTransactionCategories() {
        return transactionCategories;
    }

    public void setTransactionCategories(Set<TransactionCategory> transactionCategories) {
        this.transactionCategories = transactionCategories;
    }

    public Set<BudgetCategory> getBudgetCategories() {
        return budgetCategories;
    }

    public void setBudgetCategories(Set<BudgetCategory> budgetCategories) {
        this.budgetCategories = budgetCategories;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BudgetSubCategory that = (BudgetSubCategory) o;
        return Objects.equals(budgetCategoryId, that.budgetCategoryId) &&
                Objects.equals(category, that.category) &&
                Objects.equals(transactionType, that.transactionType) &&
                Objects.equals(transactionCategories, that.transactionCategories) &&
                Objects.equals(budgetCategories, that.budgetCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(budgetCategoryId, category, transactionType, transactionCategories, budgetCategories);
    }

    @Override
    public String toString() {
        return "BudgetSubCategory{" +
                "budgetCategoryId=" + budgetCategoryId +
                ", category='" + category + '\'' +
                ", transactionType=" + transactionType +
                ", transactionCategories=" + transactionCategories +
                ", budgetCategories=" + budgetCategories +
                '}';
    }
}
